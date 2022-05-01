package DAO;

public class Corso{
    private int id;
    private String titolo;
    private String stato;

    public Corso(int id, String titolo, String stato) {
        this.id = id;
        this.titolo = titolo;
        this.stato = stato;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", titolo='" + titolo +
                ", stato = '" + stato +'\'' +
                '}';
    }
}
