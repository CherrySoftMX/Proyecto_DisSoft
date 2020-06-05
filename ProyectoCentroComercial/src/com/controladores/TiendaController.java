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
import com.modelo.estado.CarritoEstado;
import com.modelo.tienda.Tienda;
import com.vista.MenuCarrito;
import com.vista.MenuTienda;
import com.vista.UIConstants;
import static com.vista.UIConstants.DEFAULT_ROW_ICON;
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

    private final MenuTienda vistaTienda;
    private final Tienda tienda;
    private final Cliente cliente;
    private final TableManager tableManager;

    public TiendaController(MenuTienda vistaTienda, Cliente cliente, Tienda tienda)
    {
        this.vistaTienda = vistaTienda;
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
        vistaTienda.getBtnDetallesCarrito().addActionListener(this::accionBtnDetallesCarrito);
        vistaTienda.getBtnComprarAhora().addActionListener(this::accionBtnComprarAhora);
        vistaTienda.getBtnSalir().addActionListener(this::accionBtnSalir);
        vistaTienda.getLblNombreTienda().setText(tienda.getNombre());
        initTablas();
        initPopupMenus();
        // El cliente no puede mover artículos ni comprar nada si entra sin carrito.
        habilitarAccionesEnTienda(cliente.getCarritoCompras() != null);
    }

    private void initTablas()
    {
        // Iniciamos la tabla que muestra los artículos
        JTable tabla = vistaTienda.getTablaArticulos();
        tabla.getModel().addTableModelListener(this::clicEnTablaArticulos);
        tableManager.initTabla(tabla);
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
        tabla = vistaTienda.getTablaCarrito();
        tableManager.initTabla(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);
    }

    private void initPopupMenus()
    {
        // Inicializamos el popupmenu de la tabla de los artículos de la tienda.
        final JTable tablaArticulos = vistaTienda.getTablaArticulos();
        JPopupMenu popupMenuTablaArticulos = new JPopupMenu();

        popupMenuTablaArticulos.add(new JMenuItem(new AccionEmergente("Añadir al carrito", CARRITO_ICON, e
                -> anadirArticuloAlCarrito(tienda.getArticulos().get(tablaArticulos.getSelectedRow())))));

        JMenuItem paqueteMenuItem = new JMenuItem(new AccionEmergente("Ver detalles del paquete", PAQUETE_ICON, null));
        popupMenuTablaArticulos.add(paqueteMenuItem);

        popupMenuTablaArticulos.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                int rowClicked = tableManager.getRowClicked(tablaArticulos, tablaArticulos.getMousePosition().getY());
                tableManager.selecionarFila(tablaArticulos, rowClicked);
                paqueteMenuItem.setEnabled(false);
            }
        });

        tablaArticulos.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);
        tablaArticulos.setComponentPopupMenu(popupMenuTablaArticulos);

        // Inicializamos el popupmenu de la tabla de los artículos contenidos en el carrito.
        final JTable tablaCarrito = vistaTienda.getTablaCarrito();
        JPopupMenu popupMenuTablaCarrito = new JPopupMenu();

        popupMenuTablaCarrito.add(new JMenuItem(new AccionEmergente("Eliminar del carrito", DELETE_ICON, e
                -> eliminarArticuloDelCarrito(cliente.getCarritoCompras().getArticulo(tablaCarrito.getSelectedRow())))));

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
        JTable tabla = vistaTienda.getTablaArticulos();
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

        if (carritoCompras != null)
        {
            Enumeration<Articulo> articulos = carritoCompras.listarArticulos();

            while (articulos.hasMoreElements())
            {
                Articulo articulo = articulos.nextElement();
                anadirArticuloATablaCarrito(articulo);
            }
        }
    }

    private void accionBtnDetallesCarrito(ActionEvent e)
    {
        CarritoCompras carritoActual = cliente.getCarritoCompras();

        if (!carritoActual.estaVacio())
        {
            MenuCarrito menuCarrito = new MenuCarrito(vistaTienda);
            new CarritoController(menuCarrito, cliente.getCarritoCompras());
            DialogUtils.showDialogAndWait(vistaTienda, menuCarrito);

        } else
            Alerta.mostrarError(vistaTienda, "Tu carrito está vacío.");
    }

    private void accionBtnComprarAhora(ActionEvent e)
    {

    }

    /**
     * Se presionó en algún botón de la tabla de artículos de la tienda.
     *
     * @param e
     */
    private void clicEnTablaArticulos(TableModelEvent e)
    {
        if (e.getColumn() == BOTON_TABLA_ARTICULOS)
            if (cliente.getCarritoCompras().getEstado() != CarritoEstado.ESTADO_LLENO)
            {
                Articulo articuloSeleccionado = tienda.getArticulos().get(e.getFirstRow());
                anadirArticuloAlCarrito(articuloSeleccionado);

            } else
                Alerta.mostrarError(vistaTienda, "El carrito ya está lleno.");
    }

    private void accionBtnSalir(ActionEvent e)
    {
        DialogUtils.quitarDialog(vistaTienda);
    }

    private void anadirArticuloAlCarrito(Articulo articulo)
    {
        anadirArticuloATablaCarrito(articulo);
        cliente.getCarritoCompras().addArticulo(articulo);
    }

    private void eliminarArticuloDelCarrito(Articulo articulo)
    {
        List<Articulo> articulos = cliente.getCarritoCompras().getArticulos();
        tableManager.eliminarFila(vistaTienda.getTablaCarrito(), articulos.indexOf(articulo));
        cliente.getCarritoCompras().eliminarArticulo(articulo);
    }

    private void anadirArticuloATablaCarrito(Articulo articulo)
    {
        tableManager.addFila(vistaTienda.getTablaCarrito(), new Object[]
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
        SwingUtils.setPanelEnabled(vistaTienda.getPanelListaArticulos(), habilitar);
        SwingUtils.setPanelEnabled(vistaTienda.getPanelTablaCarrito(), habilitar);
        vistaTienda.getTablaArticulos().setEnabled(habilitar);
        vistaTienda.getTablaCarrito().setEnabled(habilitar);
        vistaTienda.getBtnComprarAhora().setEnabled(habilitar);
    }

}
