package com.modelo;

import com.modelo.decorator.Articulo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class ArticuloSencillo extends Articulo
{

    private double precio;

    public ArticuloSencillo(String descripcion, String categoria, String identificador, Tienda tienda, double precio)
    {
        super(descripcion, categoria, identificador, tienda);
        this.precio = precio;
    }

    @Override
    public double getPrecio()
    {
        return precio;
    }

}
