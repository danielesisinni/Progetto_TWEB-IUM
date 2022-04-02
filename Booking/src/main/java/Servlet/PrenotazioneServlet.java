package Servlet;

import DAO.DAO;
import DAO.Prenotazione;
import DAO.Ripetizione;
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

@WebServlet(name = "prenotazioni", value = "/prenotazioni")
public class PrenotazioneServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String acc = (String) session.getAttribute("userName");
        if(action.equals("MiePrenotazioni")){
            ArrayList<Prenotazione> prenotazione = DAO.MyBooking(acc);
            String s = gson.toJson(prenotazione);
            request.setAttribute("risultato", s);
            String jsessionID = session.getId(); // estraggo il session ID
            System.out.println("JSessionID:" + jsessionID);
        }else {
            ArrayList<Prenotazione> prenotazione = DAO.Booking();
            String s = gson.toJson(prenotazione);
            request.setAttribute("risultato", s);
            String jsessionID = session.getId(); // estraggo il session ID
            System.out.println("JSessionID:" + jsessionID);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if(action.equals("Prenota") && (session.getAttribute("userRole").equals("Amministratore") || session.getAttribute("userRole").equals("Cliente"))) {
            String utente = (String) session.getAttribute("userName");
            String codice = request.getParameter("codex");
            String docente = null;String corso = null;String giorno = null;String ora = null;String status = null;
            ArrayList<Ripetizione> rip = DAO.SearchRepetition(codice);
            for(Ripetizione a: rip){
                docente = a.getDocente();
                corso = a.getCorso();
                giorno = a.getGiorno();
                ora = a.getOra();
                status = a.getStatus();
            }
            if(status.equals("DISPONIBILE")){
                if (docente != null && corso != null && giorno != null && ora != null) {
                    status = "EFFETTUATA";
                    DAO.insertBooking(utente, codice, docente, corso, giorno, ora, status);
                    request.setAttribute("risultato", "aggiunta");
                }
            }else{
                request.setAttribute("risultato", "errore");
            }
        }else if(action.equals("Disdetta") && (session.getAttribute("userRole").equals("Amministratore") || session.getAttribute("userRole").equals("Cliente"))) {
            String utente = (String) session.getAttribute("userName");
            String codice = request.getParameter("codex");
            DAO.updateBooking(utente, codice, action);
            request.setAttribute("risultato", "rimossa");
        }else if(action.equals("Riprenota") && (session.getAttribute("userRole").equals("Amministratore") || session.getAttribute("userRole").equals("Cliente"))) {
            String utente = (String) session.getAttribute("userName");
            String codice = request.getParameter("codex");
            DAO.updateBooking(utente, codice, action);
            request.setAttribute("risultato", "rimossa");
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

