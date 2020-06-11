package com.modelo.factory;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public interface FactoryTienda
{

    public Tienda crearOutlet();

    public Tienda crearFranquicia();
}
