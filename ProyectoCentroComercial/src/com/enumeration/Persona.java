package com.enumeration;

/**
 *
 * @author emman
 */
public enum Persona
{

    Emmanuel("Emmanuel Chablé"),
    Carlos("Carlos Góngora"),
    Eusebio("Eusebio Ajax"),
    Charly("Charly Álvarez"),
    Nicolas("Nicolás Canul");

    private final String nombre;

    private Persona(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

}
