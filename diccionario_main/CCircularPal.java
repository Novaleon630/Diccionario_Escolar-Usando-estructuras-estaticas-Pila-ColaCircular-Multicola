package diccionario_main;

public class CCircularPal {

    private int frente, fin;
    private int max = 100;
    private Palabra cc[] = new Palabra[max + 1];

    public CCircularPal() {
        this.frente = 0;
        this.fin = 0;
    }

    public boolean esVacia() {
        return frente == fin;
    }

    public int nroElementos() {
        int n = (fin - frente + max) % max;
        return n;
    }

    public boolean estaLlena() {
        return nroElementos() == max - 1;
    }

    public void adicola(Palabra dato) {
        if (!estaLlena()) {
            // fin avanza en forma circular y se guarda el dato
            fin = (fin + 1) % max;
            cc[fin] = dato;
        } else {
            System.out.println("Cola circular llena");
        }
    }

    public Palabra elicola() {
        // se devuelve una Palabra vacia en lugar de null
        Palabra dato = new Palabra();
        if (esVacia()) {
            System.out.println("Cola circular vacia");
        } else {
            // frente avanza en forma circular y se saca el dato
            frente = (frente + 1) % max;
            dato = cc[frente];
        }
        return dato;
    }

    public void vaciarColaCircular() {
        frente = 0;
        fin = 0;
    }

    public void mostrarColaS() {
        if (esVacia()) {
            System.out.println("Cola vacia - no hay elementos para mostrar");
        } else {
            // se recorre sin sacar elementos, con la formula circular
            for (int i = 0; i < nroElementos(); i++) {
                int posicion = (frente + 1 + i) % max;
                cc[posicion].mostrar();
            }
        }
    }
}