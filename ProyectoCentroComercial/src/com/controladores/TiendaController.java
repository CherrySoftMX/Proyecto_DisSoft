package com.controladores;

import com.modelo.decorator.Articulo;
import com.vista2.MenuTienda;
import com.vista2.UIConstants;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author HikingCarrot7
 */
public class TiendaController implements TableModelListener, UIConstants
{

    private final MenuTienda vistaTienda;
    private final List<Articulo> articulosTienda;
    private final boolean seEntroConCarrito;

    public TiendaController(MenuTienda vistaTienda, List<Articulo> articulosTienda, boolean seEntroConCarrito)
    {
        this.vistaTienda = vistaTienda;
        this.articulosTienda = articulosTienda;
        this.seEntroConCarrito = seEntroConCarrito;
        initComponents();
    }

    private void initComponents()
    {

    }

    @Override
    public void tableChanged(TableModelEvent e)
    {

    }

}
