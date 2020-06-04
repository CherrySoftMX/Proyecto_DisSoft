package com.modelo.factory;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public interface Factory {

    public Tienda crearTienda(String tipo);
}
