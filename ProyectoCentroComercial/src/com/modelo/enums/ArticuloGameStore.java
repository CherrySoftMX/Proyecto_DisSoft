package com.modelo.enums;

import com.modelo.ArticuloSencillo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author HikingCarrot7
 */
public enum ArticuloGameStore
{
    GEARS_OF_WAR("Gears of war", "Videojuego", "GOW", 300),
    GEARS_OF_WAR_2("Gears of war 2", "Videojuego", "GOW2", 300),
    GEARS_OF_WAR_3("Gears of war 3", "Videojuego", "GOW3", 400),
    GEARS_OF_WAR_4("Gears of war 4", "Videojuego", "GOW4", 700),
    GEARS_OF_WAR_5("Gears of war 5", "Videojuego", "GOW5", 1000),
    MINECRAFT("Minecraft", "Videojuego", "MIN", 500),
    GRAND_THEFT_AUTO_V("Grand Theft Auto V", "Videojuego", "GTA5", 700);

    private final String descripcion;
    private final String categoria;
    private final String id;
    private final double precio;

    private ArticuloGameStore(String descripcion, String categoria, String id, double precio)
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
