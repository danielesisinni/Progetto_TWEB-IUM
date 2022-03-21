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
            response.setContentType("text/html,charset=UTF-8");
            /*Integer idcorso = Integer.parseInt(request.getParameter("id"));
            String nomecorso = request.getParameter("corso");

            if (nomecorso != null) {
                DAO.insertCourse(idcorso, nomecorso);
            }*/
            processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        /*if(action != null) {
            PrintWriter out = response.getWriter();
            try {
                out.println("<p><span class=\"badge badge-success\">Success</span> Corso aggiunto nel Database!<p>");
                out.flush();
            } finally {
                out.close();
            }
        }else {*/
        if (action.equals("Corsi_Docenti")) {
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
}

