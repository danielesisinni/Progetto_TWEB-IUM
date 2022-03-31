package DAO;

public class Utenti{
    private int matricola;
    private String email;
    private String account;
    private String password;
    private String ruolo;

    public Utenti(int matricola, String account, String email, String password, String ruolo) {
        this.matricola = matricola;
        this.email = email;
        this.account = account;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                '}';
    }
}
