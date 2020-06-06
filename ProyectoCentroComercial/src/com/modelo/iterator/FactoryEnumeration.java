package com.modelo.iterator;

import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author emman
 */
public class FactoryEnumeration
{

    public static <T> Enumeration<T> enumeration(final List<T> lista)
    {
        return new Enumeration<T>()
        {
            //Esta es mi implementacion del patron iterator, usando la clase Enumeration de java y mi interfaz Iterator
            //Este funciona solo para objetos List de java
            private final Iterator<T> i = new Iterator<T>()
            {
                int cont;

                @Override
                public boolean hasNext()
                {
                    return cont < lista.size();
                }

                @Override
                public T next()
                {
                    return lista.get(cont++);
                }
            };

            @Override
            public boolean hasMoreElements()
            {
                return i.hasNext();
            }

            @Override
            public T nextElement()
            {
                return i.next();
            }
        };
    }

}
