package com.controladores;

import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.tienda.Tienda;
import com.vista.Menu;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author emman
 */
public class ControllerMenu {
    //Modificar el cliente actual de los otros controllers
    //Aun no se implementa los label correctamente
    //Nota los ENUM del profe no tiendn sentido;

    private Menu menu = new Menu();
    private CentroComercial centroCom;
    private Tienda tiendaActual;
    private Cliente clienteActual;
    private ControllerTienda contTienda;

    public ControllerMenu(CentroComercial centroCom) {
        this.centroCom = centroCom;
        iniciarListaClientes();
        iniciarListaTienda();
        initComponents();
    }

    public ControllerMenu(String nombre) {
        centroCom = new CentroComercial(nombre);
        iniciarListaClientes();
        iniciarListaTienda();
        initComponents();
    }

    public void iniciar() {
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    private void irATienda() {
        if (tiendaActual != null && contTienda == null) {

            clienteActual.setTiendaActual(tiendaActual);
            tiendaActual.entrar(clienteActual);

            contTienda = new ControllerTienda(tiendaActual);
            contTienda.setClienteActual(clienteActual);
            contTienda.iniciar();
        } else {
            tiendaActual.entrar(clienteActual);
            clienteActual.setTiendaActual(tiendaActual);
            contTienda.setTienda(tiendaActual);
            contTienda.setClienteActual(clienteActual);

            contTienda.iniciar();
        }
    }

    private void seleccionarTienda() {
        JList listaTienda = menu.getListTiendas();
        Tienda tienda = (Tienda) listaTienda.getSelectedValue();
        tiendaActual = tienda;
    }

    private void iniciarListaTienda() {
        int i = 0;
        JList lista = menu.getListTiendas();
        Enumeration<Tienda> listaTiendas = centroCom.listarTiendas();
        DefaultListModel model = new DefaultListModel();

        while (listaTiendas.hasMoreElements()) {
            Tienda nextElement = listaTiendas.nextElement();
            model.add(i++, nextElement);
        }

        lista.setModel(model);
    }

    private void iniciarListaClientes() {
        int i = 0;
        JList lista = menu.getListClientes();
        Enumeration<Cliente> listaClientes = centroCom.listarClientes();
        DefaultListModel model = new DefaultListModel();

        while (listaClientes.hasMoreElements()) {
            Cliente nextElement = listaClientes.nextElement();
            model.add(i++, nextElement);
        }

        lista.setModel(model);
    }

    private void selectCliente() {
        JList listaClientes = menu.getListClientes();
        Cliente cliente = (Cliente) listaClientes.getSelectedValue();
        clienteActual = cliente;
        menu.getLabelCliente().setText("Cliente: " + clienteActual.getNombre());
    }

    private void initComponents() {
        menu.getBtnIrTienda().addActionListener(this::handleSeleccionarTienda);
        menu.getBtnSelectCliente().addActionListener(this::handleSelecionarCliente);
        menu.getBtnGetCarrito().addActionListener(this::handleSolicitarCarrito);
//        menu.getBtnIrTienda().addActionListener(this);
//        menu.getBtnSelectCliente().addActionListener(this);
//        menu.getBtnGetCarrito().addActionListener(this);
    }

    private void solicitarCarrito() {
        clienteActual.setCarritoCompras(centroCom.getCarrito());
    }

    public void handleSeleccionarTienda(ActionEvent e) {
        seleccionarTienda();
        irATienda();
    }

    public void handleSelecionarCliente(ActionEvent e) {
        selectCliente();
    }

    public void handleSolicitarCarrito(ActionEvent e) {
        solicitarCarrito();
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        switch (e.getActionCommand()) {
//            case "ir a tienda":
//                seleccionarTienda();
//                irATienda();
//
//                break;
//            case "Seleccionar cliente":
//                selectCliente();
//                break;
//
//            case "Solicitar carrito":
//                solicitarCarrito();
//
//                break;
//            default:
//                throw new AssertionError();
//        }
//    }
}
