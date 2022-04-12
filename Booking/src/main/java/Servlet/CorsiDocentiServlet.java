package Servlet;

import DAO.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "corsi_docenti", value = "/corsi_docenti")
public class CorsiDocentiServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        String action = request.getParameter("action");

        if (action != null){
            switch (action){
                case "Corsi_Docenti":
                    ArrayList<CorsoDocente> corsi_docenti = DAO.CourseTeacher();
                    String s = gson.toJson(corsi_docenti);
                    request.setAttribute("risultato", s);
                    break;
                case "Corsi":
                    ArrayList<Corso> corsi = DAO.Course();
                    String s1 = gson.toJson(corsi);
                    request.setAttribute("risultato", s1);
                    break;
                case "Docenti":
                    ArrayList<Docente> docenti = DAO.Teacher();
                    String s2 = gson.toJson(docenti);
                    request.setAttribute("risultato", s2);
                    break;
            }
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("userRole").equals("Amministratore") && request.getParameter("action2").equals("Aggiunta")) {
            String nomecorso = request.getParameter("corsi");
            String var2 = request.getParameter("nome");
            String var3 = request.getParameter("cognome");
            if(nomecorso != null && var2 != null && var3 != null){
                boolean check = DAO.insertCourse(nomecorso);
                boolean check2 = DAO.insertTeacher(var2, var3);
                if(check && check2){
                    DAO.insertCourseTeacher(nomecorso, var2 + " " + var3);
                    request.setAttribute("risultato", "aggiunti");
                } else {
                    request.setAttribute("risultato", "errore");
                }
            }
        }else if (session.getAttribute("userRole").equals("Amministratore") && request.getParameter("action2").equals("Rimozione")) {
            String docente = request.getParameter("docEli");
            String corso = request.getParameter("corEli");
            if(docente != null && corso != null){

            }
        }
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

