package Servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        String action = request.getParameter("action");
        if( action!= null){
            switch (action){
                case "Autentica":
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
                    break;
                case "Crea":
                    String newacc = request.getParameter("newaccount");
                    String newpass = request.getParameter("newpassword");
                    DAO.insertUsers(newacc, newpass);
                    processRequest(request, response);
                    break;
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String amministratore = response.encodeURL("HomeAmministratore.html");
        String cliente = response.encodeURL("Home.html");
        try {
            switch (action) {
                case "Autentica":
                    String acc = (String) session.getAttribute("userName");
                    String ruolo = (String) session.getAttribute("userRole");
                    out.print("<span class=\"badge badge-success\">Success</span>");
                    out.print(" Benvenuto " + acc + " [" + ruolo + "]");
                    if (ruolo.equals("Amministratore")) {
                        out.println("<p><a href=\"" + amministratore + "\"> Accedi al menù amministratore</a></p>");
                        out.flush();
                    } else {
                        out.println("<p><a href=\"" + cliente + "\"> Accedi al menù</a></p>");
                        out.flush();
                    }
                    break;
                case "Crea":
                    out.print("Cliente Benvenuto! ");
                    out.print("<span class=\"badge badge-success\">Success</span> Account creato");
                    out.flush();
                    break;
            }
        } finally {
            if (out!=null)
                out.close();
        }
    }
}