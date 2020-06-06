package com.modelo.decorator;

import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public abstract class Articulo
{

    protected String descripcion;
    protected String categoria;
    protected String identificador;
    protected Tienda tienda;

    public Articulo(String descripcion, String categoria, String identificador, Tienda tienda)
    {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.identificador = identificador;
        this.tienda = tienda;
    }

    public abstract double getPrecio();

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    public Tienda getTienda()
    {
        return tienda;
    }

    public void setTienda(Tienda tienda)
    {
        this.tienda = tienda;
    }

}
