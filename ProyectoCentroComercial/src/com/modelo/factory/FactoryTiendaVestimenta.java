package com.modelo.factory;

import com.modelo.tienda.Tienda;
import com.modelo.tienda.franquicia.ZapateriaAndrea;
import com.modelo.tienda.outlet.TiendaSHEIN;

/**
 *
 * @author HikingCarrot7
 */
public class FactoryTiendaVestimenta implements FactoryTienda
{

    private static FactoryTiendaVestimenta instance;

    public static synchronized FactoryTiendaVestimenta getInstance()
    {
        if (instance == null)
            instance = new FactoryTiendaVestimenta();

        return instance;
    }

    private FactoryTiendaVestimenta()
    {

    }

    @Override public Tienda crearOutlet()
    {
        return new TiendaSHEIN();
    }

    @Override public Tienda crearFranquicia()
    {
        return new ZapateriaAndrea();
    }

}
