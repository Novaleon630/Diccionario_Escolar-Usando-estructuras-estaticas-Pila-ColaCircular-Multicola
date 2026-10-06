[README.md](https://github.com/user-attachments/files/33083497/README.md)
# Diccionario con Multicola Circular (Java)

Ejercicio de **estructuras de datos** hecho en Java (NetBeans) para la materia Programación 3.
Simula un diccionario donde las palabras se organizan **por letra inicial**, y cada palabra guarda sus **significados** en una pila.

La idea central es combinar tres estructuras, una dentro de otra, como cajitas:

```
MULTICOLA (un arreglo de 26 colas, una por letra)
 |
 |-- Cola circular de la letra A
 |     |-- Palabra "avion"
 |     |     |-- Pila de significados: [ Vehiculo que vuela | Aeronave con alas y motor ]
 |     |-- Palabra "arbol"
 |     |     |-- Pila de significados: [ ... | ... ]
 |     |-- Palabra "aire"
 |           |-- Pila de significados: [ ... | ... ]
 |
 |-- Cola circular de la letra B
 |     |-- Palabra "barco"  -> su pila de significados
 |     |-- Palabra "boca"   -> su pila de significados
 |
 |-- Cola circular de la letra C
 |     |-- ...
 |
 |-- (... hasta la Z)
```

---

## Estructura del proyecto

```
Diccionario_Main/
|-- src/diccionario_main/
|     |-- PilaSignificado.java
|     |-- Palabra.java
|     |-- CCircularPal.java
|     |-- MulticolaCCP.java
|     |-- Diccionario_Main.java
|-- nbproject/        (configuración de NetBeans)
|-- build.xml
|-- manifest.mf
```

## Las clases

| Clase | Qué es | Qué guarda |
|---|---|---|
| `PilaSignificado` | Pila (LIFO) de capacidad 30 | Los significados (`String`) de una palabra |
| `Palabra` | Objeto del diccionario | El nombre de la palabra y su `PilaSignificado` |
| `CCircularPal` | Cola circular de capacidad 100 | Objetos `Palabra` que empiezan con la misma letra |
| `MulticolaCCP` | Arreglo de 26 colas circulares | Una `CCircularPal` por cada letra de la A a la Z |
| `Diccionario_Main` | Clase principal | Crea los datos y prueba todas las operaciones |

## Cómo funciona

1. Se crea una `PilaSignificado` y se le agregan los significados con `adicionar`.
2. Se crea una `Palabra` con su nombre y esa pila.
3. La palabra se envía a la multicola con `adicionarPalabra`. Este método:
   - Toma la primera letra del nombre (`avion` -> `A`).
   - La convierte en un índice con `primera - 'A' + 1` (`A` = 1, `B` = 2, ... `Z` = 26).
   - Llama a `adicionar(palabra, indice)`, que delega en `C[indice].adicola(palabra)`.
4. Para mostrar, la multicola recorre las 26 colas y cada cola circular muestra sus palabras sin sacarlas.

### Cola circular

Se usan los índices `frente` y `fin`, con aritmética modular para "dar la vuelta" al arreglo:

```java
fin = (fin + 1) % max;                       // adicionar
frente = (frente + 1) % max;                 // eliminar
int n = (fin - frente + max) % max;          // cantidad de elementos
int posicion = (frente + 1 + i) % max;       // recorrer sin sacar
```

La cola está llena cuando tiene `max - 1` elementos (se deja una casilla libre para distinguir "llena" de "vacía").

### Multicola

Cada método de `MulticolaCCP` sigue el mismo patrón: **validar el índice y delegar** en la cola correspondiente.

```java
public void adicionar(Palabra px, int i) {
    if (indiceValido(i)) {
        C[i].adicola(px);
    } else {
        System.out.println("Cola " + i + " no existe");
    }
}
```

### Pila de significados

El último significado en entrar es el primero en salir (LIFO). Para mostrarla sin perder datos se usa una pila auxiliar: se vacía la original mostrando cada elemento y luego se restaura con `vaciarPila(aux)`.

## Métodos principales de MulticolaCCP

| Método | Descripción |
|---|---|
| `adicionarPalabra(Palabra px)` | Agrega la palabra en la cola de su primera letra |
| `adicionar(Palabra px, int i)` | Agrega la palabra en la cola número `i` |
| `eliminar(int i)` | Saca y devuelve la palabra del frente de la cola `i` |
| `mostrar(int i)` | Muestra las palabras de una sola letra |
| `mostrarMulticola()` | Muestra todo el diccionario, letra por letra |
| `nroElementos(int i)` | Cantidad de palabras en la cola `i` |
| `esVacia(int i)` / `esLlena(int i)` | Estado de la cola `i` |
| `vaciar(int i)` | Deja la cola `i` vacía |
| `indiceLetra(String nomP)` | Convierte la primera letra de una palabra en su índice |

## Ejemplo de uso

```java
MulticolaCCP diccionario = new MulticolaCCP();

PilaSignificado pila = new PilaSignificado();
pila.adicionar("Vehiculo que vuela");
pila.adicionar("Aeronave con alas y motor");

Palabra avion = new Palabra("avion", pila);
diccionario.adicionarPalabra(avion);

diccionario.mostrarMulticola();
```

### Salida (recortada)

```
===== DICCIONARIO COMPLETO =====

 Letra: A
Nombre de la palabra: avion
1. Aeronave con alas y motor
2. Vehiculo que vuela
Nombre de la palabra: arbol
1. Esquema de ramas en informatica
2. Planta de tronco de madera
...
 Letra: B
Nombre de la palabra: barco
1. Embarcacion grande
2. Vehiculo que navega sobre el agua
...
```

> Los significados salen en orden inverso al que se ingresaron porque es una pila: el último en entrar es el primero en salir.

## Complejidad (Big O)

| Operación | Complejidad |
|---|---|
| `adicionarPalabra`, `eliminar`, `nroElementos` | O(1) |
| Mostrar una pila de significados | O(s), con s = significados |
| Mostrar una cola (`mostrarColaS`) | O(p * s), con p = palabras |
| `mostrarMulticola` | O(L + p * s), con L = 26 letras |

## Cómo ejecutarlo

**Requisitos:** JDK 8 o superior. Se recomienda NetBeans.

1. Clonar o descargar el repositorio.
2. Abrir la carpeta `Diccionario_Main` como proyecto en NetBeans.
3. Ejecutar la clase `Diccionario_Main` (clic derecho > Run File).

O desde la terminal, dentro de la carpeta `src`:

```bash
javac diccionario_main/*.java
java diccionario_main.Diccionario_Main
```

## Convenciones del código

- Sin tildes ni caracteres especiales dentro del código, para que no aparezcan símbolos `?` en la consola.
- Recorridos con `while`, excepto `mostrarColaS`, que usa `for` con la fórmula circular.
- Se verifica si la estructura está vacía o llena antes de operar.
- Cuando una estructura está vacía se devuelve un objeto vacío (`new Palabra()`) en lugar de `null`.

## Conceptos practicados

- Pilas (LIFO) y colas circulares (FIFO)
- Estructuras anidadas: multicola de colas circulares de objetos con pilas
- Patrón de estructura auxiliar (sacar, procesar, restaurar)
- Delegación de métodos y validación de índices
- Análisis de complejidad (Big O)

Universidad Mayor de San Andrés (UMSA), La Paz, Bolivia - Informática, mención Desarrollo de Software.
