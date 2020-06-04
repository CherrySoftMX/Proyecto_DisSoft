package com.modelo.estado;

import com.modelo.CarritoCompras;
import com.modelo.decorator.Articulo;

/**
 *
 * @author emman
 */
public class CarritoUsado implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito en uso";
    }

    @Override
    public String addArticulo(CarritoCompras carrito, Articulo articulo) {
        carrito.getArticulos().add(articulo);
        if (carrito.getArticulos().size() >= CarritoCompras.CAPACIDAD) {
            carrito.setEstado(new CarritoLleno());
            return carrito.getEstado();
        }

        return carrito.getEstado();
    }

    @Override
    public String removeArticulo(CarritoCompras carrito, Articulo articulo) {
        carrito.getArticulos().remove(articulo);
        if (carrito.getArticulos().size() == 0) {
            carrito.setEstado(new CarritoVacio());
            return carrito.getEstado();
        }
        carrito.setEstado(new CarritoUsado());
        return carrito.getEstado();
    }

    @Override
    public String cancelarCarrito(CarritoCompras carrito) {
        carrito.setEstado(new CarritoCancelado());
        return carrito.getEstado();
    }

}
