package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletController", value = "/ServletController")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        if(session.getAttribute("userRole").equals("Amministratore")) {
            String action = request.getParameter("action");
            RequestDispatcher rd = ctx.getRequestDispatcher("/HomeAmministratore.html");
            if (action != null) {
                if (action.equals("Docente"))
                    rd = ctx.getRequestDispatcher("/docente.html");
                else if (action.equals("Prenotazioni")) {
                    rd = ctx.getRequestDispatcher("/ripetizione.html");
                } else if (action.equals("Menù"))
                    rd = ctx.getRequestDispatcher("/HomeAmministratore.html");
            }
            rd.forward(request, response);
        }
    }
}
