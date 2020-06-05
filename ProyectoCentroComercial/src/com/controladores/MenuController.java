package com.controladores;

import com.modelo.CarritoCompras;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.tienda.Tienda;
import com.vista2.DibujadorCarrito;
import com.vista2.Menu;
import com.vista2.MenuTienda;
import com.vista2.UIConstants;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author HikingCarrot7
 */
public class MenuController implements UIConstants, TableModelListener
{

    private final int BOTON_TABLA_TIENDAS = 1;

    private final Menu vistaMenu;
    private final CentroComercial centroComercial;
    private final ListManager listManager;
    private final TableManager tableManger;
    private final DibujadorCarrito dibujadorCarrito;

    public MenuController(Menu vistaMenu, CentroComercial centroComercial)
    {
        this.vistaMenu = vistaMenu;
        this.centroComercial = centroComercial;
        this.listManager = ListManager.getInstance();
        this.tableManger = TableManager.getInstance();
        this.dibujadorCarrito = new DibujadorCarrito(vistaMenu.getLblCarrito());
        initComponents();
        iniciarListaClientes();
        iniciarTablaTiendas();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponents()
    {
        vistaMenu.getBtnSolicitarCarrito().addActionListener(this::accionBtnSolicitarCarrito);
        vistaMenu.getBtnPagarCarrito().addActionListener(this::accionBtnPagarCarrito);

        // Iniciamos la lista que muestra a los clientes.
        JList<Cliente> listaClientes = vistaMenu.getListClientes();
        listManager.initList(listaClientes);
        listManager.initListSelectionBehavior(listaClientes, null);
        listaClientes.setCellRenderer(LISTA_CLIENTES_RENDERER);
        listaClientes.getSelectionModel().addListSelectionListener(this::accionClicListaClientes);

        // Iniciamos la tabla que muestra las tiendas.
        JTable tabla = vistaMenu.getTablaTiendas();
        tabla.getModel().addTableModelListener(this);
        tableManger.initTabla(tabla);
        tableManger.initTableSelectionBehavior(tabla);
        tableManger.quitarCabeceraTabla(tabla);

        // Para mostrar los botones en la tabla.
        tabla.getColumnModel().getColumn(BOTON_TABLA_TIENDAS).setCellRenderer((table, value, isSelected, hasFocus, row, column) ->
        {
            JButton button = new JButton(DEFAULT_ROW_ICON);
            button.setToolTipText("Ir a la tienda");
            return button;
        });

        tabla.getColumnModel().getColumn(BOTON_TABLA_TIENDAS).setCellEditor(new ButtonCellEditor());
        tabla.putClientProperty("terminateEditOnFocusLost", true);

        // Deshabilitamos el panel para seleccionar el carrito y la tabla de las tiendas (aún no hay ningún cliente seleccionado).
        habilitarPanelesSecundarios(false);
    }
    //</editor-fold>

    /**
     * Rellenamos la lista de los clientes.
     */
    private void iniciarListaClientes()
    {
        listManager.establecerElementos(vistaMenu.getListClientes(), centroComercial.listarClientes());
    }

    /**
     * Rellenamos la tabla de las tiendas.
     */
    private void iniciarTablaTiendas()
    {
        JTable tabla = vistaMenu.getTablaTiendas();
        Enumeration<Tienda> tiendas = centroComercial.listarTiendas();
        tableManger.vaciarTabla(tabla);

        while (tiendas.hasMoreElements())
        {
            Tienda tienda = tiendas.nextElement();
            tableManger.addFila(tabla, new Object[]
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

            CarritoCompras carritoCompras = clienteSeleccionado.getCarritoCompras();
            dibujadorCarrito.actualizar(carritoCompras != null ? carritoCompras.getEstado() : -1);

        } else
        {
            habilitarPanelesSecundarios(false);
            actualizarClienteSeleccionado("Seleccione a un cliente...");
            dibujadorCarrito.actualizar(-1);
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
        };

        if (carritoClienteTieneArticulos(clienteSeleccionado))
        {
            if (Alerta.mostrarConfirmacion(vistaMenu, "Confimación",
                    "Todos los artículos de su carrito anterior serán eliminados. ¿Está seguro?"))
                accionSolicitarCarrito.accept(null);

        } else
            accionSolicitarCarrito.accept(null);
    }

    private void accionBtnPagarCarrito(ActionEvent e)
    {
        Cliente clienteActual = getClienteSeleccionado();

        if (carritoClienteTieneArticulos(clienteActual))
        {

        } else
            Alerta.mostrarMensaje(vistaMenu, "No hay artículos", "No hay ningún artículo en tu carrito");
    }

    /**
     * Se presionó en algún botón de la tabla.
     *
     * @param e
     */
    @Override public void tableChanged(TableModelEvent e)
    {
        if (e.getColumn() == BOTON_TABLA_TIENDAS)
        {
            MenuTienda menuTienda = new MenuTienda(vistaMenu);

            DialogUtils.showDialogAndWait(vistaMenu, menuTienda);
        }
    }

    private boolean carritoClienteTieneArticulos(Cliente cliente)
    {
        return cliente.getCarritoCompras() != null && !cliente.getCarritoCompras().estaVacio();
    }

    private void actualizarClienteSeleccionado(String txt)
    {
        vistaMenu.getLblClienteSeleccionado().setText(txt);
    }

    /**
     * Consideramos secundarios al panel del carrito y la tabla de las tiendas.
     *
     * @param habilitar <code>true</code> si quiremos habilitar dichos paneles; false en caso contrario.
     */
    private void habilitarPanelesSecundarios(boolean habilitar)
    {
        SwingUtils.setPanelEnabled(vistaMenu.getPanelTiendas(), habilitar);
        SwingUtils.setPanelEnabled(vistaMenu.getPanelCarrito(), habilitar);
        vistaMenu.getTablaTiendas().setEnabled(habilitar);
    }

    private Cliente getClienteSeleccionado()
    {
        return vistaMenu.getListClientes().getSelectedValue();
    }

}
