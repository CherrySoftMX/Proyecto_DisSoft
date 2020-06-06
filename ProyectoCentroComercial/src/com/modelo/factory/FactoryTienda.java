package com.modelo.factory;

import com.enumeration.Comercio;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public interface FactoryTienda
{

    public Tienda crearTienda(Comercio tipo);
}
