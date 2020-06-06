package com.modelo;

import com.modelo.decorator.Articulo;
import com.modelo.estado.CarritoCancelado;

/**
 *
 * @author HikingCarrot7
 */
public class CajaRegistradora
{

    private static CajaRegistradora instance;

    public static synchronized CajaRegistradora getInstance()
    {
        if (instance == null)
            instance = new CajaRegistradora();

        return instance;
    }

    private CajaRegistradora()
    {

    }

    public double getTotalCarrito(CarritoCompras carritoCompras)
    {
        double total = 0;

        for (Articulo articulo : carritoCompras.getArticulos())
            total += articulo.getPrecio();

        return total;
    }

    public void pagar(CarritoCompras carritoCompras)
    {
        carritoCompras.setEstado(new CarritoCancelado());
        System.out.println("Se ha pagado el carrito.");
    }

}
