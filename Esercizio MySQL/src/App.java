import DAO.DAO;

import java.util.ArrayList;
import java.util.Scanner;
import DAO.*;

public class App{

    public static int operation() {
        int check = 1;
        System.out.println("Select the operation you want to perform in the database");
        System.out.println("-0) If you want exit.");
        System.out.println("-1) Insert the course titles in the catalog");
        System.out.println("-2) Delete the course titles in the catalog");
        System.out.println("-3) Insert the teacher ");
        System.out.println("-4) Delete the teacher ");
        System.out.println("-5) Insert the relationship between teacher and courses ");
        System.out.println("-6) Delete the relationship between teacher and courses ");
        System.out.println("-7) The printout of the list of possible repetitions");
        System.out.println("-8) Insert the repetition ");
        System.out.println("-9) Delete the repetition ");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        check = input.nextInt();
        if(check == 0){
            System.out.println("Bye.");
        }else if(check < 0 || check > 9){
            System.out.println("** Error in the number **");
        }

        switch (check) {
                case 1:
                    DAO.insertCourse();
                    printCourse();
                    break;
                case 2:
                    DAO.removeCourse();
                    printCourse();
                    break;
                case 3:
                    DAO.insertTeacher();
                    printTeacher();
                    break;
                case 4:
                    DAO.removeTeacher();
                    printTeacher();
                    break;
                case 5:
                    DAO.insertCourseTeacher();
                    printCourseTeacher();
                    break;
                case 6:
                    DAO.removeCourseTeacher();
                    printCourseTeacher();
                    break;
                case 7:
                    printRepetition();
                    break;
                case 8:
                    DAO.insertBooking();
                    break;
                case 9:
                    DAO.removeBooking();
                    break;

        }
            System.out.println("");

        return check;
    }

    public static void main(String[] args){
        boolean flag = true;
        int check = 0;

        while(flag) {
            check = operation();
            if(check == 0){
                flag = false;
            }
        }
        System.out.println("FINE");
    }

    public static void printCourse(){
        System.out.println("Try to access to course");
        ArrayList<Corso> Corsi = DAO.Course();
        int i = 0;
        for(Corso p: Corsi)
            System.out.println(p);
        System.out.println("");
    }

    public static void printTeacher(){
        System.out.println("Try to access to teacher");
        ArrayList<Docente> Docenti = DAO.Teacher();
        int i = 0;
        for(Docente p: Docenti)
            System.out.println(p);
        System.out.println("");
    }

    public static void printCourseTeacher(){
        System.out.println("Try to access to courseteacher");
        ArrayList<CorsoDocente> CorsiDocenti = DAO.CourseTeacher();
        int i = 0;
        for(CorsoDocente p: CorsiDocenti)
            System.out.println(p);
        System.out.println("");
    }

    public static void printUsers(){
        System.out.println("Try to access to users");
        ArrayList<Utenti> Utenti = DAO.Users();
        int i = 0;
        for(Utenti p: Utenti)
            System.out.println(p);
        System.out.println("");
    }

    public static void printRepetition(){
        System.out.println("Try to access to repetition");
        ArrayList<Ripetizione> ripetizioni = DAO.Repetition();
        int i = 0;
        for(Ripetizione p: ripetizioni)
            System.out.println(p);
        System.out.println("");
    }
}
