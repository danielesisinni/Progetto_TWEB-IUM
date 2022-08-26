package DAO;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class DAO{

    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";


    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static ArrayList<Corso> Course() {
        Connection conn1 = null;
        ArrayList<Corso> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Corso order by STATO, TITOLO");
            while (rs.next()) {
                Corso p = new Corso(rs.getInt("ID"),rs.getString("TITOLO"), rs.getString("STATO"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Docente> Teacher() {
        DAO.registerDriver();
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Docente order by STATO, NOME");
            while (rs.next()) {
                Docente p = new Docente(rs.getInt("ID"), rs.getString("NOME"), rs.getString("STATO"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<CorsoDocente> CourseTeacher() {
        Connection conn1 = null;
        ArrayList<CorsoDocente> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT c.TITOLO, d.NOME, cd.STATO FROM corso c, docente d, corsodocente cd WHERE c.ID = cd.CORSO AND d.ID = cd.DOCENTE");
            while (rs.next()) {
                CorsoDocente p = new CorsoDocente(rs.getString("TITOLO"), rs.getString("NOME"), rs.getString("STATO"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return out;
    }

    public static ArrayList<Utenti> Users(){
        Connection conn1 = null;
        ArrayList<Utenti> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utenti");
            while (rs.next()) {
                Utenti p = new Utenti(rs.getInt("MATRICOLA"), rs.getString("EMAIL"), rs.getString("ACCOUNT"), rs.getString("PASSWORD"), rs.getString("RUOLO"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Prenotazione> Booking() {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.utente, p.codice, d.nome, c.titolo, p.giorno, p.ora, p.status FROM Prenotazione p, corso c, docente d WHERE p.docente = d.id AND p.corso = c.id ORDER BY giorno, ora");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getInt("UTENTE"),rs.getString("CODICE"),rs.getString("NOME"), rs.getString("TITOLO"), rs.getString("GIORNO"), rs.getString("ORA"),rs.getString("STATUS"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Ripetizione> Repetition() {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }


            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT r.codice, d.nome, c.titolo, r.giorno, r.ora, r.status FROM Ripetizione r, corso c, docente d WHERE r.docente = d.id AND c.id = r.corso ORDER BY STATUS, giorno, ora ");
            while (rs.next()) {
                //System.out.println("prova->" + rs.getString("UTENTE"));
                //if(!rs.getString("UTENTE").equals(account)){
                Ripetizione p = new Ripetizione(rs.getString("CODICE"), rs.getString("NOME"), rs.getString("TITOLO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
                out.add(p);
                //}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Ripetizione> RepetitionPersonali(String account) {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            int matr = SearchMatricola(account);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT r.CODICE, d.NOME, c.TITOLO, r.GIORNO, r.ORA, r.STATUS FROM Ripetizione r, corso c, docente d WHERE d.id = r.DOCENTE AND c.id = r.CORSO and NOT EXISTS (SELECT  * FROM Prenotazione p WHERE r.CODICE = p.CODICE AND P.UTENTE = '"+ matr + "') ORDER BY giorno, ora");
            while (rs.next()) {
                //System.out.println("prova->" + rs.getString("UTENTE"));
                //if(!rs.getString("UTENTE").equals(account)){
                    Ripetizione p = new Ripetizione(rs.getString("CODICE"), rs.getString("NOME"), rs.getString("TITOLO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
                    out.add(p);
                //}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void insertRepetition(String docente, String corso, String giorno, String ora, String status) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            String codice;

            do {
                codice = "#" + Math.round(Math.random() * 500);
            } while(RepetitionCod().contains(codice));

            Ripetizione r = new Ripetizione(codice, docente, corso, giorno, ora, status);

            docente = searchTeacher(docente);
            corso = searchCourse(corso);

            Statement st = conn1.createStatement();
            st.execute("insert into ripetizione (codice, docente, corso, giorno, ora, status) values ('" + codice + "', '" + docente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + status + "')");
            System.out.println("New repetition added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static String insertCourse(String title) {
        Connection conn1 = null;
        ArrayList<Corso> c = null;
        String stato = "ATTIVO";
        int id = -1;
        int max = id;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            c = Course();
            for(Corso cc: c){
                if(cc.getTitolo().equals(title))
                    return "-1";
            }
            ArrayList<Corso> cour = Course();
            for(Corso cour2: cour){
                if(title.equals(""))
                    return "-1";
                id = cour2.getId();
                if(id >= max)
                    max = id;
            }

            //Execute insert query
            Statement st = conn1.createStatement();
            max++;
            st.execute("insert into corso (id,titolo, stato) values ('" + max + "', '" + title + "', '" + stato + "')");
            System.out.println("New course added! ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return String.valueOf(max);
    }

    public static void removeCourse(String nome) {
        Connection conn1 = null;
        String a = "RIMOSSO";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update corso set stato = '" + a + "' where titolo = '" + nome + "'");
            System.out.println("Course removed!");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static String insertTeacher(String nomeDocente) {
        Connection conn1 = null;
        String stato = "ATTIVO";
        int id = -1;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            if(nomeDocente.equals(""))
                return "-1";

            do {
                id = (int) (Math.random() * 500);
            } while(TeacherId().contains(id));

            Docente c = new Docente(id ,nomeDocente, stato);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (id, nome, stato) values ('" + id + "','" + nomeDocente + "', '" + stato + "')");
            System.out.println("New teacher added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return String.valueOf(id);
    }

    public static void removeTeacher(String nome) {
        Connection conn1 = null;
        String a = "RIMOSSO";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update docente set stato = '" + a + "' where nome = '" + nome + "'");
            System.out.println("Teacher removed!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void insertCourseTeacher(String course, String docente, boolean check) {
        Connection conn1 = null;
        String stato = "ATTIVO";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if(check){
                course = searchCourse(course);
                docente = searchTeacher(docente);
            }
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corsodocente (corso, docente, stato) values ('" + course + "', '" + docente + "', '" + stato + "')");
            System.out.println("New courseteacher added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static String searchCourse(String course) {
        ArrayList<Corso> out = Course();
        String tmp = "";
        for(Corso c: out){
            if(c.getTitolo().equals(course)){
                return String.valueOf(c.getId());
            }
        }
        return tmp;
    }

    public static String searchTeacher(String teacher) {
        ArrayList<Docente> out = Teacher();
        String tmp = "";
        for(Docente d: out){
            if(d.getNome().equals(teacher)){
                return String.valueOf(d.getIddocente());
            }
        }
        return tmp;
    }

    public static void updateCourseTeacher(String var, String var2) {
        Connection conn1 = null;
        String a = "RIMOSSO";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            var = searchCourse(var);
            var2 = searchTeacher(var2);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update corsodocente set stato = '" + a + "' where corso = '" + var + "' and docente = '" + var2 + "'");
            System.out.println("Courseteacher updated!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void removeCourseTeacher(String var) {
        Connection conn1 = null;
        String a = "RIMOSSO";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            String tmp = var;
            var = searchCourse(var);
            tmp = searchTeacher(tmp);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update corsodocente set stato = '" + a + "' where corso = '" + var + "' or docente = '" + tmp + "'");
            System.out.println("Courseteacher removed!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void insertUsers(String email, String account, String passwordutente) {
        DAO.registerDriver();
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            int matricola;

            do {
                matricola = (int) (Math.random() * 500);
            } while(UsersMatr().contains(matricola));

            String ruolo = "Cliente";

            Utenti u = new Utenti(matricola, email, account, passwordutente, ruolo);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into utenti (matricola, email, account, password, ruolo) values ('" + matricola + "', '" + email + "', '" + account + "', '" + passwordutente + "', '" + ruolo + "')");
            System.out.println("New users added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void removeUsers() {
        Connection conn1 = null;
        String matricola;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the serial number to be deleted: ");
            matricola = input.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from utenti where matricola = '" + matricola + "'");
            System.out.println("User removed!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }


    public static void insertBooking(String utente, String codice,String docente, String corso, String giorno, String ora, String status) {
        Connection conn1 = null;

        Prenotazione c = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            int matr = SearchMatricola(utente);
            c = new Prenotazione(matr, codice, docente, corso, giorno, ora, status);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into prenotazione (utente, codice, docente, corso, giorno, ora, status) values ('" + matr + "', '" + codice + "', '" + docente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + status + "')");
            System.out.println("New booking added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void updateBooking(String utente, String codice, String action) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            String stat;
            if(action.equals("Disdetta")){
                stat = "RIMOSSA";
            }else if(action.equals("Riprenota")){
                stat = "ATTIVA";
            }else{
                stat = "EFFETTUATA";
            }
            int matr = SearchMatricola(utente);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update prenotazione set status = '" + stat + "' where utente = '" + matr + "' and codice = '" + codice + "'");
            System.out.println("Booking update!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void removeBooking(String utente, String codice) {
        Connection conn1 = null;
        String e1 = "EFFETTUATA";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            int matr = SearchMatricola(utente);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update prenotazione set status = '" + e1 + "' where utente = '" + matr + "' and codice = '" + codice + "'");
            System.out.println("Booking removed!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void removeRepetition(String codice) {
        Connection conn1 = null;
        String r1 = "RIMOSSA";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update ripetizione set status = '" + r1 + "' where codice = '" + codice + "'");
            System.out.println("Repetition removed!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static String[] verificaUtenti(String email, String password) {
        DAO.registerDriver();
        ArrayList<Utenti> utentiCreati = DAO.Users();
        String[] esiste_ruolo = {"false", ""};
        for( Utenti p: utentiCreati){
            if(p.getEmail().equals(email) && p.getPassword().equals(password)){
                esiste_ruolo[0] = "true";
                esiste_ruolo[1] = p.getRuolo();
            }
        }
        return esiste_ruolo;
    }

    public static ArrayList<Integer> UsersMatr(){
        Connection conn1 = null;
        ArrayList<Integer> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT MATRICOLA FROM utenti");
            while (rs.next()) {
                out.add(rs.getInt("MATRICOLA"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Integer> TeacherId(){
        Connection conn1 = null;
        ArrayList<Integer> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID FROM docente");
            while (rs.next()) {
                out.add(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<String> RepetitionCod() {
        Connection conn1 = null;
        ArrayList<String> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODICE FROM ripetizione");
            while (rs.next()) {
                out.add(rs.getString("CODICE"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Prenotazione> MyBooking(String users) {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            int matricola = SearchMatricola(users);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.CODICE, p.UTENTE, d.NOME, c.TITOLO, p.GIORNO, p.ORA, p.STATUS FROM prenotazione p, corso c, docente d WHERE c.ID = p.CORSO AND d.ID = p.DOCENTE AND p.UTENTE = '"+ matricola + "' ORDER BY giorno, ora");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getInt("UTENTE"), rs.getString("CODICE"),rs.getString("NOME"), rs.getString("TITOLO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Ripetizione> SearchRepetition(String cod) {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ripetizione WHERE CODICE = '"+ cod + "'");
            while (rs.next()) {
                Ripetizione p = new Ripetizione(rs.getString("CODICE"), rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static int SearchMatricola(String email) throws SQLException {
        Connection conn1 = null;
        ResultSet rs = null;
        ArrayList<Utenti> out = new ArrayList<>();
        int matr = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            rs = st.executeQuery("SELECT * FROM utenti WHERE EMAIL = '"+ email + "'");

            while (rs.next()) {
                Utenti p = new Utenti(rs.getInt("MATRICOLA"), rs.getString("EMAIL"), rs.getString("ACCOUNT"), rs.getString("PASSWORD"), rs.getString("RUOLO"));
                out.add(p);
                matr = rs.getInt("MATRICOLA");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return matr;
    }

    public static ArrayList<CorsoDocente> CourseTeacherFree() {
        Connection conn1 = null;
        ArrayList<CorsoDocente> out = new ArrayList<>();
        String c2 = "CONFERMATA";
        String a2 = "ATTIVO";
        String a3 = "ATTIVA";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT c.titolo, d.nome FROM corso c, docente d, corsodocente cd where cd.corso = c.id AND cd.docente = d.id and cd.STATO = '"+ a2 +"' AND NOT EXISTS (select 1 from prenotazione p where p.CORSO = cd.corso and p.docente = cd.docente and P.STATUS = '" + a3 +"') order by TITOLO");
            while (rs.next()) {
                CorsoDocente p = new CorsoDocente(rs.getString("TITOLO"), rs.getString("NOME"), "ATTIVO");
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Corso> CourseFree(String action2) {
        Connection conn1 = null;
        ArrayList<Corso> out = new ArrayList<>();
        String c2 = "CONFERMATA";
        String a2 = "ATTIVO";
        String a3 = "ATTIVA";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            ResultSet rs;
            Statement st = conn1.createStatement();
            if(action2.equals("Free")) {
                rs = st.executeQuery("SELECT c.titolo FROM corso c where c.STATO = '"+ a2 +"' AND NOT EXISTS (select 1 from prenotazione p where p.CORSO = c.ID and P.STATUS = '" + a3 +"') order by TITOLO");
            }else {
                rs = st.executeQuery("SELECT TITOLO FROM Corso c where c.stato = '" + a2 + "' order by TITOLO");
            }while (rs.next()) {
                Corso p = new Corso(0,rs.getString("TITOLO"), "ATTIVO");
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Docente> TeacherFree(String action2) {
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        String c1 = "CONFERMATA";
        String a1 = "ATTIVO";
        String a3 = "ATTIVA";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            ResultSet rs;
            Statement st = conn1.createStatement();
            if(action2.equals("Free")) {
                rs = st.executeQuery("SELECT d.nome FROM docente d where d.STATO = '" + a1 + "' AND NOT EXISTS (select 1 from prenotazione p where p.docente = d.ID and P.STATUS = '" + a3 + "') order by NOME");
            }else {
                rs = st.executeQuery("SELECT NOME FROM Docente d WHERE d.stato = '" + a1 + "' order by NOME");
            }while (rs.next()) {
                Docente p = new Docente(0,rs.getString("NOME"), "ATTIVO");
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Ripetizione> RepetitionA() {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT r.codice, d.nome, c.titolo, r.giorno, r.ora, r.status FROM ripetizione r, corso c, docente d WHERE c.id = r.corso AND d.id = r.docente AND r.STATUS != 'RIMOSSA' AND NOT EXISTS (SELECT * FROM Prenotazione p WHERE r.CODICE = p.CODICE AND p.STATUS = 'ATTIVA') order by codice");
            while (rs.next()) {
                Ripetizione p = new Ripetizione(rs.getString("CODICE"),rs.getString("NOME"), rs.getString("TITOLO"), rs.getString("GIORNO"), rs.getString("ORA"), "DISPONIBILE");
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static boolean logout(Timestamp lastUpdate){
        if ((new Timestamp(System.currentTimeMillis())).after(new Timestamp(lastUpdate.getTime()  + TimeUnit.MINUTES.toMillis(1)))) {
           return true;
        } else {
            return false;
        }
    }
}


