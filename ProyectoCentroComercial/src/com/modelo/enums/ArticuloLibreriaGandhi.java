package com.modelo.enums;

import com.modelo.ArticuloSencillo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public enum ArticuloLibreriaGandhi
{
    ALGEBRA_INTERMEDIA("Álgebra intermedia", "Libro de matemáticas", "ALGINTER", 200),
    ALGEBRA_SUPERIOR("Álgebra superior", "Libro de matemáticas", "ALGSUP", 300),
    ALGEBRA_LINEAL("Álgebra lineal", "Libro de matemáticas", "ALGLIN", 400),
    MATEMATICAS_DISCRETAS("Matemáticas discretas", "Libro de matemáticas", "MATDISC", 350),
    CALCULO_DIFERENCIAL("Cálculo diferencial", "Libro de matemáticas", "CALDIF", 150),
    CALCULO_INTEGRAL("Cálculo integral", "Libro de matemáticas", "CALINT", 220),
    CALCULO_VECTORIAL("Cálculo vectorial", "Libro de matemáticas", "CALVEC", 280),
    PROBABILIDAD("Probabilidad", "Libro de matemáticas", "PROB", 180),
    INFERENCIA_ESTADISTICA("Inferencia estadística", "Libro de matemáticas", "INFEST", 550);

    private final String descripcion;
    private final String categoria;
    private final String id;
    private final double precio;

    private ArticuloLibreriaGandhi(String descripcion, String categoria, String id, double precio)
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
