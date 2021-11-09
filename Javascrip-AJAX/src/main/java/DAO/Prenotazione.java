package DAO;

public class Prenotazione {
    private int id_docente;
    private int id_corso;
    private int id_utente;

    public Prenotazione(int id_docente, int id_corso, int id_utente) {
        this.id_docente = id_docente;
        this.id_corso = id_corso;
        this.id_utente = id_utente;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getId_corso() {
        return id_corso;
    }

    public void setId_corso(int id_corso) {
        this.id_corso = id_corso;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    @Override
    public String toString() {
        return "Ripetizione{" +
                "id_docente=" + id_docente +
                ", id_corso=" + id_corso +
                ", id_utente=" + id_utente +
                '}';
    }
}
