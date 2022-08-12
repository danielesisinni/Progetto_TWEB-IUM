package Servlet;

import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletController", value = "/ServletController")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd;
        System.out.println(session.getId() + "  " + session.getAttribute("sessionid"));
        if(action != null) {
            if (session.getAttribute("userRole") != null) {
                switch (action) {
                    case "Corsi_Docenti":
                    case "Corsi":
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "RipetizioniA":
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Prenotazioni":
                    case "MiePrenotazioni":
                        rd = getServletContext().getNamedDispatcher("prenotazioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = ctx.getRequestDispatcher ("/index.html");
        String action = request.getParameter("action");

        if(action != null) {
            if (action.equals("login") || action.equals("crea") || action.equals("ospite")) {
                rd = getServletContext().getNamedDispatcher("login");
                rd.include(request, response);
                out.print(request.getAttribute("risultato"));
            }
            //Android
            if (action.equals("androidL")) {
                rd = getServletContext().getNamedDispatcher("login");
                rd.include(request, response);
                if(request.getAttribute("risultato").equals("loginandroid"))
                    out.print("Login effettuato");
            }
            if (session.getAttribute("userRole") != null){
                switch (action) {
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        out.print(request.getAttribute("risultato"));
                        break;
                    //Rimozione delle prenotazioni
                    case "Rimuovi":
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Prenotazioni":
                        rd = getServletContext().getNamedDispatcher("prenotazioni");
                        rd.include(request, response);
                        out.print(request.getAttribute("risultato"));
                        break;
                        //PRenotazione effettuata
                    case "Effettuata":
                        //Disdetta delle prenotazioni
                    case "Disdetta":
                        //Prenota delle ripetizioni
                    case "Prenota":
                        //Riprenota le ripetizioni
                    case "Riprenota":
                        rd = getServletContext().getNamedDispatcher("prenotazioni");
                        rd.include(request, response);
                        response.setContentType("text/html");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "logout":
                        rd = getServletContext().getNamedDispatcher("logout");
                        rd.include(request, response);
                        break;

                    //ANDROID
                    case "androidR":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        System.out.print(request.getAttribute("risultato"));
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "androidP":
                        rd = getServletContext().getNamedDispatcher("prenotazioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        System.out.print(request.getAttribute("risultato"));
                        out.print(request.getAttribute("risultato"));
                        break;
                }
            }
        }
    }
}
