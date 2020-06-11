package com.modelo.factory;

import com.modelo.tienda.franquicia.GamePlanet;
import com.modelo.tienda.outlet.PelisPlus;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class FactoryTiendaEntretenimiento implements FactoryTienda
{

    private static FactoryTiendaEntretenimiento instance;

    public synchronized static FactoryTiendaEntretenimiento getInstance()
    {
        if (instance == null)
            instance = new FactoryTiendaEntretenimiento();

        return instance;
    }

    @Override public Tienda crearOutlet()
    {
        return new PelisPlus();
    }

    @Override public Tienda crearFranquicia()
    {
        return new GamePlanet();
    }

}
