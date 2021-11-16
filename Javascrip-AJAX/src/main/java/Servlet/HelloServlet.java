/*package Servlet;

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

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Integer idDocente = Integer.parseInt(request.getParameter("id"));
        String nomeDocente = request.getParameter("nome");
        String cognomeDocente = request.getParameter("cognome");

        if(idDocente != null && nomeDocente != null && cognomeDocente != null){
            DAO.insertTeacher(nomeDocente, cognomeDocente, idDocente);
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Ecco i dati del docente aggiunti: ");
        out.println("<p>" + "ID: " + idDocente + "</p>");
        out.println("<p>" + "Nome: " + nomeDocente + "</p>");
        out.println("<p>" + "Cognome: " + cognomeDocente + "</p>");
        out.println("<p>Docente aggiunto nel Database!<p>");
        out.println("</br>");
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action != null) {
            if (action.equals("Autentica")) {
                String account = request.getParameter("account");
                String password = request.getParameter("password");
                String ruolo = DAO.verificaUtenti(account,password);

                if (account != null) {
                    if (ruolo.equals("")) {
                        PrintWriter out = response.getWriter();
                        out.println("<h3>Errore nell' autentificazione");
                        out.println("<p>Utente non trovato, controllare i dati inseriti</p>");
                        String index = "index.html";
                        out.println("<p><a href=\"" + index + "\"> Tornare al login</a></p>");
                        out.flush();
                        out.close();
                    } else{
                        session.setAttribute("userName", account);
                        session.setAttribute("userRole", ruolo);
                        if (ruolo.equals("Amministratore")) {
                            PrintWriter out = response.getWriter();
                            String docente = "docente.html";
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Autentificazione con successo</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<p>Sei collegato come: " + session.getAttribute("userName") + " [" + session.getAttribute("userRole") + "]" + "</p>");
                            out.println("<p><li>Puoi aggiungere un docente <a href =\"" + docente + "\">AGGIUNGI </a></p>");
                            out.println("</body>");
                            out.println("</html>");
                        } else {
                            processRequest(request, response);
                        }
                    }
                }
            } else {
                String ruolo = "ospite";
                session.setAttribute("userRole", ruolo);
                processRequest(request, response);
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("userName");
        String ruolo = request.getParameter("userRole");
        HttpSession s = request.getSession();
        if (account!=null)
            s.setAttribute("userName", account);
        if (ruolo!=null)
            s.setAttribute("userRole", ruolo);
        String url = response.encodeURL("hello-servlet");
        String index = response.encodeURL("index.html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>hello-servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Sei collegato come: " + s.getAttribute("userName") + " [" + s.getAttribute("userRole") + "]" + "</p>");
            String azione = request.getParameter("action");
            out.println("<p>URL: " + url + "</p>");
            if (azione!=null && azione.equals("invalida")) {
                s.invalidate();
                out.println("<p>Sessione invalidata!</p>");
                out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");
            }
            else {
                out.print("<p>Stato della sessione: ");
                if (s.isNew())
                    out.println(" nuova sessione</p>");
                else out.println(" vecchia sessione</p>");
                out.println("<p>ID di sessione: "+s.getId() + "</p>");
                out.println("<p>Data di creazione: " + new Date(s.getCreationTime()) + "</p>");
                out.println("<p><a href=\"" + index + "\"> Tornare al login</a></p>");
                //out.println("<p>Max inactive time interval (in secondi): "
                // + s.getMaxInactiveInterval() + "</p>");
                //out.println("<p>Invalida <a href=\"" + url + "?action=invalida\"> la sessione</a></p>");
                //out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}*/