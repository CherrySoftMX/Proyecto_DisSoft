package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoVacio implements CarritoEstado
{

    @Override
    public int addArticulo(CarritoCompras carrito, Articulo articulo)
    {
        carrito.getArticulos().add(articulo);
        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
    }

    @Override
    public int removeArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public int cancelarCarrito(CarritoCompras carrito)
    {
        carrito.setEstado(new CarritoCancelado());
        return carrito.getEstado();
    }

    @Override
    public int manejar()
    {
        return ESTADO_VACIO;
    }

}
