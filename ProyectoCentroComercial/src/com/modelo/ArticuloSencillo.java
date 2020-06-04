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
    private Tienda tiendaActual;
    private double precio;

    public ArticuloSencillo(String nombre, String identificador, Tienda tiendaActual, double precio)
    {
        this.nombre = nombre;
        this.identificador = identificador;
        this.tiendaActual = tiendaActual;
        this.precio = precio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public Tienda getTiendaActual()
    {
        return tiendaActual;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    public void setTiendaActual(Tienda tiendaActual)
    {
        this.tiendaActual = tiendaActual;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    @Override
    public String toString()
    {
        return descripcion() + " Precio: $" + precio;
    }

    @Override
    public String descripcion()
    {
        return "Articulo sencillo: " + nombre;
    }

    @Override
    public double precio()
    {
        return precio;
    }

}
