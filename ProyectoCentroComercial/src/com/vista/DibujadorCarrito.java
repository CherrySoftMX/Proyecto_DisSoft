package com.vista;

import com.modelo.CarritoCompras;
import com.modelo.observer.Observador;
import com.vista2.UIConstants;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author emman
 */
public class DibujadorCarrito implements Observador, UIConstants
{

    private JPanel panel;
    private CarritoCompras carrito;

    public DibujadorCarrito(JPanel panel, CarritoCompras carrito)
    {
        this.panel = panel;
        this.carrito = carrito;

        actualizar();
    }

    @Override
    public void actualizar()
    {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        switch (carrito.getEstado())
        {
            case "Carrito lleno":
                g.drawImage(CARRITO_LLENO.getImage(), 0, 0, null);

                break;
            case "Carrito vacio":
                g.drawImage(CARRITO_VACIO.getImage(), 0, 0, null);
                break;
            case "Carrito en uso":
                g.drawImage(CARRITO_EN_USO.getImage(), 0, 0, null);
                break;
            case "Carrito cancelado":
                g.drawImage(CARRITO_CANCELADO.getImage(), 0, 0, null);
                break;
            default:
                throw new AssertionError();
        }

        g.dispose();
    }

}
