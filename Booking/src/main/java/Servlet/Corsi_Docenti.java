package Servlet;

import DAO.DAO;
import DAO.CorsoDocente;
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

@WebServlet(name = "corsi_docenti", value = "/corsi_docenti")
public class Corsi_Docenti extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html,charset=UTF-8");

        HttpSession session = request.getSession();
        String nomecorso = request.getParameter("courses");
        String var2 = request.getParameter("teacher");

        if (session.getAttribute("userRole").equals("Amministratore")) {
            if(!nomecorso.equals("")){
                DAO.insertCourse(nomecorso);
            }else if(!var2.equals("")){

            }



            request.setAttribute("risultato","aggiunti");

            /*response.setContentType("text/html,charset=UTF-8");
            String nomeDocente = request.getParameter("nome");
            String cognomeDocente = request.getParameter("cognome");
            if (nomeDocente != null && cognomeDocente != null) {
                DAO.insertTeacher(nomeDocente, cognomeDocente);
            }
            processRequest(request, response);
            }else{
                processRequest(request, response);
            }

            if (nomecorso != null) {
                DAO.insertCourse(idcorso, nomecorso);
            }

            processRequest(request, response);*/
        }else{
            request.setAttribute("risultato","errore");
        }
    }

        public void doPost (HttpServletRequest request, HttpServletResponse response) throws
        IOException, ServletException {
            String action = request.getParameter("courses");
            String action2 = request.getParameter("teacher");
            System.out.println("action-> " + action);
            System.out.println("action2-> " + action2);
           // System.out.println("action2-> " + action2[0]);
            //System.out.println("action2-> " + action2[1]);
            if (action != null || action2 != null) {
                doGet(request, response);
            } else {
                processRequest(request, response);
            }

        }

        private void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            if (action != null && action.equals("courses")) {
                request.setAttribute("risultato", "corso_aggiunto");

                if (action.equals("Corsi_Docenti")) {
                    response.setContentType("application/json,charset=UTF-8");
                    Gson gson = new Gson();
                    PrintWriter out = response.getWriter();
                    ArrayList<CorsoDocente> corsi_docenti = DAO.CourseTeacher();
                    String s = gson.toJson(corsi_docenti);
                    request.setAttribute("risultato", s);
                    String jsessionID = session.getId(); // estraggo il session ID
                    System.out.println("JSessionID:" + jsessionID);
                }
            }
        }
}

