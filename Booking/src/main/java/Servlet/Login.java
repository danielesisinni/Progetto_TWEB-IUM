package Servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.*;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("postttt");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String flag = request.getParameter("action");
        if(flag.equals("login")){
            String em = request.getParameter("email");
            String pass = request.getParameter("password");
            String[] esiste_ruolo = DAO.verificaUtenti(em, pass);
            if (esiste_ruolo[0].equals("true")) {
                session.setAttribute("userName", em);
                session.setAttribute("userRole", esiste_ruolo[1]);
                processRequest(request, response);
            } else {
                request.setAttribute("risultato", "errore");
            }
        }else if(flag.equals("crea")) {
            String newemail = request.getParameter("email");
            String newacc = request.getParameter("account");
            String newpass = request.getParameter("password");
            if(!newacc.equals("") && !newpass.equals("") && !newemail.equals("")){
                DAO.insertUsers(newemail, newacc, newpass);
                processRequest(request, response);
            }else{
                request.setAttribute("risultato", "errore2");
            }
        }else if(flag.equals("ospite")){
            processRequest(request, response);
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String flag = request.getParameter("action");
        switch (flag) {
            case "login":
                String acc = (String) session.getAttribute("userName");
                String ruolo = (String) session.getAttribute("userRole");
                if (ruolo.equals("Amministratore")) {
                    String jsessionID = session.getId(); // estraggo il session ID
                    System.out.println("JSessionID:" + jsessionID);
                    request.setAttribute("risultato", "sono amministratore");
                } else {
                    String jsessionID = session.getId(); // estraggo il session ID
                    System.out.println("JSessionID:" + jsessionID);
                    request.setAttribute("risultato", "sono cliente");
                }
                break;
            case "crea":
                request.setAttribute("risultato", "crea");
                break;
            case "ospite":
                String jsessionID = session.getId(); // estraggo il session ID
                System.out.println("JSessionID:" + jsessionID);
                session.setAttribute("userRole", "Ospite");
                request.setAttribute("risultato", "ospite");
                break;
        }
    }
}