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

@WebServlet(name = "docenti", value = "/docenti")
public class Docenti extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("get");
        HttpSession session = request.getSession();
        if(session.getAttribute("userRole").equals("Amministratore")){
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
        System.out.println("post");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(session.getAttribute("userRole").equals("Amministratore")){
            PrintWriter out = response.getWriter();
            try {
                out.println("<p><span class=\"badge badge-success\">Success</span> Docente aggiunto nel Database!<p>");
                out.flush();
            } finally {
                out.close();
            }
        }if(action.equals("Docenti")){
            response.setContentType("application/json,charset=UTF-8");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            ArrayList<Docente> docente = DAO.Teacher();
            String s = gson.toJson(docente);
            request.setAttribute("risultato", s);
            String jsessionID = session.getId(); // estraggo il session ID
            System.out.println("JSessionID:" + jsessionID);
        }
    }
}

