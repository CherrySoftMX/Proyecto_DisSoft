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
    public int addArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public int removeArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public int cancelarCarrito(CarritoCompras carrito)
    {
        return manejar();
    }

    @Override
    public int manejar()
    {
        return ESTADO_CANCELADO;
    }

}
