package com.controladores;

import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.tienda.Tienda;
import com.vista.MenuTienda;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author emman
 */
public class ControllerTienda
{

    private MenuTienda menu = new MenuTienda();
    private ControllerCarrito contCarrito;
    private ControllerPago contPago;
    private Tienda tienda;
    private Cliente clienteActual = null;

    public ControllerTienda(Tienda tienda)
    {
        this.tienda = tienda;
        initComponents();
        iniciarListaArticulos();
        iniciarListaClientes();
    }

    public void iniciar()
    {
        menu.getLabelTienda().setText("Tienda: " + tienda);
        menu.setAlwaysOnTop(true);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    private void initComponents()
    {
        menu.getBtnAgregarCarrito().addActionListener(this::handleAgregarCarrito);
        menu.getBtnMostrarCarrito().addActionListener(this::handleMostrarCarrito);
        menu.getBtnComprar().addActionListener(this::handleComprar);
        menu.getBtnSelectCliente().addActionListener(this::handleSeleccionarCliente);
        menu.getBtnSalir().addActionListener(this::handleSalir);

    }

    public void iniciarListaArticulos()
    {
        JList listaTienda = menu.getListArticulos();
        List<Articulo> listaArticulos = tienda.getArticulos();
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < listaArticulos.size(); i++)
            model.add(i, listaArticulos.get(i));

        listaTienda.setModel(model);
    }

    public void iniciarListaClientes()
    {
        JList listaTienda = menu.getListClientes();
        List<Cliente> listaCliente = tienda.getClientes();
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < listaCliente.size(); i++)
            model.add(i, listaCliente.get(i));

        listaTienda.setModel(model);
    }

    private void agregarAlCarrito()
    {
        JList listaTienda = menu.getListArticulos();
        Articulo articulo = (Articulo) listaTienda.getSelectedValue();
        tienda.agregarAlCarrito(clienteActual.getCarritoCompras(), articulo);
    }

    private void cambiarClienteActual()
    {
        JList listaClientes = menu.getListClientes();
        Cliente cliente = (Cliente) listaClientes.getSelectedValue();
        clienteActual = cliente;
        menu.getLabelCliente().setText("Cliente: \n" + clienteActual.getNombre());
    }

    private void mostrarCarrito()
    {
        if (contCarrito == null && clienteActual != null)
        {
            contCarrito = new ControllerCarrito(clienteActual);
            contCarrito.iniciar();

        } else
        {
            contCarrito.setCliente(clienteActual);
            contCarrito.iniciar();
        }

    }

    private void mostrarPago()
    {
        if (clienteActual == null)
            return;

        if (contPago == null && clienteActual != null)
        {
            contPago = new ControllerPago(clienteActual);
            contPago.iniciar();

        } else
        {
            contPago.setCliente(clienteActual);
            contPago.iniciar();
        }
    }

    public void handleAgregarCarrito(ActionEvent e)
    {
        agregarAlCarrito();
    }

    public void handleMostrarCarrito(ActionEvent e)
    {
        mostrarCarrito();
    }

    public void handleSeleccionarCliente(ActionEvent e)
    {
        cambiarClienteActual();
    }

    public void handleComprar(ActionEvent e)
    {
        mostrarPago();
    }

    public void handleSalir(ActionEvent e)
    {
        tienda.salir(clienteActual);
        menu.dispose();
    }

    public void setClienteActual(Cliente clienteActual)
    {
        this.clienteActual = clienteActual;
        menu.getLabelCliente().setText("Cliente: \n" + clienteActual.getNombre());
    }

    public void setTienda(Tienda tienda)
    {
        this.tienda = tienda;
        iniciarListaArticulos();
        iniciarListaClientes();
        clienteActual = null;
        menu.getLabelCliente().setText("Cliente Actual: ");
    }

    public Tienda getTienda()
    {
        return tienda;
    }

}
