package com.modelo.decorator;

import java.util.List;

/**
 *
 * @author emman
 */
public abstract class PaqueteArticulo implements Articulo {

    private List<Articulo> articulos;

    public PaqueteArticulo(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

}
