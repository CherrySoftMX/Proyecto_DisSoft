package com.controladores.sonbear;

import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.vista.sonbear.MenuCarrito;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author emman
 */
public class ControllerCarrito
{

    private final MenuCarrito menu = new MenuCarrito();
    private Cliente cliente;

    public ControllerCarrito(Cliente cliente)
    {
        this.cliente = cliente;
        initComponents();
        iniciarLista();
    }

    public void iniciar()
    {
        menu.getLabelCliente().setText("Cliente: " + cliente.getNombre());
        menu.setAlwaysOnTop(true);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    private void iniciarLista()
    {
        int i = 0;
        JList listaCarrito = menu.getListaArticulos();
        Enumeration<Articulo> listaArticulos = cliente.getCarritoCompras().listarArticulos();
        DefaultListModel model = new DefaultListModel();

        while (listaArticulos.hasMoreElements())
        {
            Articulo nextElement = listaArticulos.nextElement();
            model.add(i++, nextElement);
        }

        listaCarrito.setModel(model);
    }

    private void initComponents()
    {
        menu.getBtnEliminar().addActionListener(this::handleEliminar);
        menu.getBtnInspeccionar().addActionListener(this::handleInspeccionar);
        menu.getBtnSalir().addActionListener(this::handleSalir);

    }

    public void handleInspeccionar(ActionEvent e)
    {
        inspeccionar();
    }

    public void handleEliminar(ActionEvent e)
    {
        eliminar();
    }

    public void handleSalir(ActionEvent e)
    {
        menu.dispose();
    }

    private void inspeccionar()
    {
        JList listaCarrito = menu.getListaArticulos();
        Articulo articulo = (Articulo) listaCarrito.getSelectedValue();
        menu.getLabelNombre().setText("Nombre: " + articulo.toString());
        menu.getLabelDesc().setText("Descripcion: " + articulo.getCategoria());
    }

    private void eliminar()
    {
        JList listaCarrito = menu.getListaArticulos();
        Articulo articulo = (Articulo) listaCarrito.getSelectedValue();
        cliente.getCarritoCompras().eliminarArticulo(articulo);
        iniciarLista();
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
        iniciarLista();
    }

}
