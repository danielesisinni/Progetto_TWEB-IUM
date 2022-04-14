package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DAO{

    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";


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
            ResultSet rs = st.executeQuery("SELECT * FROM Corso");
            while (rs.next()) {
                Corso p = new Corso(rs.getInt("ID"),rs.getString("TITOLO"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Docente");
            while (rs.next()) {
                Docente p = new Docente(rs.getInt("ID"), rs.getString("NOME"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM corsodocente");
            while (rs.next()) {
                CorsoDocente p = new CorsoDocente(rs.getString("CORSO"), rs.getString("DOCENTE"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Prenotazione ORDER BY giorno, ora");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("UTENTE"),rs.getString("CODICE"),rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"),rs.getString("STATUS"));
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

    public static ArrayList<Ripetizione> Repetition(String account) {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }


            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Ripetizione r WHERE NOT EXISTS (SELECT * FROM Prenotazione p WHERE r.CODICE = p.CODICE AND P.UTENTE = '"+ account + "') ORDER BY giorno, ora ");
            while (rs.next()) {
                //System.out.println("prova->" + rs.getString("UTENTE"));
                //if(!rs.getString("UTENTE").equals(account)){
                    Ripetizione p = new Ripetizione(rs.getString("CODICE"), rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
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

    public static boolean insertCourse(String title) {
        Connection conn1 = null;
        Corso c = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int id = 0;
            int max = id;

            ArrayList<Corso> cour = Course();
            for(Corso cour2: cour){
                if(title.equals(cour2.getTitolo()) || title.equals(""))
                    return false;
                id = cour2.getId();
                if(id >= max)
                    max = id;
            }
            System.out.println("max->"+ max);
            c = new Corso(max++, title);
            System.out.println("max->"+ max);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corso (id,titolo) values ('" + max++ + "', '" + title + "')");
            System.out.println("New course added!");

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
        return true;
    }

    public static void removeCourse(String nome) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from corso where titolo = '" + nome + "'");
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

    public static boolean insertTeacher(String nomeDocente) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int id;
            ArrayList<Docente> teach = Teacher();
            for(Docente teach2: teach){
                if(nomeDocente.equals(teach2.getNome()) || (nomeDocente.equals("")))
                    return false;
            }

            do {
                id = (int) (Math.random() * 500);
            } while(TeacherId().contains(id));

            Docente c = new Docente(id ,nomeDocente);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (id,nome) values ('" + id + "','" + nomeDocente + "')");
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
        return true;
    }

    public static void removeTeacher(String nome) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from docente where nome = '" + nome + "'");
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

    public static void insertCourseTeacher(String course, String docente) {
        Connection conn1 = null;
        CorsoDocente c = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            c = new CorsoDocente(course, docente);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corsodocente (corso, docente) values ('" + course + "', '" + docente + "')");
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

    public static void removeCourseTeacher(String corso, String docente) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);


            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from corsodocente where corso = '" + corso + "' and docente = '" + docente + "'");
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

            c = new Prenotazione(utente, codice, docente, corso, giorno, ora, status);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into prenotazione (utente, codice, docente, corso, giorno, ora, status) values ('" + utente + "', '" + codice + "', '" + docente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + status + "')");
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
            }else {
                stat = "EFFETTUATA";
            }
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("update prenotazione set status = '" + stat + "' where utente = '" + utente + "' and codice = '" + codice + "'");
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

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from prenotazione where utente = '" + utente + "' and codice = '" + codice + "'");
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

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from ripetizione where codice = '" + codice + "'");
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

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM prenotazione WHERE UTENTE = '"+ users + "' ORDER BY giorno, ora");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("UTENTE"), rs.getString("CODICE"),rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
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
}


