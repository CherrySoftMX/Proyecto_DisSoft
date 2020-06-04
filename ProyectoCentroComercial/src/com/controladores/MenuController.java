package com.controladores;

import com.modelo.CentroComercial;
import com.modelo.Cliente;
import com.vista2.Menu;
import com.vista2.UIConstants;
import javax.swing.JList;

/**
 *
 * @author HikingCarrot7
 */
public class MenuController implements UIConstants
{

    private final Menu vistaMenu;
    private final CentroComercial centroComercial;
    private final ListManager listManager;

    public MenuController(Menu vistaMenu, CentroComercial centroComercial)
    {
        this.vistaMenu = vistaMenu;
        this.centroComercial = centroComercial;
        this.listManager = ListManager.getInstance();
        initComponents();
        iniciarListaClientes();
        iniciarTablaTiendas();
    }

    private void initComponents()
    {
        JList<Cliente> listaClientes = vistaMenu.getListClientes();
        listManager.initList(listaClientes);
        listaClientes.setCellRenderer(LISTA_CLIENTES_RENDERER);
    }

    private void iniciarListaClientes()
    {
        listManager.establecerElementos(vistaMenu.getListClientes(), centroComercial.listarClientes());
    }

    private void iniciarTablaTiendas()
    {

    }

}
