package Servlet;

import DAO.DAO;
import DAO.CorsoDocente;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "corsi_docenti", value = "/corsi_docenti")
public class Corsi_Docenti extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action != null && action.equals("Corsi_Docenti")) {
            response.setContentType("application/json,charset=UTF-8");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            ArrayList<CorsoDocente> corsi_docenti = DAO.CourseTeacher();
            String s = gson.toJson(corsi_docenti);
            request.setAttribute("risultato", s);
            String jsessionID = session.getId(); // estraggo il session ID
            System.out.println("JSessionID:" + jsessionID);
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        String nomecorso = request.getParameter("courses");
        String var2 = request.getParameter("teacherN");
        String var3 = request.getParameter("teacherC");
        if (nomecorso != null || (var2 != null && var3 != null)) {
            HttpSession session = request.getSession();

            if (session.getAttribute("userRole").equals("Amministratore")) {
                if (!nomecorso.equals("") && (!var2.equals("") && !var3.equals(""))) {
                    DAO.insertCourse(nomecorso);
                    DAO.insertTeacher(var2, var3);
                    DAO.insertCourseTeacher(nomecorso, var2 + " " + var3);
                    request.setAttribute("risultato", "aggiunti");
                } else {
                    request.setAttribute("risultato", "errore");
                }
            }
        }
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

