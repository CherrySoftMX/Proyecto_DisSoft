package com.modelo.estado;

/**
 *
 * @author emman
 */
public class CarritoCancelado implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito cancelado";
    }

}
