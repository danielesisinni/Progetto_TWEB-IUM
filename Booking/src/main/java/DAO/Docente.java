package DAO;

public class Docente {
    private String nome;
    private int iddocente;
    private String stato;

    public Docente(int id, String nome, String stato) {
        this.nome = nome;
        this.iddocente = id;
        this.stato = stato;
    }

    public int getIddocente() {
        return iddocente;
    }

    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "nome='" + nome + '\'' +
                ", id=" + iddocente +
                ", stato=" + stato + '}';
    }
}
