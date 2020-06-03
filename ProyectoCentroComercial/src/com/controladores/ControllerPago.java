package com.controladores;

import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.vista.MenuPago;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author emman
 */
public class ControllerPago {

    private final MenuPago menu = new MenuPago();
    private Cliente cliente;

    public ControllerPago(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        iniciarLista();
        setTotal();
    }

    public void iniciar() {
        menu.getLabelCliente().setText("Cliente: " + cliente.getNombre());
        menu.setLocationRelativeTo(null);
        menu.setAlwaysOnTop(true);
        menu.setVisible(true);
    }

    public void handleRealizarCompra(ActionEvent e) {
        menu.mensaje(cliente.pagarCarrito());
    }

    private void setTotal() {
        double total = 0;
        List<Articulo> listaArticulos = cliente.getCarritoCompras().getArticulos();

        for (int i = 0; i < listaArticulos.size(); i++) {
            total += listaArticulos.get(i).precio();
        }

        menu.getLabelTotal().setText("$ " + total);
    }

    private void iniciarLista() {

        int i = 0;
        JList listaCarrito = menu.getListaArticulos();
        Enumeration<Articulo> listaArticulos = cliente.getCarritoCompras().listarArticulos();
        DefaultListModel model = new DefaultListModel();

        while (listaArticulos.hasMoreElements()) {
            Articulo nextElement = listaArticulos.nextElement();
            model.add(i++, nextElement);
        }

        listaCarrito.setModel(model);
    }

    private void initComponents() {
        menu.getBtnRealizar().addActionListener(this::handleRealizarCompra);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        iniciarLista();
        setTotal();
    }

}
