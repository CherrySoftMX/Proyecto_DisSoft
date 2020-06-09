package com.modelo.tienda;

import com.modelo.CarritoCompras;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.iterator.FactoryEnumeration;
import com.modelo.observer.Observado;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author emman
 */
public abstract class Tienda extends Observado
{

    protected String nombre;
    protected String identificador;
    protected List<Articulo> articulos;
    protected List<Cliente> clientes;

    public Tienda(String nombre, String identificador)
    {
        this.nombre = nombre;
        this.identificador = identificador;
        this.articulos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public String getNombre()
    {
        return nombre;
    }

    public Articulo getArticulo(int idx)
    {
        return articulos.get(idx);
    }

    public List<Articulo> getArticulos()
    {
        return articulos;
    }

    public List<Cliente> getClientes()
    {
        return clientes;
    }

    public void entrar(Cliente cliente)
    {
        clientes.add(cliente);
    }

    public void salir(Cliente cliente)
    {
        clientes.remove(cliente);
    }

    public Enumeration<Cliente> listarClientes()
    {
        return FactoryEnumeration.enumerationOf(clientes);
    }

    public Enumeration<Articulo> listarArticulos()
    {
        return FactoryEnumeration.enumerationOf(articulos);
    }

    public void agregarAlCarrito(CarritoCompras carrito, Articulo articulo)
    {
        carrito.addArticulo(articulo);
    }

    public void sacarArticuloDelCarrito(CarritoCompras carrito, Articulo articulo)
    {
        carrito.eliminarArticulo(articulo);
    }

    public void adicionarArticulo(Articulo articulo)
    {
        articulos.add(articulo);
        //Cada vez que un articulo se agrega se notifica a sus observadores
        notificar(this);
    }

    @Override
    public String toString()
    {
        return nombre;
    }

}
