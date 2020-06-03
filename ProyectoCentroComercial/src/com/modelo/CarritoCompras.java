package com.modelo;

import com.modelo.decorator.Articulo;
import com.modelo.estado.CarritoCancelado;
import com.modelo.estado.CarritoEstado;
import com.modelo.estado.CarritoLleno;
import com.modelo.estado.CarritoUsado;
import com.modelo.estado.CarritoVacio;
import com.modelo.iterator.FactoryEnumeration;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author emman
 */
public class CarritoCompras {

    private List<Articulo> articulos;
    private final int capacidad = 20;
    //Este es el contexto deonde utilizaremos los estados
    private CarritoEstado estado;

    public CarritoCompras() {

        articulos = new ArrayList<>();
        setEstado(new CarritoVacio());
    }

    public void addArticulo(Articulo articulo) {
        if (articulos.size() > capacidad) {
            setEstado(new CarritoLleno());

        } else {
            articulos.add(articulo);
            setEstado(new CarritoUsado());
        }
    }

    public void eliminarArticulo(Articulo articulo) {
        articulos.remove(articulo);
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public Enumeration listarArticulos() {
        Enumeration<Articulo> enumeration = FactoryEnumeration.enumeration(articulos);

        return enumeration;

    }

    public void cancelarCarrito() {
        setEstado(new CarritoCancelado());
    }

    public void setEstado(CarritoEstado estado) {
        this.estado = estado;

    }

    public String getEstado() {
        return estado.manejar();
    }

}
