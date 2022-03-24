package Servlet;

import DAO.DAO;
import DAO.Corso;
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

@WebServlet(name = "Corsi", value = "/Corsi")
public class Corsi extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action != null) {
            PrintWriter out = response.getWriter();
            try {
                out.println("<p><span class=\"badge badge-success\">Success</span> Corso aggiunto nel Database!<p>");
                out.flush();
            } finally {
                out.close();
            }
        }else {
            response.setContentType("application/json,charset=UTF-8");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            try {
                out.println("Lista dei corsi registrati: ");
                ArrayList<Corso> corso = DAO.Course();
                String s = gson.toJson(corso);
                System.out.println("STRINGA JSON " + s);
                out.print(s);
                out.flush();
            }finally {
                out.close();
            }
        }
    }
}

