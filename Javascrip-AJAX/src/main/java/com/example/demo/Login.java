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
            if(action == "Entra") {
                PrintWriter out = response.getWriter();
                out.println("<h3>Benvenuto nel server delle prenotazioni<h3>");
                out.println("<p>Sei un nuovo ospite</p>");
                out.flush();
                out.close();
            }else{
                String acc = request.getParameter("account");
                String pass = request.getParameter("password");
                String ruolo = "";

                if (acc != null) {
                    ruolo = DAO.verificaUtenti(acc, pass);
                    if (ruolo.equals("")) {
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
                            PrintWriter out = response.getWriter();
                            out.println("<p>Autentificazione avvenuta con successo: <p>");
                            out.println("</br><p>Benvenuto <p>" + acc + " [" + ruolo + "]");
                            out.flush();
                            out.close();
                        }
                    }
                }
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String docente = "docente.html";
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            String acc = (String) session.getAttribute("account");
            String ruolo = (String) session.getAttribute("userRole");
            out.println("<p>Autentificazione avvenuta con successo: <p>");
            out.println("</br><p>Benvenuto <p>" + acc + " [" + ruolo + "]");
            out.println("<p><li>Puoi aggiungere un docente <a href =\"" + docente + "\">AGGIUNGI </a></p>");
            out.flush();
        } finally {
            if (out!=null)
                out.close();
        }
    }
}
