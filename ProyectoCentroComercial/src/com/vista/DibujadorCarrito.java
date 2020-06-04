/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;

import com.modelo.CarritoCompras;
import com.modelo.observer.Observador;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author emman
 */
public class DibujadorCarrito implements Observador {

    private final ImageIcon CARRITOENUSO = new ImageIcon("src\\com\\vista\\Images\\CarritoUsado.png");
    private final ImageIcon CARRITOLLENO = new ImageIcon("src\\com\\vista\\Images\\CarritoLleno.png");
    private final ImageIcon CARRITOVACIO = new ImageIcon("src\\com\\vista\\Images\\CarritoVacio.png");
    private final ImageIcon CARRITOCANCELADO = new ImageIcon("src\\com\\vista\\Images\\CarritoCancelado.png");

    private JPanel panel;
    private CarritoCompras carrito;

    public DibujadorCarrito(JPanel panel, CarritoCompras carrito) {
        this.panel = panel;
        this.carrito = carrito;

        actualizar();
    }

    @Override
    public void actualizar() {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        switch (carrito.getEstado()) {
            case "Carrito lleno":
                g.drawImage(CARRITOLLENO.getImage(), 0, 0, null);

                break;
            case "Carrito vacio":
                g.drawImage(CARRITOVACIO.getImage(), 0, 0, null);
                break;
            case "Carrito en uso":
                g.drawImage(CARRITOENUSO.getImage(), 0, 0, null);
                break;
            case "Carrito cancelado":
                g.drawImage(CARRITOCANCELADO.getImage(), 0, 0, null);
                break;
            default:
                throw new AssertionError();
        }
        g.dispose();

    }

}
