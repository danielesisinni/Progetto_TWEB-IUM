package Servlet;

import DAO.DAO;
import DAO.*;
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

@WebServlet(name = "ripetizioni", value = "/ripetizioni")
public class RipetizioneServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        response.setContentType("application/json,charset=UTF-8");
        Gson gson = new Gson();
        if(request.getParameter("action2").equals("storico")){
            ArrayList<Ripetizione> ripetizione = DAO.Repetition();
            String s = gson.toJson(ripetizione);
            request.setAttribute("risultato", s);
        }else if(request.getParameter("action").equals("RipetizioniA") || request.getParameter("action").equals("androidR")){
            ArrayList<Ripetizione> ripetizione = DAO.RepetitionA();
            String s = gson.toJson(ripetizione);
            request.setAttribute("risultato", s);
        }else{
            String account = (String) session.getAttribute("userName");
            ArrayList<Ripetizione> ripetizione = DAO.RepetitionPersonali(account);
            String s = gson.toJson(ripetizione);
            request.setAttribute("risultato", s);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String action2 = request.getParameter("action2");
        if(session.getAttribute("userName") != null && session.getAttribute("userRole").equals("Amministratore")) {
            if (action2.equals("")) {
                String docente = request.getParameter("nome");
                String corso = request.getParameter("corsi");
                String giorno = request.getParameter("giorno");
                String ora = request.getParameter("ora");
                String status = "DISPONIBILE";
                boolean flag = true;
                if (docente != null && corso != null && giorno != null && ora != null) {
                    if (!docente.equals("") && !corso.equals("") && !giorno.equals("") && !ora.equals("")) {
                        ArrayList<CorsoDocente> cd = DAO.CourseTeacher();
                        for(CorsoDocente cd2 : cd){
                            if(cd2.getDocente().equals(docente) && cd2.getCorso().equals(corso)) {
                                DAO.insertRepetition(docente, corso, giorno, ora, status);
                                request.setAttribute("risultato", "aggiunta");
                                flag = false;
                            }
                        }
                        if(flag)
                            request.setAttribute("risultato", "errore");
                    } else {
                        request.setAttribute("risultato", "errore");
                    }
                }
            } else if (action.equals("Rimuovi")) {
                ArrayList<Ripetizione> ripetizioni = DAO.Repetition();
                for (Ripetizione list : ripetizioni) {
                    if (list.getCodice().equals(action2)) {
                        if (list.getStatus().equals("DISPONIBILE")) {
                            DAO.removeRepetition(action2);
                            request.setAttribute("risultato", "rimossa");
                        } else {
                            request.setAttribute("risultato", "errore");
                        }
                    }
                }
            }
        }
        //ANDROID
        if(action.equals("androidR"))
            doGet(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

