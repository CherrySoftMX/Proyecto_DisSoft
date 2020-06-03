package com.modelo.estado;

/**
 *
 * @author emman
 */
public class CarritoUsado implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito en uso";
    }

}
