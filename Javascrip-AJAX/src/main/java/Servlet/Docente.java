package Servlet;

import DAO.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Docente", value = "/Docente")
public class Docente extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        Integer idDocente = Integer.parseInt(request.getParameter("id"));
        String nomeDocente = request.getParameter("nome");
        String cognomeDocente = request.getParameter("cognome");

        if (idDocente != null && nomeDocente != null && cognomeDocente != null) {
            DAO.insertTeacher(nomeDocente, cognomeDocente, idDocente);
        }
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String home = response.encodeURL("HomeAmministratore.html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<p><span class=\"badge badge-success\">Success</span> Docente aggiunto nel Database!<p>");
            out.flush();
        } finally {
            out.close();
        }
    }
}

