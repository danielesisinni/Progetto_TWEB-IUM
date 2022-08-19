package Servlet;

import DAO.DAO;
import DAO.Prenotazione;
import DAO.Ripetizione;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "prenotazioni", value = "/prenotazioni")
public class PrenotazioneServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        boolean check = DAO.logout((Timestamp) session.getAttribute("lastUpdate"));
        if(check){
            rd = getServletContext().getNamedDispatcher("logout");
            rd.include(request, response);
        }else{
            session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
        }
        String action = request.getParameter("action");
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        String acc = (String) session.getAttribute("userName");
        if(action.equals("MiePrenotazioni") || action.equals("androidP")){
            ArrayList<Prenotazione> prenotazione = DAO.MyBooking(acc);
            String s = gson.toJson(prenotazione);
            request.setAttribute("risultato", s);
        }else {
            ArrayList<Prenotazione> prenotazione = DAO.Booking();
            String s = gson.toJson(prenotazione);
            request.setAttribute("risultato", s);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        boolean check = DAO.logout((Timestamp) session.getAttribute("lastUpdate"));
        if(check){
            rd = getServletContext().getNamedDispatcher("logout");
            rd.include(request, response);
        }else{
            session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
        }

        String action = request.getParameter("action");
        if(session.getAttribute("userName") != null && (session.getAttribute("userRole").equals("Amministratore") || session.getAttribute("userRole").equals("Cliente"))) {
            switch (action){
                case "Prenota":
                        String utente = (String) session.getAttribute("userName");
                        String codice = request.getParameter("action2");
                        String docente = null; String corso = null; String giorno = null; String ora = null; String status = null;
                        ArrayList<Ripetizione> rip = DAO.SearchRepetition(codice);
                        for (Ripetizione a : rip) {
                            docente = a.getDocente();
                            corso = a.getCorso();
                            giorno = a.getGiorno();
                            ora = a.getOra();
                            status = a.getStatus();
                        }
                        if (status.equals("DISPONIBILE")) {
                            if (docente != null && corso != null && giorno != null && ora != null) {
                                status = "ATTIVA";
                                DAO.insertBooking(utente, codice, docente, corso, giorno, ora, status);
                                request.setAttribute("risultato", "aggiunta");
                            }
                        } else {
                            request.setAttribute("risultato", "errore");
                        }
                        break;
                    case "Disdetta": case "Riprenota": case "Effettuata":
                        utente = (String) session.getAttribute("userName");
                        codice = request.getParameter("action2");
                        DAO.updateBooking(utente, codice, action);
                        request.setAttribute("risultato", "eseguito");
                        break;
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

