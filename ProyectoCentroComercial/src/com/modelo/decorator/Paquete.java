package com.modelo.decorator;

import com.modelo.tienda.Tienda;
import java.util.List;

/**
 *
 * @author emman
 */
public abstract class Paquete extends Articulo
{

    private List<Articulo> articulos;
    private double descuento;

    public Paquete(String descripcion, String categoria, String identificador, Tienda tienda, List<Articulo> articulos, double descuento)
    {
        super(descripcion, categoria, identificador, tienda);
        this.articulos = articulos;
        this.descuento = descuento;
    }

    @Override
    public double getPrecio()
    {
        double precioTotal = 0;

        for (Articulo articulo : articulos)
            precioTotal += articulo.getPrecio();

        return precioTotal - (descuento * precioTotal);
    }

    public List<Articulo> getArticulos()
    {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos)
    {
        this.articulos = articulos;
    }

    public double getDescuento()
    {
        return descuento;
    }

    public void setDescuento(double descuento)
    {
        this.descuento = descuento;
    }

}
