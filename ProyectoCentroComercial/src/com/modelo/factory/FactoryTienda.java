package com.modelo.factory;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public interface FactoryTienda
{

    public Tienda crearTienda(Tienda tipo);
}
