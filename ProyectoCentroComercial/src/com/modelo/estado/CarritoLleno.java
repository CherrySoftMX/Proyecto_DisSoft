package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoLleno implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito lleno";
    }

    @Override
    public String addArticulo(CarritoCompras carrito, Articulo articulo) {
        return manejar();
    }

    @Override
    public String removeArticulo(CarritoCompras carrito, Articulo articulo) {

        if (carrito.getArticulos().size() == 0) {
            carrito.setEstado(new CarritoVacio());
            return carrito.getEstado();
        }
        carrito.getArticulos().remove(articulo);
        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
    }

    @Override
    public String cancelarCarrito(CarritoCompras carrito) {
        carrito.setEstado(new CarritoCancelado());
        return carrito.getEstado();
    }

}
