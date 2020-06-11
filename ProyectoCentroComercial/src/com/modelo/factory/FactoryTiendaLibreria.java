package com.modelo.factory;

import com.modelo.tienda.Tienda;
import com.modelo.tienda.franquicia.LibreriaDante;
import com.modelo.tienda.outlet.LibreriaGandhi;

/**
 *
 * @author emman
 */
public class FactoryTiendaLibreria implements FactoryTienda
{

    private static FactoryTiendaLibreria instance;

    public static synchronized FactoryTiendaLibreria getInstance()
    {
        if (instance == null)
            instance = new FactoryTiendaLibreria();

        return instance;
    }

    private FactoryTiendaLibreria()
    {

    }

    @Override public Tienda crearOutlet()
    {
        return new LibreriaGandhi();
    }

    @Override public Tienda crearFranquicia()
    {
        return new LibreriaDante();
    }

}
