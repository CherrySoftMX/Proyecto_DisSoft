package com.modelo.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class CarritoVacioException extends RuntimeException
{

    /**
     * Creates a new instance of <code>CarritoVacioException</code> without detail message.
     */
    public CarritoVacioException()
    {
        super("El carrito está vacío");
    }

    /**
     * Constructs an instance of <code>CarritoVacioException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CarritoVacioException(String msg)
    {
        super(msg);
    }
}
