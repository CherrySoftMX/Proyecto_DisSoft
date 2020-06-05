package com.controladores;

import com.controladores.util.AccionEmergente;
import com.controladores.util.DialogUtils;
import com.controladores.util.PopupMenuAdapter;
import com.controladores.util.TableManager;
import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;
import com.vista.DibujadorCarrito;
import com.vista.MenuCarrito;
import com.vista.UIConstants;
import static com.vista.UIConstants.PAQUETE_ICON;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author HikingCarrot7
 */
public class CarritoController implements UIConstants
{

    private final MenuCarrito menuCarrito;
    private final CarritoCompras carrito;
    private final TableManager tableManager;
    private final DibujadorCarrito dibujadorCarrito;

    public CarritoController(MenuCarrito menuCarrito, CarritoCompras carrito)
    {
        this.menuCarrito = menuCarrito;
        this.carrito = carrito;
        this.tableManager = TableManager.getInstance();
        this.dibujadorCarrito = new DibujadorCarrito(menuCarrito.getLblCarrito());
        initComponets();
        initListaArticulos();
    }

    private void initComponets()
    {
        menuCarrito.getBtnEliminarDelCarrito().addActionListener(this::accionBtnEliminarDelCarrito);
        menuCarrito.getBtnSalir().addActionListener(this::accionBtnSalir);
        carrito.anadirObservador(dibujadorCarrito);
        carrito.notificar(carrito.getEstado());
        initTabla();
        initPopupMenus();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initTabla()
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        tabla.getSelectionModel().addListSelectionListener(this::accionClicTablaArticulos);
        tabla.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);

        tableManager.initTabla(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);
    }

    private void initPopupMenus()
    {
        // Inicializamos el popupmenu de la tabla de los artículos del carrito.
        final JTable tablaArticulos = menuCarrito.getTablaArticulosCarrito();
        JPopupMenu popupMenuTablaArticulos = new JPopupMenu();

        popupMenuTablaArticulos.add(new JMenuItem(new AccionEmergente("Eliminar del carrito", DELETE_ICON, null)));
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

        tablaArticulos.setComponentPopupMenu(popupMenuTablaArticulos);
    }
    //</editor-fold>

    private void initListaArticulos()
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        Enumeration<Articulo> articulos = carrito.listarArticulos();

        while (articulos.hasMoreElements())
        {
            Articulo articulo = articulos.nextElement();
            tableManager.addFila(tabla, new Object[]
            {
                articulo, String.format("$%,.2f", articulo.getPrecio())
            });
        }
    }

    private void accionBtnEliminarDelCarrito(ActionEvent e)
    {

    }

    private void accionClicTablaArticulos(ListSelectionEvent e)
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        int selectedRow = tabla.getSelectedRow();

        if (selectedRow >= 0)
        {
            Articulo articuloSeleccionado = carrito.getArticulo(selectedRow);
            actualizarCamposArticulo(articuloSeleccionado);

        } else
            limpiarCamposArticulo();
    }

    private void actualizarCamposArticulo(Articulo articulo)
    {
        menuCarrito.getTxtNombre().setText(articulo.getNombre());
        menuCarrito.getTxtDescripcion().setText(articulo.getDescripcion());
        menuCarrito.getTxtPrecio().setText(String.format("$%,.2f", articulo.getPrecio()));
    }

    private void limpiarCamposArticulo()
    {
        menuCarrito.getTxtNombre().setText("");
        menuCarrito.getTxtDescripcion().setText("");
        menuCarrito.getTxtPrecio().setText("");
    }

    private void accionBtnSalir(ActionEvent e)
    {
        DialogUtils.quitarDialog(menuCarrito);
    }

}
