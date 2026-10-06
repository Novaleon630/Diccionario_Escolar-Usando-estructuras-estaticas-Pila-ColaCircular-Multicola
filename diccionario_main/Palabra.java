package diccionario_main;

/**
 *
 * @author Estudiante : matricula:
 */
public class Palabra {

    private String nomP;
    private PilaSignificado S;

    public Palabra() {
    }

    public Palabra(String nomP, PilaSignificado S) {
        this.nomP = nomP;
        this.S = S;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public PilaSignificado getS() {
        return S;
    }

    public void setS(PilaSignificado S) {
        this.S = S;
    }
    

    public void mostrar() {
        System.out.println("Nombre de la palabra: " + nomP);
        if (S!=null) {
            S.mostrarPilaSignificado();
        }

    }

}
