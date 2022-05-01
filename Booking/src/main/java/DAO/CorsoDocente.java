package DAO;

public class CorsoDocente{
    private String corso;
    private String docente;
    private String stato;

    public CorsoDocente(String corso, String docente, String stato) {
        this.corso = corso;
        this.docente = docente;
        this.stato = stato;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
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
        return "[" + corso + "--" + docente + "--" + stato + "]\b";
    }
}
