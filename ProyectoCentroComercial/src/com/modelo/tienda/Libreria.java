package com.modelo.tienda;

/**
 *
 * @author emman
 */
public class Libreria extends Tienda
{

    private static Libreria instance;

    public static synchronized Libreria getInstance()
    {
        if (instance == null)
            instance = new Libreria();

        return instance;
    }

    public Libreria()
    {
        super("Librería", "LIB123");
    }

}
