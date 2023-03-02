# Juego de la vida

## Breve descripción de la aplicación

* Resumen: el software que se publica en este repositorio permite
  [El Juego de la vid](https://www.youtube.com/watch?v=ouipbDkwHWA)
* Versión: 1.0.

## Compilación del programa

Se ha incluido un `makefile` que define, entre otras tareas, la
generación de un `.jar`:

```console
make jar
```

## Cómo generar el HTML a partir del Javadoc

El HTML con los comentarios se puede obtener mediante la siguiente
sentencia:

```console
make html
```

## Estructura del código

Tal y como muestra la siguiente figura
![diagrama UML](diagrama_clases.png)

existen dos clases: `Tablero.java`, en el paquete `dominio`, que
contiene el método que realiza la simulación; y `Principal.java`, en el
paquete `principal`, que invoca el método de simulación.

