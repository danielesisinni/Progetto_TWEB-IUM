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
                Docente p = new Docente(rs.getString("NOME"), rs.getString("COGNOME"), rs.getInt("IDDOCENTE"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Prenotazione");
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
            ResultSet rs = st.executeQuery("SELECT * FROM Ripetizione");
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
                System.out.println("max->"+ max);
                System.out.println("id->"+ id);
            }
            System.out.println(max++);
            c = new Corso(max++, title);

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

    public static void removeCourse() {
        Connection conn1 = null;
        int id;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the id of the course to be deleted: ");
            id = input.nextInt();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from corso where id = '" + id + "'");
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

    public static boolean insertTeacher(String nomeDocente, String cognomeDocente) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int id;

            ArrayList<Docente> teach = Teacher();
            for(Docente teach2: teach){
                if(nomeDocente.equals(teach2.getNome()) && cognomeDocente.equals(teach2.getCognome()))
                    return false;
            }

            do {
                id = (int) (Math.random() * 500);
            } while(TeacherId().contains(id));

            Docente c = new Docente(nomeDocente, cognomeDocente, id);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (nome, cognome, iddocente) values ('" + nomeDocente + "', '" + cognomeDocente + "', '" + id + "')");
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

    public static void removeTeacher() {
        Connection conn1 = null;
        int id;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the id of teacher to be deleted: ");
            id = input.nextInt();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from docente where id = '" + id + "'");
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

    public static void removeCourseTeacher() {
        Connection conn1 = null;
        String course;
        int id;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the course to be deleted: ");
            course = input.nextLine();
            System.out.println("Insert the id of the teacher to be deleted: ");
            id = input.nextInt();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from corsodocente where corso = '" + course + "' and docente = '" + id + "'");
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
            ResultSet rs = st.executeQuery("SELECT IDDOCENTE FROM docente");
            while (rs.next()) {
                out.add(rs.getInt("IDDOCENTE"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM prenotazione WHERE UTENTE = '"+ users + "'");
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


