package Servlet;

import DAO.*;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet(name = "corsi_docenti", value = "/corsi_docenti")
public class CorsiDocentiServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        response.setContentType("application/json,charset=UTF-8");
        RequestDispatcher rd;
        boolean check = DAO.logout((Timestamp) session.getAttribute("lastUpdate"));
        if (check) {
            rd = getServletContext().getNamedDispatcher("logout");
            rd.include(request, response);
            return;
        } else {
            session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
        }
        Gson gson = new Gson();
        String action = request.getParameter("action");
        String action2 = request.getParameter("action2");
        if (action != null) {
            switch (action) {
                case "Corsi_Docenti":
                    if (action2.equals("Free")) {
                        ArrayList<CorsoDocente> corsi_docenti = DAO.CourseTeacherFree();
                        String s = gson.toJson(corsi_docenti);
                        request.setAttribute("risultato", s);
                    } else {
                        ArrayList<CorsoDocente> corsi_docenti = DAO.CourseTeacher();
                        String s = gson.toJson(corsi_docenti);
                        request.setAttribute("risultato", s);
                    }
                    break;
                case "Corsi":
                    if (request.getParameter("action2").equals("storico")) {
                        ArrayList<Corso> corsi = DAO.Course();
                        String s1 = gson.toJson(corsi);
                        request.setAttribute("risultato", s1);
                    } else {
                        ArrayList<Corso> corsi = DAO.CourseFree(action2);
                        String s1 = gson.toJson(corsi);
                        request.setAttribute("risultato", s1);
                    }
                    break;
                case "Docenti":
                    if (request.getParameter("action2").equals("storico")) {
                        ArrayList<Docente> docenti = DAO.Teacher();
                        String s2 = gson.toJson(docenti);
                        request.setAttribute("risultato", s2);
                    } else {
                        ArrayList<Docente> docenti = DAO.TeacherFree(action2);
                        String s2 = gson.toJson(docenti);
                        request.setAttribute("risultato", s2);
                    }
                    break;
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        boolean check1 = DAO.logout((Timestamp) session.getAttribute("lastUpdate"));
        if (check1) {
            rd = getServletContext().getNamedDispatcher("logout");
            rd.include(request, response);
            return;
        } else {
            session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
        }
        if (session.getAttribute("userRole").equals("Amministratore") && request.getParameter("action2").equals("Aggiunta")) {
            String nomecorso = request.getParameter("corsi");
            String docente = request.getParameter("nome");
            if (nomecorso != null && docente != null) {
                String check = DAO.insertCourse(nomecorso);
                String check2 = DAO.insertTeacher(docente);
                if (!check.equals("-1") && !check2.equals("-1")) {
                    DAO.insertCourseTeacher(check, check2, false);
                    request.setAttribute("risultato", "aggiunto");
                } else if (!check.equals("-1") || !check2.equals("-1")) {
                    request.setAttribute("risultato", "aggiunto");
                } else {
                    request.setAttribute("risultato", "errore");
                }
            }
        } else if (session.getAttribute("userRole").equals("Amministratore") && request.getParameter("action2").equals("Affiliare")) {
            String corso = request.getParameter("corsii");
            String docente = request.getParameter("nomee");
            DAO.insertCourseTeacher(corso, docente, true);
            request.setAttribute("risultato", "aggiunto");
        } else if (session.getAttribute("userRole").equals("Amministratore") && (!request.getParameter("action3").equals("corso") && !request.getParameter("action3").equals("docente"))) {
            System.out.println("ok " + request.getParameter("action2") + " " + request.getParameter("action3"));
            String idcorso = request.getParameter("action2");
            String iddocente = request.getParameter("action3");
            DAO.updateCourseTeacher(idcorso, iddocente);
            request.setAttribute("risultato", "rimossa");
        } else if (session.getAttribute("userRole").equals("Amministratore")) {
            String var = request.getParameter("action2");
            String var2 = request.getParameter("action3");
            if (var2.equals("corso")) {
                DAO.removeCourse(var);
            } else if (var2.equals("docente")) {
                DAO.removeTeacher(var);
            }
            DAO.removeCourseTeacher(var);
            request.setAttribute("risultato", "rimossa");
        } else {
            request.setAttribute("risultato", "errore");
        }
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

