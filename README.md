# Centro comercial

Proyecto para la asignatura Diseño de software, el cual consiste en una simulación de un centro comerial.

## Getting Started

asd

### Prerequisites


Java 8 o superior.


### Installing

Descargar la carpeta de ejecutable :V

## Descripción


### Patrones de diseño utilizados

Patrones:

```
    Singleton
    Decorator
    Factory
    Observer
    Iterator
```

### Diagramas de cada patron:
#### Singleton:
![](Diagramas/singleton/DiagramaClases.png)
```
   Descripcion
```

#### Decorator:
![](Diagramas/decorator/DiagramaClases.png)
```
   Descripción: Para esta situación, el uso del patrón Decorator nos es muy útil para crear paquetes de artículos e incluso paquetes de paquetes, ya que cada paquete es un artículo. De igual forma, el precio para un paquete se puede calcular de una manera muy sencilla siguiendo el diseño que este patrón nos ofrece.	 
```

#### Iterator:
![](Diagramas/iterator/DiagramaClases.png)
```
 Descripción: Los objectos Enumeration<T> son utilizados para recorrer una colección de elementos en algún orden no especificado. Para nuestro caso, estos objectos se usan para iterar sobre algún conjunto de datos (clientes o artículos) y posteriormente ir mostrándolos en pantalla (tablas o listas).
```

#### Factory:
![](Diagramas/factory/DiagramaClases.png)
```
   Descripción: La clase CFactoryTienda nos permite generar tiendas sin necesidad de conocer cosas innecesarias, solo importa pasarle un identificador de alguna tienda, y este se encargará de crearla.
```

#### Observer:
![](Diagramas/observer/DiagramaClases.png)
```
   Descripcion: La manera de implementar este patrón es muy sencilla, debemos tener dos clases que estarán mutuamente relacionadas Observador y Observado
Observador es una interfaz que definirá métodos, los cuales se enfocarán en mostrar como una clase reaccionará ante las acciones de un Observado.
Observado es una clase abstracta que contiene objetos Observadores dentro de él, ya que mediante el un método notificara a los Observadores de algún cambio dentro de él.

```



## Creado con:
* [Java 8 swing](https://www.java.com/es/download/) - Lenguaje principal



## Version



## Authors

* **Emmanue Chable** - *Initial work* - [SonBear](https://github.com/SonBear)
* **Nicólas Ibarra** - *full* - [HikingCarrot7](https://github.com/HikingCarrot7)


Lista de contribución [contributors](https://github.com/SonBear/Proyecto_DisSoft/graphs/contributors) who participated in this project.


## Acknowledgments

* 

