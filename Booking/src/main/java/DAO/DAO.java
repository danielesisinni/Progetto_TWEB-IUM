package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

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
                Utenti p = new Utenti(rs.getInt("MATRICOLA"), rs.getString("ACCOUNT"), rs.getString("PASSWORD"), rs.getString("RUOLO"));
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

    public static ArrayList<Prenotazione> Prenotazione() {
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
                Prenotazione p = new Prenotazione(rs.getString("UTENTE"),rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"),rs.getString("STATUS"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Ripetizione");
            while (rs.next()) {
                Ripetizione p = new Ripetizione(rs.getString("DOCENTE"), rs.getString("CORSO"), rs.getString("GIORNO"), rs.getString("ORA"), rs.getString("STATUS"));
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

    public static void insertRepetition(String docente, String corso, String giorno, String ora, String status) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Ripetizione r = new Ripetizione(docente, corso, giorno, ora, status);

            Statement st = conn1.createStatement();
            st.execute("insert into ripetizione (docente, corso, giorno, ora, status) values ('" + docente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + status + "')");
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

    public static void insertCourse(String title) {
        Connection conn1 = null;
        Corso c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int id = 0;
            ArrayList<Corso> cour = Course();
            for(Corso cour2: cour){
                id = cour2.getId();
            }
            id++;

            c = new Corso(id, title);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corso (id,titolo) values ('" + id + "', '" + title + "')");
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

    public static void insertTeacher(String nomeDocente, String cognomeDocente) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int id = (int) (Math.random() * 500);

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

    public static void insertUsers(String account, String passwordutente) {
        DAO.registerDriver();
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int matricola = (int) (Math.random() * 500);
            String ruolo = "Cliente";
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into utenti (matricola, account, password, ruolo) values ('" + matricola + "', '" + account + "', '" + passwordutente + "', '" + ruolo + "')");
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


    public static void insertPrenotazione(String utente, String docente, String corso, String giorno, String ora, String status) {
        Connection conn1 = null;

        Prenotazione c = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            c = new Prenotazione(utente, docente, corso, giorno, ora, status);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into prenotazione (utente, docente, corso, giorno, ora, status) values ('" + utente + "', '" + docente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + status + "')");
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

    public static void removePrenotazione() {
        Connection conn1 = null;
        int id_course;
        int id_user;
        int id_teacher;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert a id of course to delete: ");
            id_course = input.nextInt();
            System.out.println("Insert a id of teacher to delete: ");
            id_teacher = input.nextInt();
            System.out.println("Insert a id of user to delete: ");
            id_user = input.nextInt();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from prenotazione where docente = '" + id_teacher + "' and corso = '" + id_course + "' and utente = '" + id_user + "'");
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

    public static String[] verificaUtenti(String account, String password) {
        DAO.registerDriver();
        ArrayList<Utenti> utentiCreati = DAO.Users();
        String[] esiste_ruolo = {"false", ""};
        for( Utenti p: utentiCreati){
            if(p.getAccount().equals(account) && p.getPassword().equals(password)){
                esiste_ruolo[0] = "true";
                esiste_ruolo[1] = p.getRuolo();
            }
        }
        return esiste_ruolo;
    }
}


