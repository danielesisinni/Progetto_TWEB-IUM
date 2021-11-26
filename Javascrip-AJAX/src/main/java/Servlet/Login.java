package Servlet;

import java.io.*;
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
        String action = request.getParameter("action");
        if( action!= null){
            session.setAttribute("userName", "nuovo");
            processRequest(request, response);
        }

        String acc = request.getParameter("account");
        String pass = request.getParameter("password");
        String[] esiste_ruolo = DAO.verificaUtenti(acc, pass);
        if (esiste_ruolo[0].equals("true")) {
            session.setAttribute("userName", acc);
            session.setAttribute("userRole", esiste_ruolo[1]);
            processRequest(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<span class=\"badge badge-danger\">Errore nel login</span>");
            out.flush();
            out.close();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String amministratore = response.encodeURL("HomeAmministratore.html");
        String cliente = response.encodeURL("Home.html");
        try {
            String action = (String) session.getAttribute("userName");
            System.out.println(action);
            if(action.equals("nuovo")) {
                out.print("Benvenuto! ");
                out.print("<span class=\"badge badge-success\">Success</span> Account creato");
                out.print("Effettua il login!");
                out.flush();
            }else {
                String acc = (String) session.getAttribute("userName");
                String ruolo = (String) session.getAttribute("userRole");
                out.print("<span class=\"badge badge-success\">Success</span>");
                out.print(" Benvenuto " + acc + " [" + ruolo + "]");
                if(ruolo.equals("Amministratore")) {
                    out.println("<p><a href=\"" + amministratore + "\"> Accedi al menù amministratore</a></p>");
                    out.flush();
                }else{
                    out.println("<p><a href=\"" + cliente + "\"> Accedi al menù</a></p>");
                    out.flush();
                }
            }
        } finally {
            if (out!=null)
                out.close();
        }
    }
}
