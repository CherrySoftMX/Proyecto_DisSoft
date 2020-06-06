package com.modelo.factory;

import com.enumeration.Comercio;
import com.modelo.tienda.GameStore;
import com.modelo.tienda.Libreria;
import com.modelo.tienda.Tienda;
import com.modelo.tienda.Zapateria;

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

    @Override public Tienda crearTienda(Comercio tipo)
    {
        switch (tipo)
        {
            case Libreria:
                return Libreria.getInstance();

            case Zapateria:
                return Zapateria.getInstance();

            case GameStore:
                return GameStore.getInstance();

            default:
                throw new AssertionError();
        }

    }

}
