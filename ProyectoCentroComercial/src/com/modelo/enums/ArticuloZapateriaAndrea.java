package com.modelo.enums;

import com.modelo.ArticuloSencillo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public enum ArticuloZapateriaAndrea
{

    ZAPATOS_REFILL("Zapato masculino marca Refill", "Calzado masculino", "ZAPREFILL", 1200),
    ZAPATOS_NIKE("Zapato femenino marca Nike", "Calzado femenino", "ZAPNIKE", 1800),
    ZAPATOS_ADIDAS("Zapato masculino marca Adidas", "Calzado masculino", "ZAPADIDAS", 1300),
    ZAPATOS_CONVERSE("Zapato masculino marca Converse", "Calzado masculino", "ZAPCONVERSE", 1100),
    ZAPATOS_FILA("Tacones femeninos marca Fila", "Calzado femenino", "TACFILA", 1900);

    private final String descripcion;
    private final String categoria;
    private final String id;
    private final double precio;

    private ArticuloZapateriaAndrea(String descripcion, String categoria, String id, double precio)
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
