package com.modelo.decorator;

import com.modelo.tienda.Tienda;
import java.util.List;

/**
 *
 * @author emman
 */
public abstract class PaqueteArticulo extends Articulo
{

    private List<Articulo> articulos;
    private double descuento;

    public PaqueteArticulo(String descripcion, String categoria, String identificador, Tienda tienda, List<Articulo> articulos, double descuento)
    {
        super(descripcion, categoria, identificador, tienda);
        this.articulos = articulos;
        this.descuento = descuento;
    }

    @Override
    public double getPrecio()
    {
        double precioTotal = getPrecioTotal();
        return precioTotal - (descuento * precioTotal);
    }

    public double getPrecioTotal()
    {
        double total = 0;

        for (Articulo articulo : articulos)
            total += articulo.getPrecio();

        return total;
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
