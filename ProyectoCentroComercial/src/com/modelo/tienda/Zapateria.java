package com.modelo.tienda;

/**
 *
 * @author emman
 */
public class Zapateria extends Tienda
{

    private static Zapateria instance;

    public static synchronized Zapateria getInstance()
    {
        if (instance == null)
            instance = new Zapateria();

        return instance;
    }

    private Zapateria()
    {
        super("Zapatería", "ZAP123");
    }

}
