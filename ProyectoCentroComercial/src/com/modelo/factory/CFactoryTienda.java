package com.modelo.factory;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class CFactoryTienda implements FactoryTienda
{

    private static CFactoryTienda instance;

    public static synchronized CFactoryTienda getInstance()
    {
        if (instance == null)
            instance = new CFactoryTienda();

        return instance;
    }

    private CFactoryTienda()
    {

    }

    @Override public Tienda crearTienda(Tienda tipo)
    {
        return tipo;
    }

}
