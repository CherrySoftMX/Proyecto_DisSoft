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

    private List<Articulo> articulos;
    public static final int CAPACIDAD = 20;
    //Este es el contexto deonde utilizaremos los estados
    private CarritoEstado estado;

    public CarritoCompras()
    {

        articulos = new ArrayList<>();
        setEstado(new CarritoVacio());
    }

    public static int getCAPACIDAD()
    {
        return CAPACIDAD;
    }

    public void addArticulo(Articulo articulo)
    {
        estado.addArticulo(this, articulo);
        notificar();
    }

    public void eliminarArticulo(Articulo articulo)
    {
        estado.removeArticulo(this, articulo);
        notificar();
    }

    public List<Articulo> getArticulos()
    {
        return articulos;
    }

    public Enumeration listarArticulos()
    {
        Enumeration<Articulo> enumeration = FactoryEnumeration.enumeration(articulos);

        return enumeration;

    }

    public void cancelarCarrito()
    {
        setEstado(new CarritoCancelado());
        notificar();

    }

    public void setEstado(CarritoEstado estado)
    {
        this.estado = estado;
        notificar();

    }

    public String getEstado()
    {
        return estado.manejar();
    }

}
