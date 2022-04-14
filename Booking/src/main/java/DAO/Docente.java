package DAO;

public class Docente {
    private String nome;
    private int iddocente;

    public Docente(int id, String nome) {
        this.nome = nome;
        this.iddocente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdDocente() {
        return iddocente;
    }

    public void setIdDocente(int iddocente) {
        this.iddocente = iddocente;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "nome='" + nome + '\'' +
                ", id=" + iddocente +
                '}';
    }
}
