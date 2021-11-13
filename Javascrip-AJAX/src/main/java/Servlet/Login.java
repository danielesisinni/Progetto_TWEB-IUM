package Servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.*;
import com.google.gson.Gson;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    //private Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("userName", "ospite");

        String acc = request.getParameter("account");
        String pass = request.getParameter("password");
        String[] esiste_ruolo = DAO.verificaUtenti(acc, pass);
        if (esiste_ruolo[0].equals("true")) {
            session.setAttribute("userName", acc);
            session.setAttribute("userRole", esiste_ruolo[1]);
            processRequest(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<span class=\"badge badge-danger\">Errore nel login</span>");
            out.flush();
            out.close();
        }
        if(session.getAttribute("userName").equals("ospite"))
            processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            String action = (String) session.getAttribute("userName");
            if(action.equals("ospite")) {
                out.println("<span class=\"badge badge-success\">Success</span> Loggato come ospite");
                out.flush();
            }else {
                String acc = (String) session.getAttribute("userName");
                String ruolo = (String) session.getAttribute("userRole");
                out.print("<span class=\"badge badge-success\">Success</span>");
                out.print(" Benvenuto " + acc + " [" + ruolo + "]");
                out.flush();
            }
        } finally {
            if (out!=null)
                out.close();
        }
    }
}
