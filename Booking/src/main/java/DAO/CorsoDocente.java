package DAO;

public class CorsoDocente{
    private String corso;
    private String docente;

    public CorsoDocente(String corso, String docente) {
        this.corso = corso;
        this.docente = docente;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    @Override
    public String toString() {
        return "[" + corso + "--" + docente + "]\b";
    }
}
