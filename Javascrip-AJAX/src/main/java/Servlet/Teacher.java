package Servlet;

import DAO.DAO;
import DAO.Docente;
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

@WebServlet(name = "Teacher", value = "/Teacher")
public class Teacher extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action.equals("Docente")) {
            response.setContentType("text/html,charset=UTF-8");
            Integer idDocente = Integer.parseInt(request.getParameter("id"));
            String nomeDocente = request.getParameter("nome");
            String cognomeDocente = request.getParameter("cognome");

            if (idDocente != null && nomeDocente != null && cognomeDocente != null) {
                DAO.insertTeacher(nomeDocente, cognomeDocente, idDocente);
            }
            processRequest(request, response);
        }else{
            processRequest(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action.equals("Docente")) {
            PrintWriter out = response.getWriter();
            try {
                out.println("<p><span class=\"badge badge-success\">Success</span> Docente aggiunto nel Database!<p>");
                out.flush();
            } finally {
                out.close();
            }
        }else {
            response.setContentType("application/json,charset=UTF-8");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.println("<p>Lista delle possibili ripetizioni da inserire: </p>");
            ArrayList<Docente> prova = DAO.Teacher();
            String s = gson.toJson(prova);
            System.out.println("STRINGA JSON " + s);
            out.print(s);
            out.flush();
            out.close();
        }
    }
}

