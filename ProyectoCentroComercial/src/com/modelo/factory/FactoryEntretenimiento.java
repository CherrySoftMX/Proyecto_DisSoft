/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.factory;

import com.modelo.tienda.GamePlanet;
import com.modelo.tienda.PelisOutlet;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class FactoryEntretenimiento implements AbstracFactory
{

    private static FactoryEntretenimiento instance;

    public synchronized static FactoryEntretenimiento getInstance()
    {
        if (instance == null)
            instance = new FactoryEntretenimiento();

        return instance;
    }

    @Override
    public Tienda crearOutlet()
    {
        return new PelisOutlet();
    }

    @Override
    public Tienda crearFranquicia()
    {
        return new GamePlanet();
    }

}
