package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoVacio implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito vacio";
    }

    @Override
    public String addArticulo(CarritoCompras carrito, Articulo articulo) {
        carrito.getArticulos().add(articulo);
        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
    }

    @Override
    public String removeArticulo(CarritoCompras carrito, Articulo articulo) {
        return manejar();
    }

    @Override
    public String cancelarCarrito(CarritoCompras carrito) {
        carrito.setEstado(new CarritoCancelado());
        return carrito.getEstado();
    }

}
