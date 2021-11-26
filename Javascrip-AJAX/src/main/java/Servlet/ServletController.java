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
        String action = request.getParameter("action");
        if(session.getAttribute("userRole").equals("Amministratore")) {
            RequestDispatcher rd = ctx.getRequestDispatcher("/HomeAmministratore.html");
            if (action != null) {
                if (action.equals("Docente")) {
                    rd = ctx.getRequestDispatcher("/docente.html");
                }else if (action.equals("Prenotazioni")) {
                    rd = ctx.getRequestDispatcher("/ripetizione.html");
                }else if (action.equals("Menù")) {
                    rd = ctx.getRequestDispatcher("/HomeAmministratore.html");
                }else if (action.equals("logout")) {
                    rd = ctx.getRequestDispatcher("/index.html");
                    try {
                        session.removeAttribute("logonSessData");
                        session.invalidate();
                        response.sendRedirect("index.html");
                    } catch (Exception sqle) {
                        System.out.println("error UserValidateServlet message : " + sqle.getMessage());
                        System.out.println("error UserValidateServlet exception : " + sqle);
                    }
                }else if (action.equals("printdoce")) {
                    rd = ctx.getRequestDispatcher("/printDocenti.html");
                }else if (action.equals("printripe")) {
                rd = ctx.getRequestDispatcher("/printRipetizioni.html");
                }
            }
            rd.forward(request, response);
        }
    }
}
