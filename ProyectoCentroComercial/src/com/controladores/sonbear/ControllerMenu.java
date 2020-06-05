package com.controladores.sonbear;

import com.modelo.CarritoCompras;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.tienda.Tienda;
import com.vista.sonbear.DibujadorCarrito;
import com.vista.sonbear.Menu;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author emman
 */
public class ControllerMenu
{
    //Modificar el cliente actual de los otros controllers
    //Aun no se implementa los label correctamente
    //Nota los ENUM del profe no tiendn sentido;

    private Menu menu = new Menu();
    private CentroComercial centroCom;
    private Tienda tiendaActual;
    private Cliente clienteActual;
    private ControllerTienda contTienda;
    private ControllerPago contPago;

    public ControllerMenu(CentroComercial centroCom)
    {
        this.centroCom = centroCom;
        iniciarListaClientes();
        iniciarListaTienda();
        initComponents();
    }

    public ControllerMenu(String nombre)
    {
        centroCom = new CentroComercial(nombre);
        iniciarListaClientes();
        iniciarListaTienda();
        initComponents();
    }

    public void iniciar()
    {
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    private void irATienda()
    {
        if (tiendaActual != null && contTienda == null)
        {
            clienteActual.setTiendaActual(tiendaActual);
            tiendaActual.entrar(clienteActual);

            contTienda = new ControllerTienda(tiendaActual);
            contTienda.setClienteActual(clienteActual);
            contTienda.iniciar();

        } else
        {
            tiendaActual.entrar(clienteActual);
            clienteActual.setTiendaActual(tiendaActual);
            contTienda.setTienda(tiendaActual);
            contTienda.setClienteActual(clienteActual);

            contTienda.iniciar();
        }
    }

    private void seleccionarTienda()
    {
        JList listaTienda = menu.getListTiendas();
        Tienda tienda = (Tienda) listaTienda.getSelectedValue();
        tiendaActual = tienda;
    }

    private void iniciarListaTienda()
    {
        int i = 0;
        JList lista = menu.getListTiendas();
        Enumeration<Tienda> listaTiendas = centroCom.listarTiendas();
        DefaultListModel model = new DefaultListModel();

        while (listaTiendas.hasMoreElements())
        {
            Tienda nextElement = listaTiendas.nextElement();
            model.add(i++, nextElement);
        }

        lista.setModel(model);
    }

    private void iniciarListaClientes()
    {
        int i = 0;
        JList lista = menu.getListClientes();
        Enumeration<Cliente> listaClientes = centroCom.listarClientes();
        DefaultListModel model = new DefaultListModel();

        while (listaClientes.hasMoreElements())
        {
            Cliente nextElement = listaClientes.nextElement();
            model.add(i++, nextElement);
        }

        lista.setModel(model);
    }

    private void selectCliente()
    {
        JList listaClientes = menu.getListClientes();
        Cliente cliente = (Cliente) listaClientes.getSelectedValue();
        clienteActual = cliente;
        menu.getLabelCliente().setText("Cliente: " + clienteActual.getNombre());

        if (clienteActual.getCarritoCompras() == null)
            panelCarrito.repaint();
        else
            clienteActual.getCarritoCompras().notificar(0);
    }

    private void initComponents()
    {
        menu.getBtnIrTienda().addActionListener(this::handleSeleccionarTienda);
        menu.getBtnSelectCliente().addActionListener(this::handleSelecionarCliente);
        menu.getBtnGetCarrito().addActionListener(this::handleSolicitarCarrito);

        menu.getBtnPagar().addActionListener(this::handlePagar);
    }

    private void pagar()
    {
        if (contPago == null)
        {
            contPago = new ControllerPago(clienteActual);
            contPago.iniciar();

        } else
        {
            contPago.setCliente(clienteActual);
            contPago.iniciar();
        }

    }

    //--------------------------------------------------------------------------------------------------------|
    /**
     * Esto sirve para dibujar el carrito en la pantalla, igual utiliza el patron observador para actualizarse
     */
    private JPanel panelCarrito = menu.getPanelCarrito();
    private DibujadorCarrito dib;

    private void solicitarCarrito()
    {
        CarritoCompras carrito = centroCom.getCarrito();
        clienteActual.setCarritoCompras(carrito);
        dib = new DibujadorCarrito(panelCarrito, clienteActual.getCarritoCompras());

        carrito.anadirObservador(dib);
    }
    //-----------------------------------------------------------------------------------------------------------|

    public void handleSeleccionarTienda(ActionEvent e)
    {
        seleccionarTienda();
        irATienda();
    }

    public void handlePagar(ActionEvent e)
    {
        pagar();
    }

    public void handleSelecionarCliente(ActionEvent e)
    {
        selectCliente();
    }

    public void handleSolicitarCarrito(ActionEvent e)
    {
        solicitarCarrito();
    }

}
