package com.modelo.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class CarritoLlenoException extends RuntimeException
{

    /**
     * Creates a new instance of <code>CarritoLlenoException</code> without detail message.
     */
    public CarritoLlenoException()
    {
        super("El carrito está lleno.");
    }

    /**
     * Constructs an instance of <code>CarritoLlenoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CarritoLlenoException(String msg)
    {
        super(msg);
    }
}
