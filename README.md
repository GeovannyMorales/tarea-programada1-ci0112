# Dojo de Supervivencia

## Integrantes

- José Rojas - C4J313
- Bryan Morales - C15238
- Maria Vargas - C38127

---

## ¿De qué va el juego?

Es un juego donde un equipo de tres jugadores intenta sobrevivir a tres oponentes usando cartas. Cada jugador tiene una carta de Aire, una de Tierra y una de Agua. Para atacar al oponente hay que armar combos eligiendo qué carta de cada jugador va contra cada carta del oponente, y decidir cuántos ataques hace ese combo. Las cartas se desgastan con cada pelea, así que hay que pensar bien cómo usarlas. Además hay una barra de energía que baja con cada combo, entonces tampoco se puede atacar infinito

Gana el equipo si logra derrotar a los tres oponentes antes de quedarse sin energía o sin cartas vivas

---

## Cómo compilar

Desde la carpeta donde están los archivos, en la terminal:

```
javac *.java
```

---

## Cómo ejecutar

Si se quiere jugar en la consola:
```
java Main
```

Si se prefiere que aparezcan ventanitas:
```
java Main -gui
```

---

## Cómo se juega

1. Al abrir el programa se puede ver el récord del mejor equipo que haya jugado, iniciar una partida nueva o salir
2. Para jugar hay que poner el nombre del equipo y el de cada uno de los tres jugadores
3. Las cartas se asignan solas, un Aire, un Tierra y un Agua por jugador
4. Contra cada oponente podés ver la vida de tus cartas pero no sus stats completos, eso solo se muestra al final
5. En cada turno se elige qué carta de cada jugador va contra cada carta del oponente y cuántos ataques se quiere hacer
6. Si un jugador pierde todas sus cartas, el oponente se debilita a la mitad de intensidad
7. Al terminar la partida, ganaron o no, se muestra el resumen completo de todos los atributos

---

## Cómo nos dividimos el trabajo

Hubo partes que cada quien fue haciendo por su cuenta y después juntábamos, y otras en las que nos reunimos para resolverlas juntos, sobre todo cuando algo no estaba saliendo o había que ver cómo conectaban las clases entre sí

- Bryan se encargó de las clases principales del juego: Carta, Jugador, Combo y Oponente
- Maria hizo la interfaz gráfica con JOptionPane
- José hizo la lógica del récord y la clase Juego que controla todo el flujo

---

## Errores conocidos y mejoras pendientes

El código está completo. Lo que más nos costó fue la clase Juego, específicamente la parte de detectar cuándo un jugador se acababa de quedar sin cartas después de ejecutar un combo, porque al principio no estábamos guardando el estado anterior y no sabíamos con qué comparar. Lo solucionamos guardando un arreglo de booleanos antes de ejecutar el combo con el estado activo de cada jugador, y después del combo comparábamos ese arreglo con el estado nuevo para saber quién había caído en ese turno
