# Centro comercial.

Proyecto para la asignatura Diseño de software, el cual consiste en una simulación de un centro comercial.

## Tabla de contenido.

- [Programa](#programa)
- [Patrones de diseño](#patrones)
- [Información adicional](#info)  

<a name="programa"></a>

## Descripción.
Centro comercial, que nos presenta una lista de clientes y tiendas, despúes de seleccionar un cliente y solicitar un carrito de compras, podrá a empezar a añadir elementos a su carrito, para luego procesar un pago.

### Capturas de pantalla.



<!--Patrones de diseño-->

<a name="patrones"></a>

## Patrones de diseño utilizados:

Patrones:

```
    Singleton
    Decorator
    Factory
    Observer
    Iterator
```

### Diagramas de cada patrón:

#### Singleton:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/singleton/DiagramaClases.png?raw=true" alt="diagrama_clases_singleton"/>
</p>

La implementación de este patrón es muy sencilla, únicamente se usa para mantener una única instancia y un punto de acceso global a la factoría de las tiendas.


#### Decorator:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/decorator/DiagramaClases.png?raw=true" alt="diagrama_clases_decorator"/>
</p>

Para esta situación, el uso del patrón Decorator nos es muy útil para crear paquetes de artículos e incluso paquetes de paquetes, ya que cada paquete es un artículo. De igual forma, el precio para un paquete se puede calcular de una manera muy sencilla siguiendo el diseño que este patrón nos ofrece.	 


#### Iterator:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/iterator/DiagramaClases.png?raw=true" alt="diagrama_clases_iterator"/>
</p>

Los objectos Enumeration<T> son utilizados para recorrer una colección de elementos en algún orden no especificado. Para nuestro caso, estos objectos se usan para iterar sobre algún conjunto de datos (clientes o artículos) y posteriormente ir mostrándolos en pantalla (tablas o listas).


#### Abstract Factory:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/abstract%20factory/DiagramaClases.png?raw=true" alt="diagrama_clases_iterator"/>
</p>

Este patrón de diseño nos facilita mucho la creación de familias de objectos. Para esta situación en particular en la que tenemos objetos que no dependen entre sí, la creación de este conjunto de tiendas es muy sencilla. 


#### Observer:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/observer/DiagramaClases.png?raw=true" alt="diagrama_clases_iterator"/>
</p>

Este patrón de diseño no solo se usa en este contexto específico del sistema, sino también para mandar notificaciones entre ventanas para responder de cierta manera ante las acciones del usuario; dibujar el estado del carrito en pantalla según los cambios que se hagan dentro de él, o simples acciones internas que se realizan dentro de las tablas para actualizar los datos. 


#### State:

<p align="center">
  <img src="https://github.com/SonBear/Proyecto_DisSoft/blob/master/Diagramas/state/DiagramaClases.png?raw=true" alt="diagrama_clases_iterator"/>
</p>

Este patrón es utilizado para la clase CarritoCompras el cual nos ayuda a procesar los estados que el carrito tiene cuando se ejecuta una acción. Este patrón también es utilizado para la parte gráfica y, uniéndolo con el patrón Observer, nos permite actualizar elementos visuales que representan al carrito.


## Información adicional.

### Prerequisites

Java 8 o superior.


### Installing
*Instalar Java 8 o superior
*Descargar la carpeta de ejecutable
*Ejecutar el archivo .jar


### Creado con:
* [Java 8 swing](https://www.java.com/es/download/) - Lenguaje principal


### Version


### Authors

* **Emmanue Chable** - *Initial work* - [SonBear](https://github.com/SonBear)
* **Nicólas Ibarra** - *full* - [HikingCarrot7](https://github.com/HikingCarrot7)

Lista de contribución [contributors](https://github.com/SonBear/Proyecto_DisSoft/graphs/contributors) que
han participado en el proyecto.
