package Servlet;

import DAO.DAO;
import DAO.CorsoDocente;
import DAO.Ripetizione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "Ripetizioni", value = "/Ripetizioni")
public class Ripetizioni extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        String docente = request.getParameter("docente");
        String corso = request.getParameter("corso");
        String data = request.getParameter("data");
        String ora = request.getParameter("ora");
        String status = request.getParameter("attesa");

        if (docente != null && corso != null && data != null && ora != null) {
            DAO.insertRepetition(docente, corso, data, ora, status);
        }
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        String type1 = request.getParameter("type1");

        if(type1.equals("corso-docente")){
            PrintWriter out = response.getWriter();
            out.println("<p>Lista delle possibili ripetizioni da inserire: <p>");
            ArrayList<CorsoDocente> prova = DAO.CourseTeacher();
            for (CorsoDocente cd : prova) {
                out.println("[" + cd.getCorso() + "--" + cd.getDocente() + "]<br>");
            }
            out.flush();
        }
        String type2 = request.getParameter("type2");
        if(type2.equals("ripetizioni")){
            PrintWriter out = response.getWriter();
            out.println("<p>Lista delle possibili ripetizioni da inserire: <p>");
            ArrayList<Ripetizione> prova2 =DAO.Repetition();
            for(Ripetizione r: prova2){
                out.println("[" + r.getCorso() +"--"+ r.getDocente()+ "--"+ r.getData()+ "--"+ r.getOra()+"]<br>");
            }
            out.flush();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        String home = response.encodeURL("HomeAmministratore.html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<p><span class=\"badge badge-success\">Success</span> Ripetizione aggiunta nel Database!<p>");
            out.println("<p><a href=\"" + home + "\"> Torna al men&ugrave; amministratore</a></p>");
            out.flush();
        } finally {
            out.close();
        }
    }
}

