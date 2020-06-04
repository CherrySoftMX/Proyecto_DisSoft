package com.controladores;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Nicolás
 */
@SuppressWarnings("unchecked")
public class ListManager
{

    private static ListManager instance;

    public static synchronized ListManager getInstance()
    {
        if (instance == null)
            instance = new ListManager();

        return instance;
    }

    private ListManager()
    {

    }

    public void initList(JList<?> lista)
    {
        lista.setModel(new DefaultListModel<>());
    }

    public void establecerElementos(JList<?> lista, Object[] elementos)
    {
        establecerElementos(lista, Arrays.asList(elementos));
    }

    public void establecerElementos(JList<?> lista, Enumeration<?> elementos)
    {
        vaciarLista(lista);

        while (elementos.hasMoreElements())
            anadirElemento(lista, elementos.nextElement());
    }

    public void establecerElementos(JList<?> lista, List<?> elementos)
    {
        vaciarLista(lista);

        for (Object elemento : elementos)
            anadirElemento(lista, elemento);
    }

    public void anadirElemento(JList<?> lista, Object elemento)
    {
        DefaultListModel<Object> defaultListModel = getDefaultListModel(lista);
        defaultListModel.addElement(elemento);
    }

    public void vaciarLista(JList<?> lista)
    {
        DefaultListModel<?> defaultListModel = getDefaultListModel(lista);
        defaultListModel.removeAllElements();
    }

    public Object getSelectedObject(JList<?> lista)
    {
        return lista.getSelectedValue();
    }

    public DefaultListModel<Object> getDefaultListModel(JList<?> lista)
    {
        return (DefaultListModel<Object>) lista.getModel();
    }

}
