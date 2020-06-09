package com.controladores;

import com.controladores.util.Alerta;
import com.controladores.util.ButtonCellEditor;
import com.controladores.util.DialogUtils;
import com.controladores.util.ListManager;
import com.controladores.util.SwingUtils;
import com.controladores.util.TableManager;
import com.modelo.CarritoCompras;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.estado.CarritoCancelado;
import com.modelo.tienda.Tienda;
import com.vista.DibujadorCarrito;
import com.vista.Menu;
import com.vista.MenuPago;
import com.vista.MenuTienda;
import com.vista.UIConstants;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author HikingCarrot7
 */
public class MenuController implements UIConstants
{

    private final int BOTON_TABLA_TIENDAS = 1;

    private final Menu menu;
    private final CentroComercial centroComercial;
    private final ListManager listManager;
    private final TableManager tableManager;
    private final DibujadorCarrito dibujadorCarrito;

    public MenuController(Menu vistaMenu, CentroComercial centroComercial)
    {
        this.menu = vistaMenu;
        this.centroComercial = centroComercial;
        this.listManager = ListManager.getInstance();
        this.tableManager = TableManager.getInstance();
        this.dibujadorCarrito = new DibujadorCarrito(vistaMenu.getLblCarrito());
        initComponents();
        iniciarListaClientes();
        iniciarTablaTiendas();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponents()
    {
        menu.getBtnSolicitarCarrito().addActionListener(this::accionBtnSolicitarCarrito);
        menu.getBtnPagarCarrito().addActionListener(this::accionBtnPagarCarrito);
        menu.getBtnCancelarCarrito().addActionListener(this::accionBtnCancelarCarrito);

        // Iniciamos la lista que muestra a los clientes.
        JList<Cliente> listaClientes = menu.getListClientes();
        listManager.initList(listaClientes);
        listManager.initListSelectionBehavior(listaClientes, null);
        listaClientes.setCellRenderer(LISTA_CLIENTES_RENDERER);
        listaClientes.getSelectionModel().addListSelectionListener(this::accionClicListaClientes);

        // Iniciamos la tabla que muestra las tiendas.
        JTable tabla = menu.getTablaTiendas();
        tabla.getModel().addTableModelListener(this::clicEnTablaTiendas);
        tabla.getColumnModel().getColumn(0).setCellRenderer(TIENDA_COLUMNA_RENDERER);
        tableManager.initTable(tabla);
        tableManager.initTableSelectionBehavior(tabla);
        tableManager.quitarCabeceraTabla(tabla);

        // Para mostrar los botones en la tabla de la tiendas.
        tabla.getColumnModel().getColumn(BOTON_TABLA_TIENDAS).setCellRenderer((table, value, isSelected, hasFocus, row, column) ->
        {
            JButton button = new JButton(DEFAULT_ROW_ICON);
            button.setToolTipText("Ir a la tienda");
            return button;
        });

        tabla.getColumnModel().getColumn(BOTON_TABLA_TIENDAS).setCellEditor(new ButtonCellEditor());

        // Deshabilitamos el panel para seleccionar el carrito y la tabla de las tiendas (aún no hay ningún cliente seleccionado).
        habilitarPanelesSecundarios(false);
    }
    //</editor-fold>

    /**
     * Rellenamos la lista de los clientes.
     */
    private void iniciarListaClientes()
    {
        listManager.establecerElementos(menu.getListClientes(), centroComercial.listarClientes());
    }

    /**
     * Rellenamos la tabla de las tiendas.
     */
    private void iniciarTablaTiendas()
    {
        JTable tabla = menu.getTablaTiendas();
        Enumeration<Tienda> tiendas = centroComercial.listarTiendas();
        tableManager.vaciarTabla(tabla);

        while (tiendas.hasMoreElements())
        {
            Tienda tienda = tiendas.nextElement();
            tableManager.addFila(tabla, new Object[]
            {
                tienda.getNombre()
            });
        }
    }

    private void accionClicListaClientes(ListSelectionEvent e)
    {
        Cliente clienteSeleccionado = getClienteSeleccionado();

        if (clienteSeleccionado != null)
        {
            habilitarPanelesSecundarios(true);
            actualizarClienteSeleccionado("Cliente actual: " + clienteSeleccionado.getNombre());

            Cliente clienteActual = getClienteSeleccionado();
            dibujadorCarrito.actualizar(clienteActual.tieneCarrito() ? clienteActual.getCarritoCompras().getEstado() : -1);

            comprobarBtnPagar();
            comprobarBtnCancelarCarrito();

        } else
        {
            habilitarPanelesSecundarios(false);
            actualizarClienteSeleccionado("Seleccione a un cliente...");
            dibujadorCarrito.actualizar(-1);
            comprobarBtnPagar();
            comprobarBtnCancelarCarrito();
        }
    }

    private void accionBtnSolicitarCarrito(ActionEvent e)
    {
        Cliente clienteSeleccionado = getClienteSeleccionado();

        Consumer<Object> accionSolicitarCarrito = object ->
        {
            CarritoCompras carrito = centroComercial.getCarrito();
            Cliente clienteActual = getClienteSeleccionado();
            clienteActual.setCarritoCompras(carrito);
            carrito.anadirObservador(dibujadorCarrito);
            carrito.notificar(carrito.getEstado());
            comprobarBtnCancelarCarrito();
        };

        if (carritoClienteTieneArticulos(clienteSeleccionado)
                && !clienteSeleccionado.getCarritoCompras().estaCancelado())
        {
            if (Alerta.mostrarConfirmacion(menu,
                    "Todos los artículos de su carrito serán eliminados. ¿Está seguro?"))
                accionSolicitarCarrito.accept(null);

        } else
            accionSolicitarCarrito.accept(null);
    }

    private void accionBtnPagarCarrito(ActionEvent e)
    {
        Cliente clienteActual = getClienteSeleccionado();
        MenuPago menuPago = new MenuPago(menu);
        new PagoController(menuPago, clienteActual);

        DialogUtils.showDialogAndWait(menu, menuPago);

        comprobarBtnPagar();
        comprobarBtnCancelarCarrito();
    }

    private void accionBtnCancelarCarrito(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(menu, "Perderá todos los artículos del carrito. ¿Está seguro?"))
        {
            Cliente cliente = getClienteSeleccionado();
            cliente.getCarritoCompras().setEstado(new CarritoCancelado());
            comprobarBtnPagar();
            comprobarBtnCancelarCarrito();
        }
    }

    /**
     * Se presionó en algún botón de la tabla.
     *
     * @param e
     */
    private void clicEnTablaTiendas(TableModelEvent e)
    {
        if (e.getColumn() == BOTON_TABLA_TIENDAS)
        {
            Cliente clienteActual = getClienteSeleccionado();

            Consumer<Object> accionEntrarATienda = object ->
            {
                Tienda tiendaSeleccionada = centroComercial.getTiendas().get(e.getFirstRow());
                tiendaSeleccionada.entrar(clienteActual);
                clienteActual.setTiendaActual(tiendaSeleccionada);

                MenuTienda menuTienda = new MenuTienda(menu);
                TiendaController tiendaController = new TiendaController(menuTienda, clienteActual, tiendaSeleccionada);

                DialogUtils.showDialogAndWait(menu, menuTienda);

                // Cuando la ventana del menú de la tienda se cierre, sacaremos al cliente de la tienda.
                tiendaSeleccionada.salir(clienteActual);
                clienteActual.setTiendaActual(null);
                comprobarBtnPagar();
                comprobarBtnCancelarCarrito();
            };

            if (!clienteActual.tieneCarrito() || clienteActual.getCarritoCompras().estaCancelado())
            {
                if (Alerta.mostrarConfirmacion(menu, "No tiene carrito",
                        "No podrá comprar nada si entra sin carrito. ¿Está seguro?"))
                    accionEntrarATienda.accept(null);

            } else
                accionEntrarATienda.accept(null);
        }
    }

    private boolean carritoClienteTieneArticulos(Cliente cliente)
    {
        return cliente.tieneCarrito() && !cliente.getCarritoCompras().estaVacio();
    }

    private void actualizarClienteSeleccionado(String txt)
    {
        menu.getLblClienteSeleccionado().setText(txt);
    }

    /**
     * Consideramos secundarios al panel del carrito y la tabla de las tiendas.
     *
     * @param habilitar <code>true</code> si quiremos habilitar dichos componentes; <code>false</code> en caso contrario.
     */
    private void habilitarPanelesSecundarios(boolean habilitar)
    {
        SwingUtils.setPanelEnabled(menu.getPanelTiendas(), habilitar);
        SwingUtils.setPanelEnabled(menu.getPanelCarrito(), habilitar);
        menu.getTablaTiendas().setEnabled(habilitar);
    }

    private void comprobarBtnPagar()
    {
        Cliente clienteSeleccionado = getClienteSeleccionado();
        menu.getBtnPagarCarrito().setEnabled(clienteSeleccionado != null
                && carritoClienteTieneArticulos(clienteSeleccionado)
                && !clienteSeleccionado.getCarritoCompras().estaCancelado());
    }

    private void comprobarBtnCancelarCarrito()
    {
        Cliente clienteSeleccionado = getClienteSeleccionado();
        menu.getBtnCancelarCarrito().setEnabled(clienteSeleccionado != null
                && clienteSeleccionado.tieneCarrito()
                && !clienteSeleccionado.getCarritoCompras().estaCancelado());
    }

    private Cliente getClienteSeleccionado()
    {
        return menu.getListClientes().getSelectedValue();
    }

}
