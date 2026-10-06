package diccionario_main;

public class MulticolaCCP {

    private int nc;
    private CCircularPal C[] = new CCircularPal[27];

    public MulticolaCCP() {
        this.nc = 26;
        int i = 1;
        while (i <= nc) {
            C[i] = new CCircularPal();
            i++;
        }
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        if (nc >= 1 && nc <= C.length - 1) {
            this.nc = nc;
        } else {
            System.out.println("Numero de colas invalido");
        }
    }

    private boolean indiceValido(int i) {
        return i >= 1 && i <= nc;
    }

    public int indiceLetra(String nomP) {
        int indice = 0;
        if (nomP != null && nomP.length() > 0) {
            char primera = Character.toUpperCase(nomP.charAt(0));
            if (primera >= 'A' && primera <= 'Z') {
                indice = primera - 'A' + 1;
            }
        }
        return indice;
    }

    public boolean esVacia(int i) {
        boolean vacia = true;
        if (indiceValido(i)) {
            vacia = C[i].esVacia();
        }
        return vacia;
    }

    public boolean esLlena(int i) {
        boolean llena = false;
        if (indiceValido(i)) {
            llena = C[i].estaLlena();
        }
        return llena;
    }

    public int nroElementos(int i) {
        int n = 0;
        if (indiceValido(i)) {
            n = C[i].nroElementos();
        }
        return n;
    }

    public void adicionar(Palabra px, int i) {
        if (indiceValido(i)) {
            C[i].adicola(px);
        } else {
            System.out.println("Cola " + i + " no existe");
        }
    }

    public void adicionarPalabra(Palabra px) {
        int i = indiceLetra(px.getNomP());
        if (i == 0) {
            System.out.println("La palabra no empieza con una letra de la A a la Z");
        } else {
            adicionar(px, i);
        }
    }

    public Palabra eliminar(int i) {
        Palabra dato = new Palabra();
        if (indiceValido(i)) {
            dato = C[i].elicola();
        } else {
            System.out.println("Cola " + i + " no existe");
        }
        return dato;
    }

    public void mostrar(int i) {
        if (indiceValido(i)) {
            char letra = (char) ('A' + i - 1);
            System.out.println("\n Letra: " + letra);
            C[i].mostrarColaS();
        } else {
            System.out.println("Cola " + i + " no existe");
        }
    }

    public void mostrarMulticola() {
        int i = 1;
        while (i <= nc) {
            if (!esVacia(i)) {
                mostrar(i);
            }
            i++;
        }
    }

    public void vaciar(int i) {
        if (indiceValido(i)) {
            C[i].vaciarColaCircular();
        } else {
            System.out.println("Cola " + i + " no existe");
        }
    }
}