package com.enumeration;

/**
 *
 * @author emman
 */
public enum Persona {

    Juan("Julio chow", "02/06/2020"),
    Manuel("Manuel Rodriguez", "03/07/2020"),
    Julia("Julia Deewers", " 04/06/2020");

    private final String nombreP;
    private final String fechaP;

    Persona(String nombre, String fecha) {
        nombreP = nombre;
        fechaP = fecha;
    }

    public String getNombreP() {
        return nombreP;
    }

    public String getFechaP() {
        return fechaP;
    }

}
