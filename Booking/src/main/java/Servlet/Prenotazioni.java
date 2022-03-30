package Servlet;

import DAO.DAO;
import DAO.Prenotazione;
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
import java.util.Date;

@WebServlet(name = "prenotazioni", value = "/prenotazioni")
public class Prenotazioni extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        ArrayList<Prenotazione> prenotazione = DAO.Prenotazione();
        String s = gson.toJson(prenotazione);
        request.setAttribute("risultato", s);
        String jsessionID = session.getId(); // estraggo il session ID
        System.out.println("JSessionID:" + jsessionID);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        System.out.println("Entrooooo");
        HttpSession session = request.getSession();

        if(session.getAttribute("userRole").equals("Amministratore") || session.getAttribute("userRole").equals("Cliente")) {
            String utente = (String) session.getAttribute("userName");
            String docente = request.getParameter("var1");
            String corso = request.getParameter("corsi");
            String data = request.getParameter("giorno");
            String ora = request.getParameter("ora");
            String status = request.getParameter("disponibile");
            if (docente != null && corso != null && data != null && ora != null) {
                if (!docente.equals("") && !corso.equals("") && !data.equals("") && !ora.equals("")) {
                    DAO.insertPrenotazione(utente, docente, corso, data, ora, status);
                    request.setAttribute("risultato", "aggiunta");
                } else {
                    request.setAttribute("risultato", "errore");
                }
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

