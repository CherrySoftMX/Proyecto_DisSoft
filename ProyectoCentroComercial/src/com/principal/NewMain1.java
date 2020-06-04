package com.principal;

import com.controladores.ControllerMenu;
import com.modelo.ArticuloSencillo;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete1;
import com.modelo.factory.Factory;
import com.modelo.factory.FactoryTienda;
import com.modelo.tienda.Libreria;
import com.modelo.tienda.Tienda;
import java.util.ArrayList;

/**
 * Falta el solicitar un carrito Falta el crear paquetes
 *
 * @author emman
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CentroComercial centroComercial = new CentroComercial("MATH.ES");

        Factory fac = new FactoryTienda();
        Tienda zapateria = fac.crearTienda("Zapateria");
        Tienda libreria = fac.crearTienda("Libreria");
        Cliente cliente = new Cliente("Emmanuel chable", null, zapateria);
        Cliente cliente2 = new Cliente("Alvaro Trujeque", null, zapateria);
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
        centroComercial.entrar(cliente);
        centroComercial.entrar(cliente2);
        centroComercial.addTienda(zapateria);
        centroComercial.addTienda(libreria);
        libreria.adicionarArticulo(new ArticuloSencillo("Libro", "aaaaa", libreria, 103));
        System.out.println(cliente.getNotificacion());
        ControllerMenu c2 = new ControllerMenu(centroComercial);
        c2.iniciar();
    }

}
