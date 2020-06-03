package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public interface CarritoEstado {

    public String addArticulo(CarritoCompras carrito, Articulo articulo);

    public String removeArticulo(CarritoCompras carrito, Articulo articulo);

    public String cancelarCarrito(CarritoCompras carrito);

    public String manejar();
}
