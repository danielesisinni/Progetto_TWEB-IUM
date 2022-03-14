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
        String flag = request.getParameter("flag");


        if(flag.equals("login")){
            String acc = request.getParameter("account");
            String pass = request.getParameter("password");

            String[] esiste_ruolo = DAO.verificaUtenti(acc, pass);
            if (esiste_ruolo[0].equals("true")) {
                session.setAttribute("userName", acc);
                session.setAttribute("userRole", esiste_ruolo[1]);
                processRequest(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.flush();
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
        PrintWriter out = response.getWriter();
        String flag = request.getParameter("flag");
        switch (flag) {
            case "login":
                try {
                    String acc = (String) session.getAttribute("userName");
                    String ruolo = (String) session.getAttribute("userRole");
                    if (ruolo.equals("Amministratore")) {
                        String jsessionID = session.getId(); // estraggo il session ID
                        System.out.println("JSessionID:" + jsessionID);
                        out.print("sono amministratore");
                        out.flush();
                    } else {
                        String jsessionID = session.getId(); // estraggo il session ID
                        System.out.println("JSessionID:" + jsessionID);
                        out.print("sono cliente");
                        out.flush();
                    }
                } finally {
                    if (out != null)
                        out.close();
                }
                break;
            case "crea":
                try {
                    out.print("Cliente Benvenuto! ");
                    out.print("<span class=\"badge badge-success\">Success</span> Account creato");
                    out.flush();
                }finally {
                    if (out != null)
                        out.close();
                }
                break;
            }
        }

}