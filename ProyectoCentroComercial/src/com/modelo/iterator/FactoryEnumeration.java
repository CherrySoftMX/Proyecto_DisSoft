package com.modelo.iterator;

import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author emman
 */
/*Esta es mi implementacion del patron iterator, usando la interface Enumeration de java y mi interfaz Iterator.
Solo funciona para objetos List<T> de java.*/
public class FactoryEnumeration
{

    public static <T> Enumeration<T> enumerationOf(final List<T> lista)
    {
        return new Enumeration<T>()
        {
            private final Iterator<T> iterator = new Iterator<T>()
            {
                int progress = 0;

                @Override public boolean hasNext()
                {
                    return progress < lista.size();
                }

                @Override public T next()
                {
                    return lista.get(progress++);
                }
            };

            @Override public boolean hasMoreElements()
            {
                return iterator.hasNext();
            }

            @Override public T nextElement()
            {
                return iterator.next();
            }
        };
    }

}
