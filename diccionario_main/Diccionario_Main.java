package diccionario_main;

public class Diccionario_Main {

    // crea una palabra con su pila de 2 significados y la devuelve
    public static Palabra crearPalabra(String nombre, String sig1, String sig2) {
        PilaSignificado pila = new PilaSignificado();
        pila.adicionar(sig1);
        pila.adicionar(sig2);
        Palabra pal = new Palabra(nombre, pila);
        return pal;
    }

    public static void main(String[] args) {
        // se crea la multicola (26 colas circulares, una por letra)
        MulticolaCCP diccionario = new MulticolaCCP();

        // letra A (cola 1)
        diccionario.adicionarPalabra(crearPalabra("avion", "Vehiculo que vuela", "Aeronave con alas y motor"));
        diccionario.adicionarPalabra(crearPalabra("arbol", "Planta de tronco de madera", "Esquema de ramas en informatica"));
        diccionario.adicionarPalabra(crearPalabra("aire", "Mezcla de gases que respiramos", "Aspecto o apariencia de algo"));

        // letra B (cola 2)
        diccionario.adicionarPalabra(crearPalabra("barco", "Vehiculo que navega sobre el agua", "Embarcacion grande"));
        diccionario.adicionarPalabra(crearPalabra("boca", "Abertura por donde se come", "Entrada de un tunel o rio"));

        // letra C (cola 3)
        diccionario.adicionarPalabra(crearPalabra("casa", "Edificio para vivir", "Familia o hogar"));
        diccionario.adicionarPalabra(crearPalabra("carro", "Vehiculo de cuatro ruedas", "Carreta de dos ruedas"));
        diccionario.adicionarPalabra(crearPalabra("cielo", "Espacio sobre la tierra", "Lugar de paz segun la religion"));

        // se muestra todo el diccionario
        System.out.println("===== DICCIONARIO COMPLETO =====");
        diccionario.mostrarMulticola();

        // se muestra solo la letra B (cola 2)
        System.out.println("\n===== SOLO LA LETRA B =====");
        diccionario.mostrar(2);

        // se elimina la primera palabra de la letra A y se muestra
        System.out.println("\n===== SE ELIMINA LA PRIMERA PALABRA DE LA A =====");
        Palabra eliminada = diccionario.eliminar(1);
        eliminada.mostrar();

        // se muestra la letra A despues de eliminar
        System.out.println("\n===== LETRA A DESPUES DE ELIMINAR =====");
        diccionario.mostrar(1);

        // se muestra cuantas palabras quedan en la letra A
        System.out.println("\nPalabras que quedan en la A: " + diccionario.nroElementos(1));
    }
}