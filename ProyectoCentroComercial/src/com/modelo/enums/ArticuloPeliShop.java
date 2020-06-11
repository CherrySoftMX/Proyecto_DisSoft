package com.modelo.enums;

import com.modelo.ArticuloSencillo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author HikingCarrot7
 */
public enum ArticuloPeliShop
{
    RAPIDOS_Y_FURIOSOS("Rápidos y furiosos", "Pilicula de acción", "RAPFUR", 80),
    RAPIDOS_Y_FURIOSOS_2("Rápidos y furiosos 2", "Pilicula de acción", "RAPFUR2", 90),
    RAPIDOS_Y_FURIOSOS_3("Rápidos y furiosos 3", "Pilicula de acción", "RAPFUR3", 100),
    RAPIDOS_Y_FURIOSOS_4("Rápidos y furiosos 4", "Pilicula de acción", "RAPFUR4", 110),
    RAPIDOS_Y_FURIOSOS_5("Rápidos y furiosos 5", "Pilicula de acción", "RAPFUR5", 120),
    RAPIDOS_Y_FURIOSOS_6("Rápidos y furiosos 6", "Pilicula de acción", "RAPFUR6", 130),
    RAPIDOS_Y_FURIOSOS_7("Rápidos y furiosos 7", "Pilicula de acción", "RAPFUR7", 140);

    private final String descripcion;
    private final String categoria;
    private final String id;
    private final double precio;

    private ArticuloPeliShop(String descripcion, String categoria, String id, double precio)
    {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.id = id;
        this.precio = precio;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public String getId()
    {
        return id;
    }

    public double getPrecio()
    {
        return precio;
    }

    public ArticuloSencillo getAsArticuloSencillo(Tienda tienda)
    {
        return new ArticuloSencillo(descripcion, categoria, id, tienda, precio);
    }
}
