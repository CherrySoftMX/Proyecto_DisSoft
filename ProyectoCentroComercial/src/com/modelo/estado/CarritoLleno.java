package com.modelo.estado;

/**
 *
 * @author emman
 */
public class CarritoLleno implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito Lleno";
    }

}
