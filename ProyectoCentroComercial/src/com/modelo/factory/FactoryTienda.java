package com.modelo.factory;

import com.modelo.tienda.Libreria;
import com.modelo.tienda.Tienda;
import com.modelo.tienda.Zapateria;

/**
 *
 * @author emman
 */
public class FactoryTienda implements Factory {

    private Tienda libreria;
    private Tienda zapateria;

    @Override
    public Tienda crearTienda(String tipo) {
        switch (tipo) {
            case "Libreria":
                if (libreria == null) {
                    libreria = new Libreria();
                    return libreria;
                } else {
                    return libreria;
                }

            case "Zapateria":
                if (zapateria == null) {
                    zapateria = new Zapateria();
                    return zapateria;

                } else {
                    return zapateria;
                }

            default:
                throw new AssertionError();
        }

    }

}
