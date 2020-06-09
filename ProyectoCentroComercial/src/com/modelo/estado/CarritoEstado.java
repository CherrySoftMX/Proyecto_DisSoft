package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public interface CarritoEstado
{

    public int ESTADO_LLENO = 0;
    public int ESTADO_USADO = 1;
    public int ESTADO_VACIO = 2;
    public int ESTADO_CANCELADO = 3;

    public int addArticulo(CarritoCompras carrito, Articulo articulo);

    public int removeArticulo(CarritoCompras carrito, Articulo articulo);

    public int cancelarCarrito(CarritoCompras carrito);

    public int manejar();
}
