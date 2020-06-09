package com.modelo.factory;

import com.modelo.tienda.Libreria;
import com.modelo.tienda.Tienda;
import com.modelo.tienda.TiendaEntretenimiento;
import com.modelo.tienda.TiendaRopa;
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

    @Override public Tienda crearTiendaEntretenimiento(String tipo)
    {
        switch (tipo)
        {
            case Tienda.GAMESTORE:
                return new TiendaEntretenimiento("GameStore", "GS123");
            case Tienda.PELISHOP:
                return new TiendaEntretenimiento("PeliShop", "PS674");
            default:
                throw new AssertionError();
        }
    }

    @Override public Tienda crearTiendaVestimenta(String tipo)
    {
        switch (tipo)
        {
            case Tienda.ZAPATERIA_ANDREA:
                return new Zapateria("Zapatería Andrea", "ZAPAND178");
            case Tienda.TIENDA_SHE_IN:
                return new TiendaRopa("Tienda SHEIN", "SHE78");
            default:
                throw new AssertionError();
        }
    }

    @Override public Tienda crearTiendaLibreria(String tipo)
    {
        switch (tipo)
        {
            case Tienda.LIBRERIA_DANTE:
                return new Libreria("Librería Dante", "LIB674");
            case Tienda.LIBRERIA_GANDHI:
                return new Libreria("Librería Gandhi", "LIB092");
            default:
                throw new AssertionError();
        }
    }

}
