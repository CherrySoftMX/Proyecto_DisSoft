package com.modelo.decorator;

import com.modelo.tienda.Tienda;
import java.util.List;

/**
 *
 * @author emman
 */
public class PaqueteArticulo extends Paquete
{

    public PaqueteArticulo(String descripcion, String categoria, String identificador, Tienda tienda, List<Articulo> articulos, double descuento)
    {
        super(descripcion, categoria, identificador, tienda, articulos, descuento);
    }

}
