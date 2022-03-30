package DAO;

public class Prenotazione {
    private String utente;
    private String docente;
    private String corso;
    private String giorno;
    private String ora;
    private String status;

    public Prenotazione(String utente, String docente, String corso, String giorno, String ora, String status) {
        this.utente = utente;
        this.docente = docente;
        this.corso = corso;
        this.giorno = giorno;
        this.ora = ora;
        this.status = status;
    }

    public String getUtente() {
        return utente;
    }

    public String getDocente() {
        return docente;
    }

    public String getCorso() {
        return corso;
    }

    public String getGiorno() {
        return giorno;
    }

    public String getOra() {
        return ora;
    }

    public String getStatus() {
        return status;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "utente=" + utente +
                ", docente=" + docente +
                ", corso=" + corso +
                ", giorno=" + giorno +
                ", ora=" + ora +
                ", status=" + status +
                '}';
    }
}
