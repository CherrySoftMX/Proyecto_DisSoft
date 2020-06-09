package com.modelo;

import com.controladores.util.Alerta;
import com.modelo.observer.Observador;
import com.modelo.tienda.Tienda;

/**
 *
 * @author emman
 */
public class Cliente implements Observador
{

    private String nombre;
    private CarritoCompras carritoCompras;
    private Tienda tiendaActual;

    public Cliente(String nombre, CarritoCompras carritoCompras)
    {
        this.nombre = nombre;
        this.carritoCompras = carritoCompras;
    }

    public String getNombre()
    {
        return nombre;
    }

    public CarritoCompras getCarritoCompras()
    {
        return carritoCompras;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras)
    {
        this.carritoCompras = carritoCompras;
    }

    public Tienda getTiendaActual()
    {
        return tiendaActual;
    }

    public void setTiendaActual(Tienda tiendaActual)
    {
        this.tiendaActual = tiendaActual;
    }

    public boolean tieneCarrito()
    {
        return carritoCompras != null;
    }

    @Override
    public String toString()
    {
        return "Cliente = " + "nombre: " + nombre;
    }

    @Override
    public void actualizar(Object item)
    {
        Alerta.mostrarMensaje(null, "Mensaje", nombre + ": Hay un nuevo artículo en la tienda: " + item);
        //System.out.println(nombre + ": Hay un nuevo artículo en la tienda: " + item);
    }

}
