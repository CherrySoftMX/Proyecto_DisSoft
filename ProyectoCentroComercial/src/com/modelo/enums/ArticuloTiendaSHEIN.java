package com.modelo.enums;

/**
 *
 * @author HikingCarrot7
 */
public enum ArticuloTiendaSHEIN
{
    SACO_DAMA("Saco para dama", "Ropa para dama", "SACDAM", 700),
    BLUSA("Blusa azul para niña", "Ropa para dama", "BLUDAM", 200),
    GABARDINA("Gabardina verde para dama", "Ropa para dama", "GABDAM", 400),
    PANTALON("Pantalón azul", "Ropa para dama", "PANDAM", 550),
    JUMP_SUIT("Jump suit", "Ropa para dama", "JUMPDAM", 950),
    TRAJE_BANO("Traje de baño amarillo para adolescente", "Ropa para dama", "TRADAM", 220),
    CAMISETA("Camiseta de rayas", "Ropa para dama", "CAMDAM", 280),
    MAQUILLAJE("Maquillaje para la cara", "Artículos para dama", "MAQDAM", 180),
    VESTIDO("Vestido elegante para dama", "Ropa para dama", "VESDAM", 550);

    private final String descripcion;
    private final String categoria;
    private final String identificador;
    private final double precio;

    private ArticuloTiendaSHEIN(String descripcion, String categoria, String identificador, double precio)
    {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.identificador = identificador;
        this.precio = precio;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public double getPrecio()
    {
        return precio;
    }
}
