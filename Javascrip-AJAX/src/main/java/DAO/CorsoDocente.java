package DAO;

public class CorsoDocente{
    private String corso;
    private int id;

    public CorsoDocente(String corso, int id) {
        this.corso = corso;
        this.id = id;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CorsoDocente{" +
                "corso='" + corso + '\'' +
                ", docente='" + id + '\'' +
                '}';
    }
}
