package com.modelo.enums;

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
    private final String identificador;
    private final double precio;

    private ArticuloZapateriaAndrea(String descripcion, String categoria, String identificador, double precio)
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
