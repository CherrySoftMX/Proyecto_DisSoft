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
    public String descripcion()
    {
        return "Articulos: " + getArticulos().toString();
    }

    private double sumar()
    {
        List<Articulo> ar = getArticulos();
        double total = 0;

        for (int i = 0; i < ar.size(); i++)
            total += ar.get(i).precio();

        return total;
    }

    //Por cada total de los articulos se resta un 15% del total
    @Override
    public double precio()
    {
        double suma = sumar();
        return suma - (descuento * suma);
    }

    @Override
    public String toString()
    {
        return "Paquete1{" + descripcion() + "TOTAL: " + precio();
    }

}
