package com.modelo.factory;

import com.modelo.enums.Negocio;
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

    @Override public Tienda crearTienda(Negocio tipo)
    {
        switch (tipo)
        {
            case Zapateria:
                return new Zapateria();
            case Libreria:
                return new Libreria();
            case GameStore:
                return new GameStore();
            default:
                throw new AssertionError();
        }
    }

}
