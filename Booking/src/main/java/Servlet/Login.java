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
        String flag = request.getParameter("action");
        if(flag.equals("login")){
            String acc = request.getParameter("account");
            String pass = request.getParameter("password");
            String[] esiste_ruolo = DAO.verificaUtenti(acc, pass);
            if (esiste_ruolo[0].equals("true")) {
                session.setAttribute("userName", acc);
                session.setAttribute("userRole", esiste_ruolo[1]);
                processRequest(request, response);
            } else {
                request.setAttribute("risultato", "errore");
            }
        }else {
            String newacc = request.getParameter("account");
            String newpass = request.getParameter("password");
            DAO.insertUsers(newacc, newpass);
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
        }
    }
}