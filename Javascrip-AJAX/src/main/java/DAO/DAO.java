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
                Docente p = new Docente(rs.getString("NOME"), rs.getString("COGNOME"), rs.getInt("ID"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM CorsoDocente");
            while (rs.next()) {
                CorsoDocente p = new CorsoDocente(rs.getString("CORSO"), rs.getInt("DOCENTE"));
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
        System.out.println(out);
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
                Prenotazione p = new Prenotazione(rs.getInt("DOCENTE"), rs.getInt("CORSO"), rs.getInt("UTENTE"));
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
                Ripetizione p = new Ripetizione(rs.getString("DOCENTE"), rs.getInt("ID_DOCENTE"), rs.getString("CORSO"), rs.getString("DATA"), rs.getString("ORA"));
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

    public static void insertCourse() {
        Connection conn1 = null;
        String title;
        int id;
        Corso c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert a title: ");
            title = input.nextLine();
            System.out.println("Insert a id: ");
            id = input.nextInt();
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

    public static void insertTeacher() {
        Connection conn1 = null;
        String name;
        String surname;
        int id;
        Docente c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert a name of teacher: ");
            name = input.nextLine();
            System.out.println("Insert a surname of teacher: ");
            surname = input.nextLine();
            System.out.println("Insert a id of teacher: ");
            id = input.nextInt();
            c = new Docente(name, surname, id);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (nome, cognome, id) values ('" + name + "', '" + surname + "', '" + id + "')");
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

    public static void insertTeacher(String nomeDocente, String cognomeDocente, Integer id) {
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Docente c = new Docente(nomeDocente, cognomeDocente, id);
            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (nome, cognome, idutente) values ('" + nomeDocente + "', '" + cognomeDocente + "', '" + id + "')");
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

    public static void insertCourseTeacher() {
        Connection conn1 = null;
        String course;
        int id;
        CorsoDocente c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert a course: ");
            course = input.nextLine();
            System.out.println("Insert a id of teacher: ");
            id = input.nextInt();
            c = new CorsoDocente(course, id);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corsodocente (corso, docente) values ('" + course + "', '" + id + "')");
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

    public static void insertUsers() {
        Connection conn1 = null;
        int matricola;
        String account;
        String password_user;
        String role;
        Utenti c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert serial number: ");
            matricola = input.nextInt();
            System.out.println("Insert account: ");
            account = input.nextLine();
            System.out.println("Insert a password: ");
            password_user = input.nextLine();
            System.out.println("Insert a role: ");
            role = input.nextLine();

            c = new Utenti(matricola, account, password_user, role);


            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into utenti (matricola, account, password, role) values ('" + matricola + "', '" + account + "', '" + password_user + "', '" + role + "')");
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

    /*public static void insertRepetition(){
        Connection conn1 = null;
        int id_teacher;
        String course, date, hour, place;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the ID number of the teacher: ");
            id_teacher = input.nextInt();
            System.out.println("Insert the course title: ");
            course = input.nextLine();
            System.out.println("Insert the date of the lesson: ");
            date = input.nextLine();
            System.out.println("Insert the hour of the lesson: ");
            hour = input.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into ripetizione (docente, corso, data, ora " +
                    "values ('" + id_teacher + "', '" + course + "', '" + date + "', '" + hour + "')");
            System.out.println("Repetition added!");

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

    public static void removeRepetition(){
        Connection conn1 = null;
        int id_teacher;
        String course, date, hour;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert the ID number of the teacher: ");
            id_teacher = input.nextInt();
            System.out.println("Insert the course title: ");
            course = input.nextLine();
            System.out.println("Insert the date of the lesson: ");
            date = input.nextLine();
            System.out.println("Insert the hour of the lesson: ");
            hour = input.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from ripetizione where docente =  '" + id_teacher +
                    "' and corso = '" + course + "' and data = '" + date + "' and ora = '" + hour + "'");
            System.out.println("The booked lesson is deleted.");

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
    }*/

    public static void insertBooking() {
        Connection conn1 = null;
        int id_course;
        int id_user;
        int id_teacher;
        Prenotazione c = null;
        Scanner input = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            System.out.println("Insert a id of course: ");
            id_course = input.nextInt();
            System.out.println("Insert a id of teacher: ");
            id_teacher = input.nextInt();
            System.out.println("Insert a id of user: ");
            id_user = input.nextInt();
            c = new Prenotazione(id_teacher, id_course, id_user);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into prenotazione (docente, corso, utente) values ('" + id_teacher + "', '" + id_course + "', '" + id_user + "')");
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

    public static void removeBooking() {
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

    public static String verificaUtenti(String account, String password) {
        DAO.registerDriver();
        ArrayList<Utenti> utentiCreati = DAO.Users();
        String ruolo = "";
        for( Utenti p: utentiCreati){
            if(p.getAccount().equals(account) && p.getPassword().equals(password)){
                ruolo = p.getRuolo();
            }
        }
        return ruolo;
    }
}


