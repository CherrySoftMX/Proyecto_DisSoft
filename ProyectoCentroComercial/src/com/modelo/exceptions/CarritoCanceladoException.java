package com.modelo.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class CarritoCanceladoException extends RuntimeException
{

    /**
     * Creates a new instance of <code>CarritoCanceladoException</code> without detail message.
     */
    public CarritoCanceladoException()
    {
        super("El carrito está cancelado.");
    }

    /**
     * Constructs an instance of <code>CarritoCanceladoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CarritoCanceladoException(String msg)
    {
        super(msg);
    }
}
