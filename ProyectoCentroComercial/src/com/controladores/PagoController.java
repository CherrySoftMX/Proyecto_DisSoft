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
import com.modelo.observer.Observado;
import com.vista.MenuPago;
import com.vista.UIConstants;
import com.vista.VistaDetallesPaquete;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author HikingCarrot7
 */
public class PagoController extends Observado implements UIConstants
{

    private final MenuPago menuPago;
    private final Cliente cliente;
    private final CajaRegistradora cajaRegistradora;
    private final TableManager tableManager;

    public PagoController(MenuPago menuPago, Cliente cliente)
    {
        this.menuPago = menuPago;
        this.cliente = cliente;
        this.cajaRegistradora = CajaRegistradora.getInstance();
        this.tableManager = TableManager.getInstance();
        initComponents();
        initListaArticulos();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponents()
    {
        menuPago.getBtnRealizarPago().addActionListener(this::accionBtnRealizarPago);
        menuPago.getBtnCancelar().addActionListener(this::accionBtnCancelar);

        menuPago.getTxtCliente().setText(cliente.getNombre());
        actualizarPrecioCarrito();

        JTable tabla = menuPago.getTablaArticulosCarrito();
        tableManager.initTable(tabla);
        tableManager.initTableSelectionBehavior(tabla, DEFAULT_FOCUS_LOST_BEHAVIOR);
        tabla.getColumnModel().getColumn(0).setCellRenderer(ARTICULO_COLUMNA_RENDERER);

        initPopupMenu();
    }

    private void initPopupMenu()
    {
        // Inicializamos el popupmenu de la tabla de los artículos del carrito.
        final JTable tablaArticulos = menuPago.getTablaArticulosCarrito();
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
        JTable tabla = menuPago.getTablaArticulosCarrito();
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

    private void accionBtnRealizarPago(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(menuPago, "Está a punto de realizar una compra de " + getTotalCarrito()))
        {
            cajaRegistradora.pagar(cliente.getCarritoCompras());
            DialogUtils.quitarDialog(menuPago);
            notificar();
        }
    }

    public void accionPopupMenuEliminarDelCarrito(ActionEvent e)
    {
        JTable tablaCarrito = menuPago.getTablaArticulosCarrito();
        eliminarArticuloDelCarrito(cliente.getCarritoCompras().getArticulo(tablaCarrito.getSelectedRow()));
        actualizarPrecioCarrito();
    }

    public void accionPopupMenuVerDetallesPaquete(ActionEvent e)
    {
        JTable tablaArticulos = menuPago.getTablaArticulosCarrito();
        VistaDetallesPaquete vistaDetallesPaquete = new VistaDetallesPaquete(menuPago);

        new DetallesPaqueteController(vistaDetallesPaquete,
                (PaqueteArticulo) cliente.getCarritoCompras().getArticulo(tablaArticulos.getSelectedRow()));

        DialogUtils.showDialogAndWait(menuPago, vistaDetallesPaquete);
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        DialogUtils.quitarDialog(menuPago);
    }

    private void eliminarArticuloDelCarrito(Articulo articulo)
    {
        CarritoCompras carrito = cliente.getCarritoCompras();
        List<Articulo> articulos = carrito.getArticulos();
        tableManager.eliminarFila(menuPago.getTablaArticulosCarrito(), articulos.indexOf(articulo));
        carrito.eliminarArticulo(articulo);

        if (cliente.getCarritoCompras().estaVacio())
            DialogUtils.quitarDialog(menuPago);
    }

    private String getTotalCarrito()
    {
        return menuPago.getTxtTotal().getText();
    }

    private void actualizarPrecioCarrito()
    {
        menuPago.getTxtTotal().setText(String.format("$%,.2f", cajaRegistradora.getTotalCarrito(cliente.getCarritoCompras())));
    }

}
