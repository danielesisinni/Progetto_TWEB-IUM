package Servlet;

import DAO.DAO;
import DAO.CorsoDocente;
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
import java.util.Date;

@WebServlet(name = "ripetizioni", value = "/ripetizioni")
public class RipetizioneServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String account = (String) session.getAttribute("userName");
        //System.out.println(account);
        ArrayList<Ripetizione> ripetizione = DAO.Repetition(account);
        String s = gson.toJson(ripetizione);
        request.setAttribute("risultato", s);
        String jsessionID = session.getId(); // estraggo il session ID
        System.out.println("JSessionID:" + jsessionID);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        System.out.println("Non devo entrare qui");
        HttpSession session = request.getSession();

        if(session.getAttribute("userRole").equals("Amministratore")) {
            String docente = request.getParameter("nome");
            String corso = request.getParameter("corsi");
            String giorno = request.getParameter("giorno");
            String ora = request.getParameter("ora");
            String status = "attiva";
            if (docente != null && corso != null && giorno != null && ora != null) {
                if (!docente.equals("") && !corso.equals("") && !giorno.equals("") && !ora.equals("")) {
                    DAO.insertRepetition(docente, corso, giorno, ora, status);
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

