package DAO;

public class Docente {
    private String nome;
    private String cognome;
    private int iddocente;

    public Docente(String nome, String cognome, int id) {
        this.nome = nome;
        this.cognome = cognome;
        this.iddocente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
                ", cognome='" + cognome + '\'' +
                ", id=" + iddocente +
                '}';
    }
}
