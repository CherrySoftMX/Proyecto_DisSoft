package com.modelo;

import com.modelo.decorator.Articulo;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class ArticuloSencillo implements Articulo
{

    private String nombre;
    private String identificador;
    private Tienda tienda;
    private double precio;

    public ArticuloSencillo(String nombre, String identificador, Tienda tienda, double precio)
    {
        this.nombre = nombre;
        this.identificador = identificador;
        this.tienda = tienda;
        this.precio = precio;
    }

    @Override
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    public Tienda getTiendaActual()
    {
        return tienda;
    }

    public void setTiendaActual(Tienda tiendaActual)
    {
        this.tienda = tiendaActual;
    }

    @Override
    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s: Precio: $%,.2f", getNombre(), getDescripcion(), getPrecio());
    }

    @Override
    public String getDescripcion()
    {
        return "Artículo sencillo";
    }

}
