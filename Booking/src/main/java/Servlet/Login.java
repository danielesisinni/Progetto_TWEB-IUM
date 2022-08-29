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
        }else if(flag.equals("ospite") || flag.equals("ospiteL")){
            session.setAttribute("lastUpdate", new Timestamp(System.currentTimeMillis()));
            System.out.println(session.getAttribute("lastUpdate")  +" guest");
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
            //Android
            case "androidL":
                String jsessionID = session.getId(); // estraggo il session ID
                session.setAttribute("app", "android");
                System.out.println("Login:\nJSessionID: " + jsessionID);
                break;
            case "ospiteL":
                DAO.registerDriver();
                String jsessionID2 = session.getId(); // estraggo il session ID
                System.out.println("Login:\nJSessionID: " + jsessionID2);
                session.setAttribute("userRole", "Ospite");
                session.setAttribute("app", "android");
                break;
            //WEB
            case "login":
                if (ruolo.equals("Amministratore")) {
                    String jsessionID3 = session.getId(); // estraggo il session ID
                    System.out.println("Login:\nJSessionID: " + jsessionID3);
                    request.setAttribute("risultato", "sono amministratore");
                } else {
                    String jsessionID4 = session.getId(); // estraggo il session ID
                    System.out.println("Login:\nJSessionID: " + jsessionID4);
                    request.setAttribute("risultato", "sono cliente");
                }
                session.setAttribute("app", "web");
                break;
            case "crea":
                request.setAttribute("risultato", "crea");
                break;
            case "ospite":
                DAO.registerDriver();
                String jsessionID5 = session.getId(); // estraggo il session ID
                System.out.println("Login:\nJSessionID: " + jsessionID5);
                session.setAttribute("userRole", "Ospite");
                session.setAttribute("app", "web");
                request.setAttribute("risultato", "ospite");
                break;
        }
    }
}