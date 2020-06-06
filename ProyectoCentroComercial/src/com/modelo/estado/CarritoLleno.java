package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoLleno implements CarritoEstado
{

    @Override
    public int manejar()
    {
        return ESTADO_LLENO;
    }

    @Override
    public int addArticulo(CarritoCompras carrito, Articulo articulo)
    {
        return manejar();
    }

    @Override
    public int removeArticulo(CarritoCompras carrito, Articulo articulo)
    {
        if (carrito.estaVacio())
        {
            carrito.setEstado(new CarritoVacio());
            return carrito.getEstado();
        }

        carrito.getArticulos().remove(articulo);
        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
    }

    @Override
    public int cancelarCarrito(CarritoCompras carrito)
    {
        carrito.setEstado(new CarritoCancelado());
        return carrito.getEstado();
    }

}
