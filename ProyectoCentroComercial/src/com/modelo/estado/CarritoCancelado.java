package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoCancelado implements CarritoEstado
{

    @Override
    public String manejar()
    {
        return "Carrito cancelado";
    }

    @Override
    public String addArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public String removeArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public String cancelarCarrito(CarritoCompras carrito)
    {
        return manejar();
    }

}
