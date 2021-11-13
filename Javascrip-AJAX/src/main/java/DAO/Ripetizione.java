package DAO;

public class Ripetizione {
    private String docente;
    private String corso;
    private String data;
    private String ora;

    public Ripetizione(String docente, String corso, String data, String ora) {
        this.docente = docente;
        this.corso = corso;
        this.data = data;
        this.ora = ora;
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
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    @Override
    public String toString() {

        return "Docente='" + docente + '\'' +
                ", corso='" + corso + '\'' +
                ", data='" + data + '\'' +
                ", ora=" + ora +
                '}';
    }
}
