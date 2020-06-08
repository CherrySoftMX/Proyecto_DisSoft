package com.modelo.enumeration;

/**
 *
 * @author emman
 */
public enum ArticuloZapateria
{

    Refill("Zapato masculino marca Refill", "Calzado masculino", "ZAPREFILL", 1200),
    Nike("Zapato femenino marca Nike", "Calzado femenino", "ZAPNIKE", 1800),
    Adidas("Zapato masculino marca Adidas", "Calzado masculino", "ZAPADIDAS", 1300),
    Converse("Zapato masculino marca Converse", "Calzado masculino", "ZAPCONVERSE", 1100),
    Fila("Tacones femeninos marca Fila", "Calzado femenino", "TACFILA", 1900);

    private final String descripcion;
    private final String categoria;
    private final String identificador;
    private final double precio;

    private ArticuloZapateria(String descripcion, String categoria, String identificador, double precio)
    {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.identificador = identificador;
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

    public String getIdentificador()
    {
        return identificador;
    }

    public double getPrecio()
    {
        return precio;
    }

}
