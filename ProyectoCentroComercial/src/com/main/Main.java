package com.main;

import com.controladores.MenuController;
import com.modelo.ArticuloSencillo;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete1;
import com.modelo.factory.CFactoryTienda;
import com.modelo.factory.FactoryTienda;
import com.modelo.tienda.Libreria;
import com.modelo.tienda.Tienda;
import com.vista2.Menu;
import java.awt.EventQueue;
import java.util.ArrayList;

/**
 * Falta el solicitar un carrito Falta el crear paquetes
 *
 * @author emman
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        CentroComercial centroComercial = new CentroComercial("MATH.ES");

        FactoryTienda fac = CFactoryTienda.getInstance();
        Tienda zapateria = fac.crearTienda("Zapateria");
        Tienda libreria = fac.crearTienda("Libreria");

        Cliente cliente1 = new Cliente("Emmanuel Chablé", null, null);
        Cliente cliente2 = new Cliente("Eusebio Ajax", null, null);
        Cliente cliente3 = new Cliente("Carlos Góngora", null, null);
        Cliente cliente4 = new Cliente("Nicolás Canul", null, null);
        Cliente cliente5 = new Cliente("Charly Álvarez", null, null);

        zapateria.adicionarArticulo(new ArticuloSencillo("Zapato", "ddddd", zapateria, 103));
        zapateria.adicionarArticulo(new ArticuloSencillo("Zapato", "dddd1", zapateria, 1202));
        zapateria.adicionarArticulo(new ArticuloSencillo("Zapato", "dddd2", zapateria, 1333));
        zapateria.adicionarArticulo(new ArticuloSencillo("Zapato", "dddd3", zapateria, 300));
        zapateria.adicionarArticulo(new ArticuloSencillo("Zapato", "dddd4", zapateria, 560));

        libreria.adicionarArticulo(new ArticuloSencillo("Libro", "aaaaa", libreria, 103));
        libreria.adicionarArticulo(new ArticuloSencillo("Libro", "aaaaa", libreria, 103));

        Articulo ar1 = new ArticuloSencillo("asdsa", "sadasda", new Libreria(), 1200);
        Articulo ar2 = new ArticuloSencillo("asdsa", "sadasda", new Libreria(), 1200);
        Articulo ar3 = new ArticuloSencillo("asdsa", "sadasda", new Libreria(), 1200);
        Articulo ar4 = new ArticuloSencillo("asdsa", "sadasda", new Libreria(), 1200);
        Articulo ar5 = new ArticuloSencillo("asdsa", "sadasda", new Libreria(), 1200);

        ArrayList<Articulo> articulos = new ArrayList<>();
        articulos.add(ar1);
        articulos.add(ar2);
        articulos.add(ar5);

        Articulo paq1 = new Paquete1(articulos, 0.9);
        libreria.adicionarArticulo(paq1);

        centroComercial.entrar(cliente1);
        centroComercial.entrar(cliente2);
        centroComercial.entrar(cliente3);
        centroComercial.entrar(cliente4);
        centroComercial.entrar(cliente5);

        centroComercial.addTienda(zapateria);
        centroComercial.addTienda(libreria);

        libreria.adicionarArticulo(new ArticuloSencillo("Libro", "aaaaa", libreria, 103));

        EventQueue.invokeLater(() ->
        {
            Menu menu = new Menu();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            new MenuController(menu, centroComercial);
        });
    }

}
