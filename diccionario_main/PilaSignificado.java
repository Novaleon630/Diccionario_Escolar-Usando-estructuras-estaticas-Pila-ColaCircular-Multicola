package diccionario_main;

/**
 *
 * @author Estudiante : matricula:
 */
public class PilaSignificado {
     private int max = 30;
    private String est[] = new String[max + 1];
    private int tope;

    public PilaSignificado() {
        this.tope = 0;

    }

    public boolean esVacia() {
        return tope == 0;
    }

    public boolean esLlena() {
        return tope == max;
    }

    public int numeroElementos() {
        return tope;
    }

    public String eliminar() {
        String elemento = "";//null tambien se puede usar
        if (!esVacia()) {
            elemento = est[tope];
            tope--;
        } else {
            System.out.println("la pila esta vacia");
        }
        return elemento;
    }

    public void adicionar(String px) {
        if (esLlena()) {
            System.out.println("la pila esta llena");
        } else {
            tope++;
            est[tope] = px;
        }
    }

    public void vaciarPila(PilaSignificado aux) {
        while (!aux.esVacia()) {
            String dato = aux.eliminar();
            adicionar(dato);
        }
    }

    public void mostrarPilaSignificado() {
        PilaSignificado aux = new PilaSignificado();
        int i = 0;
        if (esVacia()) {
            System.out.println("pila esta vacia");
        } else {
            while (!esVacia()) {
                String dato = eliminar();
                System.out.print((i+1)+". ");
                System.out.println(dato);
                aux.adicionar(dato);
                i++;
            }
            vaciarPila(aux);
        }

    }
}
