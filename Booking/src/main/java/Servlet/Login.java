package Servlet;

import DAO.DAO;

import java.io.*;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String flag = request.getParameter("action");
        if(flag.equals("login") || flag.equals("androidL")){
            String emailUser = request.getParameter("email");
            String pass = request.getParameter("password");
            String[] esiste_ruolo = DAO.verificaUtenti(emailUser, pass);
            if (esiste_ruolo[0].equals("true")) {
				session.setAttribute("userName", emailUser);
                session.setAttribute("userRole", esiste_ruolo[1]);
                session.setAttribute("sessionid", session.getId());
                session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
                System.out.println(session.getAttribute("lastUpdate"));
                request.setAttribute("risultato", "loginandroid");

                processRequest(request, response);
            } else {
                request.setAttribute("risultato", "errore");
            }
        }else if(flag.equals("crea")) {
            String newemail = request.getParameter("email");
            String newacc = request.getParameter("account");
            String newpass = request.getParameter("password");
            if(!newacc.equals("") && !newpass.equals("") && !newemail.equals("")){
                DAO.insertUsers(newemail, newacc, newpass);
                processRequest(request, response);
            }else{
                request.setAttribute("risultato", "errore2");
            }
        }else if(flag.equals("ospite")){
            processRequest(request, response);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String flag = request.getParameter("action");
        String acc = (String) session.getAttribute("userName");
        String ruolo = (String) session.getAttribute("userRole");
        switch (flag) {
            case "login":
                if (ruolo.equals("Amministratore")) {
                    request.setAttribute("risultato", "sono amministratore");
                } else {
                    request.setAttribute("risultato", "sono cliente");
                }
                break;
            case "crea":
                request.setAttribute("risultato", "crea");
                break;
            case "ospite":
                DAO.registerDriver();
                String jsessionID = session.getId(); // estraggo il session ID
                System.out.println("JSessionID:" + jsessionID);
                session.setAttribute("userRole", "Ospite");
                request.setAttribute("risultato", "ospite");
                break;
        }
    }
}