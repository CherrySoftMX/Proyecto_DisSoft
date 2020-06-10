/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.factory;

import com.modelo.tienda.Dante;
import com.modelo.tienda.OutletOficina;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class FactoryEscolar implements AbstracFactory
{

    private static FactoryEscolar instance;

    public static synchronized FactoryEscolar getInstance()
    {
        if (instance == null)
            instance = new FactoryEscolar();

        return instance;
    }

    private FactoryEscolar()
    {
    }

    @Override
    public Tienda crearOutlet()
    {
        return new OutletOficina();
    }

    @Override
    public Tienda crearFranquicia()
    {
        return new Dante();
    }

}
