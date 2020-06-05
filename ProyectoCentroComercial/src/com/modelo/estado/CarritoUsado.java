package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoUsado implements CarritoEstado
{

    @Override
    public int addArticulo(CarritoCompras carrito, Articulo articulo)
    {
        carrito.getArticulos().add(articulo);

        if (carrito.getArticulos().size() >= CarritoCompras.MAX_CAPACIDAD)
        {
            carrito.setEstado(new CarritoLleno());
            return carrito.getEstado();
        }

        return carrito.getEstado();
    }

    @Override
    public int removeArticulo(CarritoCompras carrito, Articulo articulo)
    {
        carrito.getArticulos().remove(articulo);

        if (carrito.estaVacio())
        {
            carrito.setEstado(new CarritoVacio());
            return carrito.getEstado();
        }

        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
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
        return ESTADO_USADO;
    }

}
