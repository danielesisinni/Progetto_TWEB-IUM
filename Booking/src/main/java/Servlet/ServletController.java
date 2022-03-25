package Servlet;

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
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = ctx.getRequestDispatcher ("/index.html");
        String action = request.getParameter("action");
        if(action != null) {
            if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Amministratore")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        System.out.println("check");
                        if(request.getParameter("courses") != null){
                            String check = (String) request.getAttribute("risultato");
                            switch (check){
                                case "aggiunti":

                            }
                        }
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                }
            } else if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Cliente")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                }
            } else if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Ospite")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
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
                System.out.println(request.getAttribute("risultato"));
                out.print(request.getAttribute("risultato"));
            }
            if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Amministratore")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        if(request.getParameter("courses") != null){
                            String check = (String) request.getAttribute("risultato");
                            switch (check){
                                case "aggiunti":

                            }
                        }
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "logout":
                        rd = getServletContext().getNamedDispatcher("logout");
                        rd.include(request, response);
                        break;
                }
            } else if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Cliente")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "logout":
                        rd = getServletContext().getNamedDispatcher("logout");
                        rd.include(request, response);
                        break;
                }
            } else if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("Ospite")) {
                switch (action) {
                    case "Docenti":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Corsi_Docenti":
                        rd = getServletContext().getNamedDispatcher("corsi_docenti");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "Ripetizioni":
                        rd = getServletContext().getNamedDispatcher("ripetizioni");
                        rd.include(request, response);
                        response.setContentType("application/json,charset=UTF-8");
                        out.print(request.getAttribute("risultato"));
                        break;
                    case "logout":
                        rd = getServletContext().getNamedDispatcher("logout");
                        rd.include(request, response);
                        break;
                }
            }
        }
    }
}
