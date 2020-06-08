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
 * Esta clase extiende de observado ya que notificara a un panel de los estados del carrito por medio de una imagen.
 *
 * @author emman
 */
public class CarritoCompras extends Observado
{

    public static final int MAX_CAPACIDAD = 20;

    private final List<Articulo> articulos;
    //Este es el contexto donde utilizaremos los estados.
    private CarritoEstado estado;

    public CarritoCompras()
    {
        articulos = new ArrayList<>();
        setEstado(new CarritoVacio());
    }

    public void addArticulo(Articulo articulo)
    {
        estado.addArticulo(this, articulo);
        notificar(estado.manejar());
    }

    public void eliminarArticulo(Articulo articulo)
    {
        estado.removeArticulo(this, articulo);
        notificar(estado.manejar());
    }

    /*Metodos creado para mejorar el patron estado*/
    public Articulo getArticulo(int idx)
    {
        return articulos.get(idx);
    }

    public List<Articulo> getArticulos()
    {
        return articulos;
    }

    public Enumeration<Articulo> listarArticulos()
    {
        return FactoryEnumeration.enumerationOf(articulos);
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
        return articulos.isEmpty();
    }

    public boolean estaCancelado()
    {
        return getEstado() == CarritoEstado.ESTADO_CANCELADO;
    }

}
