package com.main;

import com.controladores.MenuController;
import com.modelo.ArticuloSencillo;
import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete1;
import com.modelo.decorator.PaqueteArticulo;
import com.modelo.enums.ArticuloGameStore;
import com.modelo.enums.ArticuloLibreriaDante;
import com.modelo.enums.ArticuloLibreriaGandhi;
import com.modelo.enums.ArticuloPeliShop;
import com.modelo.enums.ArticuloTiendaSHEIN;
import com.modelo.enums.ArticuloZapateriaAndrea;
import com.modelo.enums.Persona;
import com.modelo.factory.AbstracFactoryTienda;
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

        //Abstract factory para las tiendas...
        AbstracFactoryTienda factoryVest = FactoryTiendaVestimenta.getInstance();
        AbstracFactoryTienda factoryLib = FactoryTiendaLibreria.getInstance();
        AbstracFactoryTienda factoryEntre = FactoryTiendaEntretenimiento.getInstance();

        Tienda zapateriaAndrea = factoryVest.crearFranquicia();
        Tienda tiendaSHEIN = factoryVest.crearOutlet();

        Tienda libreriaDante = factoryLib.crearFranquicia();
        Tienda libreriaGandhi = factoryLib.crearOutlet();

        Tienda gameStore = factoryEntre.crearFranquicia();
        Tienda pelisPlus = factoryEntre.crearOutlet();

        for (Persona persona : Persona.values())
        {
            Cliente cliente = new Cliente(persona.getNombre(), null);
            centroComercial.entrar(cliente);
        }

        for (ArticuloZapateriaAndrea value : ArticuloZapateriaAndrea.values())
            zapateriaAndrea.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), libreriaDante, value.getPrecio()));

        for (ArticuloTiendaSHEIN value : ArticuloTiendaSHEIN.values())
            tiendaSHEIN.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), libreriaGandhi, value.getPrecio()));

        for (ArticuloLibreriaDante value : ArticuloLibreriaDante.values())
            libreriaDante.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), libreriaDante, value.getPrecio()));

        for (ArticuloLibreriaGandhi value : ArticuloLibreriaGandhi.values())
            libreriaGandhi.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), libreriaGandhi, value.getPrecio()));

        for (ArticuloGameStore value : ArticuloGameStore.values())
            gameStore.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), gameStore, value.getPrecio()));

        for (ArticuloPeliShop value : ArticuloPeliShop.values())
            pelisPlus.adicionarArticulo(new ArticuloSencillo(value.getDescripcion(), value.getCategoria(), value.getIdentificador(), pelisPlus, value.getPrecio()));

        List<Articulo> sagaGOW = new ArrayList<>();

        sagaGOW.add(new ArticuloSencillo(
                ArticuloGameStore.GEARS_OF_WAR.getDescripcion(),
                ArticuloGameStore.GEARS_OF_WAR.getCategoria(),
                ArticuloGameStore.GEARS_OF_WAR.getIdentificador(), gameStore,
                ArticuloGameStore.GEARS_OF_WAR.getPrecio()));

        sagaGOW.add(new ArticuloSencillo(
                ArticuloGameStore.GEARS_OF_WAR_2.getDescripcion(),
                ArticuloGameStore.GEARS_OF_WAR_2.getCategoria(),
                ArticuloGameStore.GEARS_OF_WAR_2.getIdentificador(), gameStore,
                ArticuloGameStore.GEARS_OF_WAR_2.getPrecio()));

        sagaGOW.add(new ArticuloSencillo(
                ArticuloGameStore.GEARS_OF_WAR_3.getDescripcion(),
                ArticuloGameStore.GEARS_OF_WAR_3.getCategoria(),
                ArticuloGameStore.GEARS_OF_WAR_3.getIdentificador(), gameStore,
                ArticuloGameStore.GEARS_OF_WAR_3.getPrecio()));

        sagaGOW.add(new ArticuloSencillo(
                ArticuloGameStore.GEARS_OF_WAR_4.getDescripcion(),
                ArticuloGameStore.GEARS_OF_WAR_4.getCategoria(),
                ArticuloGameStore.GEARS_OF_WAR_4.getIdentificador(), gameStore,
                ArticuloGameStore.GEARS_OF_WAR_4.getPrecio()));

        sagaGOW.add(new ArticuloSencillo(
                ArticuloGameStore.GEARS_OF_WAR_5.getDescripcion(),
                ArticuloGameStore.GEARS_OF_WAR_5.getCategoria(),
                ArticuloGameStore.GEARS_OF_WAR_5.getIdentificador(), gameStore,
                ArticuloGameStore.GEARS_OF_WAR_5.getPrecio()));

        PaqueteArticulo paqueteGOW = new Paquete1("Saga de Gears of War", "Paquete de videojuegos", "PACKGOW", gameStore, sagaGOW, 0.1);
        gameStore.adicionarArticulo(paqueteGOW);

        List<Articulo> juegosMasVendidos = new ArrayList<>();
        juegosMasVendidos.add(paqueteGOW);

        juegosMasVendidos.add(new ArticuloSencillo(
                ArticuloGameStore.GRAND_THEFT_AUTO_V.getDescripcion(),
                ArticuloGameStore.GRAND_THEFT_AUTO_V.getCategoria(),
                ArticuloGameStore.GRAND_THEFT_AUTO_V.getIdentificador(),
                gameStore,
                ArticuloGameStore.GRAND_THEFT_AUTO_V.getPrecio()));

        juegosMasVendidos.add(new ArticuloSencillo(
                ArticuloGameStore.MINECRAFT.getDescripcion(),
                ArticuloGameStore.MINECRAFT.getCategoria(),
                ArticuloGameStore.MINECRAFT.getIdentificador(),
                gameStore,
                ArticuloGameStore.MINECRAFT.getPrecio()));

        gameStore.adicionarArticulo(new Paquete1("Juegos más vendidos de la historia", "Paquete de videojuegos", "PACKMASVENDIDOS", gameStore, juegosMasVendidos, 0.2));

        centroComercial.addTienda(zapateriaAndrea);
        centroComercial.addTienda(tiendaSHEIN);

        centroComercial.addTienda(libreriaDante);
        centroComercial.addTienda(libreriaGandhi);

        centroComercial.addTienda(gameStore);
        centroComercial.addTienda(pelisPlus);

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
