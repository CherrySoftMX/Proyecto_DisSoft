package com.controladores;

import com.controladores.util.AccionEmergente;
import com.controladores.util.Alerta;
import com.controladores.util.ButtonCellEditor;
import com.controladores.util.DialogUtils;
import com.controladores.util.PopupMenuAdapter;
import com.controladores.util.SwingUtils;
import com.controladores.util.TableManager;
import com.modelo.CarritoCompras;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete;
import com.modelo.estado.CarritoCancelado;
import com.modelo.estado.CarritoEstado;
import com.modelo.exceptions.CarritoLlenoException;
import com.modelo.exceptions.CarritoVacioException;
import com.modelo.tienda.Tienda;
import com.vista.MenuCarrito;
import com.vista.MenuPago;
import com.vista.MenuTienda;
import com.vista.UIConstants;
import com.vista.VistaDetallesPaquete;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author HikingCarrot7
 */
public class TiendaController implements UIConstants
{

    private final int BOTON_TABLA_ARTICULOS = 2;

    private final MenuTienda menuTienda;
    private final Tienda tienda;
    private final Cliente cliente;
    private final TableManager tableManager;

    public TiendaController(MenuTienda vistaTienda, Cliente cliente, Tienda tienda)
    {
        this.menuTienda = vistaTienda;
        this.cliente = cliente;
        this.tienda = tienda;
        this.tableManager = TableManager.getInstance();
        initComponents();
        initListaArticulos();
        initListaCarrito();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponents()
    {
        menuTienda.getBtnDetallesCarrito().addActionListener(this::accionBtnDetallesCarrito);
        menuTienda.getBtnCancelarCarrito().addActionListener(this::accionBtnCancelarCarrito);
        menuTienda.getBtnComprarAhora().addActionListener(this::accionBtnComprarAhora);
        menuTienda.getBtnSalir().addActionListener(this::accionBtnSalir);
        menuTienda.getLblNombreTienda().setText(tienda.getNombre());
        initTablas();
        initPopupMenus();

        // El cliente no puede mover artículos ni comprar nada si entra sin carrito.
        habilitarAccionesEnTienda(cliente.getCarritoCompras() != null
                && !cliente.getCarritoCompras().estaCancelado());

        if (cliente.getCarritoCompras() != null && !cliente.getCarritoCompras().estaCancelado())
            habilitarAccionesDelCarrito(!cliente.getCarritoCompras().estaVacio());

        else
            habilitarAccionesDelCarrito(false);
    }

    private void initTablas()
    {
        // Iniciamos la tabla que muestra los artículos
        JTable tabla = menuTienda.getTablaArticulos();
        tabla.getModel().addTableModelListener(this::clicEnTablaArticulos);
        tableManager.initTable(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);

        // Para mostrar los botones en la tabla de los artículos.
        tabla.getColumnModel().getColumn(BOTON_TABLA_ARTICULOS).setCellRenderer((table, value, isSelected, hasFocus, row, column) ->
        {
            JButton button = new JButton(DEFAULT_ROW_ICON);
            button.setToolTipText("Mover al carrito");
            return button;
        });

        tabla.getColumnModel().getColumn(BOTON_TABLA_ARTICULOS).setCellEditor(new ButtonCellEditor());

        // Inicializamos la tabla que muestra el carrito del cliente actual.
        tabla = menuTienda.getTablaCarrito();
        tableManager.initTable(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);
    }

    private void initPopupMenus()
    {
        // Inicializamos el popupmenu de la tabla de los artículos de la tienda.
        final JTable tablaArticulos = menuTienda.getTablaArticulos();
        JPopupMenu popupMenuTablaArticulos = new JPopupMenu();

        popupMenuTablaArticulos.add(new JMenuItem(new AccionEmergente("Añadir al carrito", CARRITO_ICON, this::accionPopupMenuAnadirAlCarrito)));

        JMenuItem paqueteMenuItem = new JMenuItem(new AccionEmergente("Ver detalles del paquete", PAQUETE_ICON, this::accionPopupMenuVerDetallesDelPaquete));
        popupMenuTablaArticulos.add(paqueteMenuItem);

        popupMenuTablaArticulos.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                int rowClicked = tableManager.getRowClicked(tablaArticulos, tablaArticulos.getMousePosition().getY());
                tableManager.selecionarFila(tablaArticulos, rowClicked);
                paqueteMenuItem.setEnabled(tienda.getArticulo(rowClicked) instanceof Paquete);
            }
        });

        tablaArticulos.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);
        tablaArticulos.setComponentPopupMenu(popupMenuTablaArticulos);

        // Inicializamos el popupmenu de la tabla de los artículos contenidos en el carrito.
        final JTable tablaCarrito = menuTienda.getTablaCarrito();
        JPopupMenu popupMenuTablaCarrito = new JPopupMenu();

        popupMenuTablaCarrito.add(new JMenuItem(new AccionEmergente("Eliminar del carrito", DELETE_ICON, this::accionPopupMenuEliminarDelCarrito)));

        popupMenuTablaCarrito.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                int rowClicked = tableManager.getRowClicked(tablaCarrito, tablaCarrito.getMousePosition().getY());
                tableManager.selecionarFila(tablaCarrito, rowClicked);
            }
        });

        tablaCarrito.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);
        tablaCarrito.setComponentPopupMenu(popupMenuTablaCarrito);
    }
    //</editor-fold>

    private void initListaArticulos()
    {
        JTable tabla = menuTienda.getTablaArticulos();
        Enumeration<Articulo> articulos = tienda.listarArticulos();

        while (articulos.hasMoreElements())
        {
            Articulo articulo = articulos.nextElement();
            tableManager.addFila(tabla, new Object[]
            {
                articulo, String.format("$%,.2f", articulo.getPrecio())
            });
        }
    }

    private void initListaCarrito()
    {
        CarritoCompras carritoCompras = cliente.getCarritoCompras();
        tableManager.vaciarTabla(menuTienda.getTablaCarrito());

        if (carritoCompras != null)
        {
            Enumeration<Articulo> articulos = carritoCompras.listarArticulos();

            while (articulos.hasMoreElements())
            {
                Articulo articulo = articulos.nextElement();
                anadirArticuloATablaCarrito(articulo);
            }
            habilitarBtnCancelarCarrito(true);
        }
    }

    private void accionBtnDetallesCarrito(ActionEvent e)
    {
        try
        {
            CarritoCompras carritoActual = cliente.getCarritoCompras();

            if (carritoActual.estaVacio())
                throw new CarritoVacioException();

            MenuCarrito menuCarrito = new MenuCarrito(menuTienda);
            new CarritoController(menuCarrito, cliente);
            DialogUtils.showDialogAndWait(menuTienda, menuCarrito);
            initListaCarrito();
            habilitarAccionesDelCarrito(!carritoActual.estaVacio());

        } catch (CarritoVacioException ex)
        {
            Alerta.mostrarError(menuTienda, ex.getMessage());
        }
    }

    private void accionBtnCancelarCarrito(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(menuTienda, "Perderá todos los artículos del carrito. ¿Está seguro?"))
        {
            cliente.getCarritoCompras().setEstado(new CarritoCancelado());
            habilitarAccionesEnTienda(false);
            habilitarAccionesDelCarrito(false);
        }
    }

    private void accionBtnComprarAhora(ActionEvent e)
    {
        try
        {
            if (cliente.getCarritoCompras().estaVacio())
                throw new CarritoVacioException();

            MenuPago menuPago = new MenuPago(menuTienda);
            PagoController pagoController = new PagoController(menuPago, cliente);
            pagoController.anadirObservador(i -> DialogUtils.quitarDialog(menuTienda));
            DialogUtils.showDialogAndWait(menuTienda, menuPago);
            initListaCarrito();

        } catch (CarritoVacioException ex)
        {
            Alerta.mostrarError(menuTienda, ex.getMessage());
        }
    }

    /**
     * Se presionó en algún botón de la tabla de artículos de la tienda.
     *
     * @param e
     */
    private void clicEnTablaArticulos(TableModelEvent e)
    {
        if (e.getColumn() == BOTON_TABLA_ARTICULOS)
            try
            {
                if (cliente.getCarritoCompras().getEstado() == CarritoEstado.ESTADO_LLENO)
                    throw new CarritoLlenoException();

                Articulo articuloSeleccionado = tienda.getArticulos().get(e.getFirstRow());
                anadirArticuloAlCarrito(articuloSeleccionado);

                habilitarAccionesDelCarrito(true);

            } catch (CarritoLlenoException ex)
            {
                Alerta.mostrarError(menuTienda, ex.getMessage());
            }
    }

    /**
     * Se ha presionado la opción "añadir al carrito" en el popup menú de la tabla de artículos.
     *
     * @param e
     */
    private void accionPopupMenuAnadirAlCarrito(ActionEvent e)
    {
        JTable tablaArticulos = menuTienda.getTablaArticulos();
        anadirArticuloAlCarrito(tienda.getArticulos().get(tablaArticulos.getSelectedRow()));
    }

    /**
     * Se ha presionado la opción "ver detalles del paquete" en el popup menú de la tabla de artículos.
     *
     * @param e
     */
    private void accionPopupMenuVerDetallesDelPaquete(ActionEvent e)
    {
        JTable tablaArticulos = menuTienda.getTablaArticulos();
        VistaDetallesPaquete vistaDetallesPaquete = new VistaDetallesPaquete(menuTienda);

        new DetallesPaqueteController(vistaDetallesPaquete,
                (Paquete) tienda.getArticulo(tablaArticulos.getSelectedRow()));

        DialogUtils.showDialogAndWait(menuTienda, vistaDetallesPaquete);
    }

    /**
     * Se ha presionado la opción "eliminar del carrito" en el popup menú de la tabla de artículos del carrito.
     *
     * @param e
     */
    private void accionPopupMenuEliminarDelCarrito(ActionEvent e)
    {
        JTable tablaCarrito = menuTienda.getTablaCarrito();
        eliminarArticuloDelCarrito(cliente.getCarritoCompras().getArticulo(tablaCarrito.getSelectedRow()));
    }

    private void accionBtnSalir(ActionEvent e)
    {
        DialogUtils.quitarDialog(menuTienda);
    }

    private void anadirArticuloAlCarrito(Articulo articulo)
    {
        anadirArticuloATablaCarrito(articulo);
        cliente.getCarritoCompras().addArticulo(articulo);
    }

    private void eliminarArticuloDelCarrito(Articulo articulo)
    {
        List<Articulo> articulos = cliente.getCarritoCompras().getArticulos();
        tableManager.eliminarFila(menuTienda.getTablaCarrito(), articulos.indexOf(articulo));
        cliente.getCarritoCompras().eliminarArticulo(articulo);
    }

    private void anadirArticuloATablaCarrito(Articulo articulo)
    {
        tableManager.addFila(menuTienda.getTablaCarrito(), new Object[]
        {
            articulo, String.format("$%,.2f", articulo.getPrecio())
        });
    }

    /**
     * Las acciones de la tienda son: mover artículos al carrito, ver los detalles del carrito y comprar ahora.
     *
     * @param habilitar <code>true</code> si quiremos habilitar dichos componentes; <code>false</code> en caso contrario.
     */
    private void habilitarAccionesEnTienda(boolean habilitar)
    {
        SwingUtils.setPanelEnabled(menuTienda.getPanelListaArticulos(), habilitar);
        SwingUtils.setPanelEnabled(menuTienda.getPanelTablaCarrito(), habilitar);
        menuTienda.getTablaArticulos().setEnabled(habilitar);
        menuTienda.getTablaCarrito().setEnabled(habilitar);
        habilitarBtnComprarAhora(habilitar);
    }

    private void habilitarAccionesDelCarrito(boolean habilitar)
    {
        habilitarBtnVerDetalles(habilitar);
        habilitarBtnCancelarCarrito(habilitar);
        habilitarBtnComprarAhora(habilitar);
    }

    private void habilitarBtnVerDetalles(boolean habilitar)
    {
        menuTienda.getBtnDetallesCarrito().setEnabled(habilitar);
    }

    private void habilitarBtnCancelarCarrito(boolean habilitar)
    {
        menuTienda.getBtnCancelarCarrito().setEnabled(habilitar);
    }

    private void habilitarBtnComprarAhora(boolean habilitar)
    {
        menuTienda.getBtnComprarAhora().setEnabled(habilitar);
    }

}
