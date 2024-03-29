package DAO;

public class Ripetizione {
    private String codice;
    private String docente;
    private String corso;
    private String giorno;
    private String ora;
    private String status;

    public Ripetizione(String codice, String docente, String corso, String giorno, String ora, String status) {
        this.codice = codice;
        this.docente = docente;
        this.corso = corso;
        this.giorno = giorno;
        this.ora = ora;
        this.status = status;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getDocente() {
        return docente;
    }
    public void setDocente(String docente) {
        this.docente = docente;
    }
    public String getCorso() {
        return corso;
    }
    public void setCorso(String corso) {
        this.corso = corso;
    }
    public String getOra() {
        return ora;
    }
    public void setOra(String ora) {
        this.ora = ora;
    }
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    @Override
    public String toString() {
        return  "codice='" + codice + '\'' +
                "Docente='" + docente + '\'' +
                ", corso='" + corso + '\'' +
                ", giorno='" + giorno + '\'' +
                ", ora=" + ora + '\'' +
                ", status=" +
                '}';
    }
}
