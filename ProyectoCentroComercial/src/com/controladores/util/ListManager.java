package com.controladores.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

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
        lista.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void initListSelectionBehavior(JList<?> lista, ActionListener action)
    {
        lista.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ESCAPE"), "Clear selection");
        lista.getActionMap().put("Clear selection", new AbstractAction()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                lista.getSelectionModel().clearSelection();

                if (action != null)
                    action.actionPerformed(e);
            }
        });
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

    public DefaultListModel<Object> getDefaultListModel(JList<?> lista)
    {
        return (DefaultListModel<Object>) lista.getModel();
    }

}
