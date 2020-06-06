package com.main;

import com.controladores.MenuController;
import com.enumeration.ArticuloGameStore;
import com.enumeration.ArticuloLibreria;
import com.enumeration.ArticuloZapateria;
import com.enumeration.Comercio;
import com.enumeration.Persona;
import com.modelo.ArticuloSencillo;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.factory.CFactoryTienda;
import com.modelo.factory.FactoryTienda;
import com.modelo.tienda.Tienda;
import com.vista.Menu;
import java.awt.EventQueue;

/**
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

        FactoryTienda factory = CFactoryTienda.getInstance();
        Tienda zapateria = factory.crearTienda(Comercio.Zapateria);
        Tienda libreria = factory.crearTienda(Comercio.Libreria);
        Tienda gameStore = factory.crearTienda(Comercio.GameStore);

        for (Persona persona : Persona.values())
        {
            Cliente cliente = new Cliente(persona.getNombre(), null);
            centroComercial.entrar(cliente);
        }

        centroComercial.addTienda(zapateria);
        centroComercial.addTienda(libreria);
        centroComercial.addTienda(gameStore);

        for (ArticuloZapateria value : ArticuloZapateria.values())
            zapateria.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), zapateria, value.getPrecio()));

        for (ArticuloLibreria value : ArticuloLibreria.values())
            libreria.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), zapateria, value.getPrecio()));

        for (ArticuloGameStore value : ArticuloGameStore.values())
            gameStore.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), zapateria, value.getPrecio()));

        /**
         * Iniciamos el menú principal.
         */
        EventQueue.invokeLater(() ->
        {
            Menu menu = new Menu();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            new MenuController(menu, centroComercial);
        });
    }

}
