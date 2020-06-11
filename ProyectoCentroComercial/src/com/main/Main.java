package com.main;

import com.controladores.MenuController;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete;
import com.modelo.decorator.PaqueteArticulo;
import com.modelo.enums.ArticuloGameStore;
import com.modelo.enums.ArticuloLibreriaDante;
import com.modelo.enums.ArticuloLibreriaGandhi;
import com.modelo.enums.ArticuloPeliShop;
import com.modelo.enums.ArticuloTiendaSHEIN;
import com.modelo.enums.ArticuloZapateriaAndrea;
import com.modelo.enums.Persona;
import com.modelo.factory.FactoryTienda;
import com.modelo.factory.FactoryTiendaEntretenimiento;
import com.modelo.factory.FactoryTiendaLibreria;
import com.modelo.factory.FactoryTiendaVestimenta;
import com.modelo.tienda.Tienda;
import com.vista.Menu;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
        cargarDatos(centroComercial);

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

    //<editor-fold defaultstate="collapsed" desc="Cargar Datos.">
    public static void cargarDatos(CentroComercial centroComercial)
    {
        //<editor-fold defaultstate="collapsed" desc="Tiendas y artículos.">
        //Abstract factory para las tiendas...
        FactoryTienda factoryVest = FactoryTiendaVestimenta.getInstance();
        FactoryTienda factoryLib = FactoryTiendaLibreria.getInstance();
        FactoryTienda factoryEntre = FactoryTiendaEntretenimiento.getInstance();

        Tienda zapateriaAndrea = factoryVest.crearFranquicia();
        Tienda tiendaSHEIN = factoryVest.crearOutlet();

        Tienda libreriaDante = factoryLib.crearFranquicia();
        Tienda libreriaGandhi = factoryLib.crearOutlet();

        Tienda gamePlanet = factoryEntre.crearFranquicia();
        Tienda pelisPlus = factoryEntre.crearOutlet();

        for (Persona persona : Persona.values())
        {
            Cliente cliente = new Cliente(persona.getNombre(), null);
            centroComercial.entrar(cliente);
        }

        for (ArticuloZapateriaAndrea value : ArticuloZapateriaAndrea.values())
            zapateriaAndrea.adicionarArticulo(value.getAsArticuloSencillo(zapateriaAndrea));

        for (ArticuloTiendaSHEIN value : ArticuloTiendaSHEIN.values())
            tiendaSHEIN.adicionarArticulo(value.getAsArticuloSencillo(tiendaSHEIN));

        for (ArticuloLibreriaDante value : ArticuloLibreriaDante.values())
            libreriaDante.adicionarArticulo(value.getAsArticuloSencillo(libreriaDante));

        for (ArticuloLibreriaGandhi value : ArticuloLibreriaGandhi.values())
            libreriaGandhi.adicionarArticulo(value.getAsArticuloSencillo(libreriaGandhi));

        for (ArticuloGameStore value : ArticuloGameStore.values())
            gamePlanet.adicionarArticulo(value.getAsArticuloSencillo(gamePlanet));

        for (ArticuloPeliShop value : ArticuloPeliShop.values())
            pelisPlus.adicionarArticulo(value.getAsArticuloSencillo(pelisPlus));

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Paquetes.">
        List<Articulo> sagaGOW = new ArrayList<>();

        sagaGOW.add(ArticuloGameStore.GEARS_OF_WAR.getAsArticuloSencillo(gamePlanet));
        sagaGOW.add(ArticuloGameStore.GEARS_OF_WAR_2.getAsArticuloSencillo(gamePlanet));
        sagaGOW.add(ArticuloGameStore.GEARS_OF_WAR_3.getAsArticuloSencillo(gamePlanet));
        sagaGOW.add(ArticuloGameStore.GEARS_OF_WAR_4.getAsArticuloSencillo(gamePlanet));
        sagaGOW.add(ArticuloGameStore.GEARS_OF_WAR_5.getAsArticuloSencillo(gamePlanet));

        Paquete paqueteGOW = new PaqueteArticulo("Saga de Gears of War", "Paquete de videojuegos", "PACKGOW", gamePlanet, sagaGOW, 0.1);
        gamePlanet.adicionarArticulo(paqueteGOW);

        List<Articulo> juegosMasVendidos = new ArrayList<>();
        juegosMasVendidos.add(paqueteGOW);

        juegosMasVendidos.add(ArticuloGameStore.GRAND_THEFT_AUTO_V.getAsArticuloSencillo(gamePlanet));
        juegosMasVendidos.add(ArticuloGameStore.MINECRAFT.getAsArticuloSencillo(gamePlanet));

        gamePlanet.adicionarArticulo(new PaqueteArticulo("Juegos más vendidos de la historia", "Paquete de videojuegos", "PACKMASVENDIDOS", gamePlanet, juegosMasVendidos, 0.2));
        //</editor-fold>

        centroComercial.addTienda(zapateriaAndrea);
        centroComercial.addTienda(tiendaSHEIN);

        centroComercial.addTienda(libreriaDante);
        centroComercial.addTienda(libreriaGandhi);

        centroComercial.addTienda(gamePlanet);
        centroComercial.addTienda(pelisPlus);
    }
    //</editor-fold>

}
