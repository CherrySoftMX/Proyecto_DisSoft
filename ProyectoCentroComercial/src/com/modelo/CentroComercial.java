package com.modelo;

import com.modelo.iterator.FactoryEnumeration;
import com.modelo.tienda.Tienda;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author emman
 */
public class CentroComercial
{

    private String nombre;
    private List<Tienda> tiendas;
    private List<Cliente> clientes;

    public CentroComercial(String nombre)
    {
        this.nombre = nombre;
        this.tiendas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void entrar(Cliente cliente)
    {
        clientes.add(cliente);
    }

    public void salir(Cliente cliente)
    {
        clientes.remove(cliente);
    }

    public CarritoCompras getCarrito()
    {
        return new CarritoCompras();
    }

    public List<Tienda> getTiendas()
    {
        return tiendas;
    }

    public Enumeration<Tienda> listarTiendas()
    {
        return FactoryEnumeration.enumeration(tiendas);
    }

    public Enumeration<Cliente> listarClientes()
    {
        return FactoryEnumeration.enumeration(clientes);
    }

    public void addTienda(Tienda tienda)
    {
        clientes.forEach(tienda::anadirObservador);
        tiendas.add(tienda);
    }

    public List<Cliente> getClientes()
    {
        return clientes;
    }

}
