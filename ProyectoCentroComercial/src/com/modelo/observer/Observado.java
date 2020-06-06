package com.modelo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emman
 */
public abstract class Observado
{

    protected List<Observador> observadores = new ArrayList<>();

    public void anadirObservador(Observador ob)
    {
        eliminarObservador(ob);
        observadores.add(ob);
    }

    public void eliminarObservador(Observador ob)
    {
        observadores.remove(ob);
    }

    public void notificar(Object item)
    {
        observadores.forEach(o -> o.actualizar(item));
    }
}
