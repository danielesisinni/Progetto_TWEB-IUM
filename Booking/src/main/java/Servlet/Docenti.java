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

@WebServlet(name = "Docenti", value = "/Docenti")
public class Docenti extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action != null) {
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(action != null) {
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
            try {
                out.println("Lista dei docenti registrati: ");
                ArrayList<Docente> docente = DAO.Teacher();
                String s = gson.toJson(docente);
                System.out.println("STRINGA JSON " + s);
                out.print(s);
                String jsessionID = session.getId(); // estraggo il session ID
                System.out.println("JSessionID:" + jsessionID);
                out.print(jsessionID);
                out.flush();
            }finally {
                out.close();
            }
        }
    }
}
