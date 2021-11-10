package com.example.demo;

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

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action != null) {
            String acc = request.getParameter("account");
            String pass = request.getParameter("password");
            String ruolo = "";

            if (acc != null) {
                ruolo = DAO.verificaUtenti(acc, pass, ruolo);
                if (ruolo.equals("false")) {
                    PrintWriter out = response.getWriter();
                    out.println("<h3>Errore nell' autentificazione");
                    out.println("<p>Utente non trovato, controllare i dati inseriti</p>");
                    String index = "index.html";
                    out.println("<p><a href=\"" + index + "\"> Tornare al login</a></p>");
                    out.flush();
                    out.close();
                } else {
                    session.setAttribute("userName", acc);
                    session.setAttribute("userRole", ruolo);
                    if (ruolo.equals("Amministratore")) {
                        processRequest(request, response);
                    } else {
                        processRequest(request, response);
                    }
                }
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            String acc = (String) session.getAttribute("account");
            String ruolo = (String) session.getAttribute("userRole");
            out.println("<p>Autentificazione avvenuta con successo: <p>");
            out.println("</br><p>Benvenuto <p>" + acc + " [" + ruolo + "]");
            out.flush();
        } finally {
            if (out!=null)
                out.close();
        }
    }
}
