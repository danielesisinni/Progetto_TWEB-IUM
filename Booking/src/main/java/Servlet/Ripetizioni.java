package Servlet;

import DAO.DAO;
import DAO.CorsoDocente;
import DAO.Ripetizione;
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
import java.util.Date;

@WebServlet(name = "Ripetizioni", value = "/Ripetizioni")
public class Ripetizioni extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action != null) {
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
        }else{
            processRequest(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*response.setContentType("text/html,charset=UTF-8");
        String type1 = request.getParameter("type1");

        if(type1.equals("corso-docente")){
            PrintWriter out = response.getWriter();
            out.println("Lista delle possibili ripetizioni da inserire: ");
            ArrayList<CorsoDocente> prova = DAO.CourseTeacher();
            out.flush();
        }*/
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null) {
            PrintWriter out = response.getWriter();
            try {
                out.println("<p><span class=\"badge badge-success\">Success</span> Ripetizione aggiunta nel Database!<p>");
                out.flush();
            } finally {
                out.close();
            }
        }else{
            response.setContentType("application/json,charset=UTF-8");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            try {
                out.println("Lista delle ripetizioni registrate: ");
                ArrayList<Ripetizione> ripetizione = DAO.Repetition();
                String s = gson.toJson(ripetizione);
                System.out.println("STRINGA JSON " + s);
                out.print(s);
                out.flush();
            }finally {
                out.close();
            }
        }
    }
}
