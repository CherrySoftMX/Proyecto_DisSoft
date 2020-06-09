package com.modelo.factory;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public interface FactoryTienda
{

    public Tienda crearTiendaEntretenimiento(String tipo);

    public Tienda crearTiendaVestimenta(String tipo);

    public Tienda crearTiendaLibreria(String tipo);
}
