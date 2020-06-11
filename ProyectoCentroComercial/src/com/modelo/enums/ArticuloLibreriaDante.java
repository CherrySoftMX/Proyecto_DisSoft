package com.modelo.enums;

import com.modelo.ArticuloSencillo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author HikingCarrot7
 */
public enum ArticuloLibreriaDante
{
    WIGETTA("Wigetta", "Cuentos para niños", "WIG", 200),
    WIGETTA_Y_BABYREX("Wigetta, Viegetta y babyrex", "Cuentos para niños", "WIGVIE", 210),
    WIGETTA_Y_TROTUMAN("Wigetta y el mundo de trotuman", "Cuentos para niños", "WIGTRO", 150),
    WIGETTA_Y_MOMIA("Wigetta y al momia de Ra-mon", "Cuentos para niños", "WINMOM", 400),
    WIGETTA_Y_CHOCATUSPALMAS("Wigetta y el tesoro de chocatuspalmas", "Cuentos para niños", "WIGCHOCA", 150),
    WIGETTA_Y_EL_CUERPO_HUMANO("Wigetta y los secretos del cuerpo humano", "Cuentos para niños", "WIGCUER", 190),
    WIGETTA_Y_FERIA_FANTASMA("Wigetta y la feria fantasma", "Cuentos para niños", "WIGFERIA", 280),
    WIGETTA_Y_EL_CUENTOJAMASCONTADO("Wigetta y el cuento jamás contando", "Cuentos para niños", "WIGCUEN", 230),
    WIGETTA_Y_MUMUCHUMU("Wigetta y la isla mumúchumu", "Cuentos para niños", "WIGMU", 230),
    WIGETTA_Y_ANTIDOTO("Wigetta y el antídoto secreto", "Cuentos para niños", "WIGANT", 300);

    private final String descripcion;
    private final String categoria;
    private final String id;
    private final double precio;

    private ArticuloLibreriaDante(String descripcion, String categoria, String id, double precio)
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
