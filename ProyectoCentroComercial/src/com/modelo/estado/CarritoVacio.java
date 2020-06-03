package com.modelo.estado;

/**
 *
 * @author emman
 */
public class CarritoVacio implements CarritoEstado {

    @Override
    public String manejar() {
        return "Carrito vacio";
    }

}
