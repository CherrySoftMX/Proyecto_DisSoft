package com.modelo;

import com.modelo.decorator.Articulo;
import com.modelo.estado.CarritoCancelado;
import com.modelo.estado.CarritoEstado;
import com.modelo.estado.CarritoVacio;
import com.modelo.iterator.FactoryEnumeration;
import com.modelo.observer.Observado;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Esta calse extiende de observado ya que notificara a un panel de los estados del carrito por medio de una imagen.
 *
 * @author emman
 */
public class CarritoCompras extends Observado
{

    public static final int MAX_CAPACIDAD = 20;

    private final List<Articulo> articulos;
    //Este es el contexto deonde utilizaremos los estados
    private CarritoEstado estado;

    public CarritoCompras()
    {
        articulos = new ArrayList<>();
        setEstado(new CarritoVacio());
    }

    public void addArticulo(Articulo articulo)
    {
        estado.addArticulo(this, articulo);
        notificar(articulo);
    }

    public void eliminarArticulo(Articulo articulo)
    {
        estado.removeArticulo(this, articulo);
        notificar(articulo);
    }

    public List<Articulo> getArticulos()
    {
        return articulos;
    }

    public Enumeration<Articulo> listarArticulos()
    {
        return FactoryEnumeration.enumeration(articulos);
    }

    public void cancelarCarrito()
    {
        setEstado(new CarritoCancelado());
        notificar(CarritoEstado.ESTADO_CANCELADO);
    }

    public void setEstado(CarritoEstado estado)
    {
        this.estado = estado;
        notificar(estado.manejar());
    }

    public int getEstado()
    {
        return estado.manejar();
    }

    public boolean estaVacio()
    {
        return getArticulos().isEmpty();
    }

}
