package com.vista;

import com.modelo.estado.CarritoEstado;
import com.modelo.observer.Observador;
import javax.swing.JLabel;

/**
 *
 * @author HikingCarrot7
 */
public class DibujadorCarrito implements Observador, UIConstants
{

    private final JLabel lblCarrito;

    public DibujadorCarrito(JLabel lblCarrito)
    {
        this.lblCarrito = lblCarrito;
    }

    @Override
    public void actualizar(Object item)
    {
        if (item instanceof Integer)
        {
            int estadoCarrito = (Integer) item;

            switch (estadoCarrito)
            {
                case CarritoEstado.ESTADO_LLENO:
                    lblCarrito.setIcon(CARRITO_LLENO);
                    break;
                case CarritoEstado.ESTADO_VACIO:
                    lblCarrito.setIcon(CARRITO_VACIO);
                    break;
                case CarritoEstado.ESTADO_USADO:
                    lblCarrito.setIcon(CARRITO_USADO);
                    break;
                case CarritoEstado.ESTADO_CANCELADO:
                    lblCarrito.setIcon(CARRITO_CANCELADO);
                    break;
                default:
                    lblCarrito.setIcon(CARRITO_NO_EXISTENTE);
            }
        }
    }

}
