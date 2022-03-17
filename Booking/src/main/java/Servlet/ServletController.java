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
        processRequest(request, response);
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
        if (action != null && action.equals("login")) {
            rd = getServletContext().getNamedDispatcher("login");
            rd.include(request, response);
            out.print(request.getAttribute("risultato"));
        }
        if (action != null && !action.equals("login")) {
            if (session.getAttribute("userRole").equals("Amministratore")) {

                switch (action) {
                    case "docente":
                        rd = getServletContext().getNamedDispatcher("docenti");
                        rd.include(request, response);
                        break;
                    case "corso":
                        rd = ctx.getRequestDispatcher("Corsi");
                        break;
                    case "logout":
                        rd = ctx.getRequestDispatcher("Logout");
                        break;
                }
            } else if (session.getAttribute("userRole").equals("Cliente")) {
                    switch (action) {
                        case "DocentiCliente":
                            rd = getServletContext().getNamedDispatcher("docenti");
                            rd.include(request, response);
                            response.setContentType("application/json,charset=UTF-8");
                            out.print(request.getAttribute("risultato"));
                            break;
                        case "corso":
                            rd = ctx.getRequestDispatcher("Corsi");
                            break;
                        case "RipetizioniCliente":
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
            } else if (session.getAttribute("userRole").equals("Ospite")) {
                    switch (action) {
                        case "DocentiCliente":
                            rd = ctx.getRequestDispatcher("");
                            break;
                        case "corso":
                            rd = ctx.getRequestDispatcher("/corso.html");
                            break;
                        case "logout":
                            rd = ctx.getRequestDispatcher("/logout");
                            break;
                    }
            }
        }
    }
}
