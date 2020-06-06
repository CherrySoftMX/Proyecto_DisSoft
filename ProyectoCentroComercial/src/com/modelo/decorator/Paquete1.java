package com.modelo.decorator;

import java.util.List;

/**
 *
 * @author emman
 */
public class Paquete1 extends PaqueteArticulo
{

    private double descuento;

    public Paquete1(List<Articulo> articulos, double descuento)
    {
        super(articulos);
        this.descuento = descuento;
    }

    @Override
    public String getNombre()
    {
        return "Paquete 1";
    }

    @Override
    public String getDescripcion()
    {
        return "Articulos: " + getArticulos().toString();
    }

    /**
     * Nota el descuento debe estar entre [0 - 1], ya debe ser un porcentaje convertido
     *
     * @return
     */
    @Override
    public double getPrecio()
    {
        double precioTotal = getPrecioTotal();
        return precioTotal - (descuento * precioTotal);
    }

    @Override
    public String toString()
    {
        return "Paquete1{" + getDescripcion() + "TOTAL: " + getPrecio();
    }

}
