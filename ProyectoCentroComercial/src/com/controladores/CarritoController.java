package com.controladores;

import com.controladores.util.AccionEmergente;
import com.controladores.util.Alerta;
import com.controladores.util.DialogUtils;
import com.controladores.util.PopupMenuAdapter;
import com.controladores.util.TableManager;
import com.modelo.CajaRegistradora;
import com.modelo.CarritoCompras;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.PaqueteArticulo;
import com.vista.DibujadorCarrito;
import com.vista.MenuCarrito;
import com.vista.UIConstants;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.List;
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
    private final Cliente cliente;
    private final TableManager tableManager;
    private final CajaRegistradora cajaRegistradora;
    private final DibujadorCarrito dibujadorCarrito;

    private int[] idxSeleccionados;

    public CarritoController(MenuCarrito menuCarrito, Cliente cliente)
    {
        this.menuCarrito = menuCarrito;
        this.cliente = cliente;
        this.tableManager = TableManager.getInstance();
        this.dibujadorCarrito = new DibujadorCarrito(menuCarrito.getLblCarrito());
        this.cajaRegistradora = CajaRegistradora.getInstance();
        initComponets();
        initListaArticulos();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponets()
    {
        menuCarrito.getBtnEliminarDelCarrito().addActionListener(this::accionBtnEliminarDelCarrito);
        menuCarrito.getBtnSalir().addActionListener(this::accionBtnSalir);
        actualizarPrecioCarrito();

        CarritoCompras carritoCompras = cliente.getCarritoCompras();

        carritoCompras.anadirObservador(dibujadorCarrito);
        carritoCompras.notificar(carritoCompras.getEstado());
        initTabla();
        initPopupMenus();
    }

    private void initTabla()
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        tabla.getSelectionModel().addListSelectionListener(this::accionClicTablaArticulosCarrito);
        tabla.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);

        tableManager.initTabla(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);

        tabla.getSelectionModel().addListSelectionListener(e ->
        {
            int[] indices = tableManager.getFilasSeleccionadas(tabla);

            if (indices.length != 0)
                idxSeleccionados = indices;

            habilitarBtnEliminarArticulos(true);
        });

        habilitarBtnEliminarArticulos(false);
    }

    private void initPopupMenus()
    {
        // Inicializamos el popupmenu de la tabla de los artículos del carrito.
        final JTable tablaArticulos = menuCarrito.getTablaArticulosCarrito();
        JPopupMenu popupMenuTablaArticulos = new JPopupMenu();

        popupMenuTablaArticulos.add(new JMenuItem(new AccionEmergente("Eliminar del carrito", DELETE_ICON, this::accionPopupMenuEliminarDelCarrito)));
        JMenuItem paqueteMenuItem = new JMenuItem(new AccionEmergente("Ver detalles del paquete", PAQUETE_ICON, this::accionPopupMenuVerDetallesPaquete));
        popupMenuTablaArticulos.add(paqueteMenuItem);

        popupMenuTablaArticulos.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                int rowClicked = tableManager.getRowClicked(tablaArticulos, tablaArticulos.getMousePosition().getY());
                tableManager.selecionarFila(tablaArticulos, rowClicked);
                paqueteMenuItem.setEnabled(cliente.getCarritoCompras().getArticulo(rowClicked) instanceof PaqueteArticulo);
            }
        });

        tablaArticulos.setComponentPopupMenu(popupMenuTablaArticulos);
    }
    //</editor-fold>

    private void initListaArticulos()
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        Enumeration<Articulo> articulos = cliente.getCarritoCompras().listarArticulos();

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
        if (Alerta.mostrarConfirmacion(menuCarrito, "Confirmación",
                String.format("Se eliminarán %d artículo(s) del carrito.", idxSeleccionados.length)))
        {
            for (int i = idxSeleccionados.length - 1; i >= 0; i--)
                eliminarArticuloDelCarrito(cliente.getCarritoCompras().getArticulo(idxSeleccionados[i]));

            actualizarPrecioCarrito();
        }
    }

    public void accionPopupMenuEliminarDelCarrito(ActionEvent e)
    {
        JTable tablaCarrito = menuCarrito.getTablaArticulosCarrito();
        eliminarArticuloDelCarrito(cliente.getCarritoCompras().getArticulo(tablaCarrito.getSelectedRow()));
        actualizarPrecioCarrito();
    }

    private void accionClicTablaArticulosCarrito(ListSelectionEvent e)
    {
        JTable tabla = menuCarrito.getTablaArticulosCarrito();
        int selectedRow = tabla.getSelectedRow();

        if (selectedRow >= 0)
        {
            Articulo articuloSeleccionado = cliente.getCarritoCompras().getArticulo(selectedRow);
            actualizarCamposArticulo(articuloSeleccionado);
            habilitarBtnEliminarArticulos(true);

        } else
        {
            limpiarCamposArticulo();
            habilitarBtnEliminarArticulos(false);
        }
    }

    public void accionPopupMenuVerDetallesPaquete(ActionEvent e)
    {

    }

    private void eliminarArticuloDelCarrito(Articulo articulo)
    {
        CarritoCompras carrito = cliente.getCarritoCompras();
        List<Articulo> articulos = carrito.getArticulos();
        tableManager.eliminarFila(menuCarrito.getTablaArticulosCarrito(), articulos.indexOf(articulo));
        carrito.eliminarArticulo(articulo);

        if (cliente.getCarritoCompras().estaVacio())
            DialogUtils.quitarDialog(menuCarrito);
    }

    private void accionBtnSalir(ActionEvent e)
    {
        DialogUtils.quitarDialog(menuCarrito);
    }

    private void actualizarPrecioCarrito()
    {
        menuCarrito.getLblTotal().setText(String.format("Total: $%,.2f", cajaRegistradora.getTotalCarrito(cliente.getCarritoCompras())));
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

    private void habilitarBtnEliminarArticulos(boolean habilitar)
    {
        menuCarrito.getBtnEliminarDelCarrito().setEnabled(habilitar);
    }

}
