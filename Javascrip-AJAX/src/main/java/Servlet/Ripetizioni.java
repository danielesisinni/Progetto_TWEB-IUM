package Servlet;

import DAO.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Ripetizioni", value = "/Ripetizioni")
public class Ripetizioni extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String docente = request.getParameter("docente");
        String corso = request.getParameter("corso");
        String data = request.getParameter("data");
        String ora = request.getParameter("ora");

        if (docente != null && corso != null && data != null && ora != null) {
            DAO.insertRepetition(docente, corso, data, ora);
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Ecco i dati della ripetizione aggiunta: ");
        out.println("<p>" + "Docente: " + docente + "</p>");
        out.println("<p>" + "Corso: " + corso + "</p>");
        out.println("<p>" + "data: " + data + "</p>");
        out.println("<p>" + "ora: " + ora + "</p>");
        out.println("<p>Ripetizione aggiunto nel Database!<p>");
        out.println("</br>");
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        String url = response.encodeURL("Ripetizione");
        String index = response.encodeURL("index.html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ripetizione</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Sei collegato come: " + s.getAttribute("userName") + " [" + s.getAttribute("userRole") + "]" + "</p>");
            String azione = request.getParameter("action");
            out.println("<p>URL: " + url + "</p>");
            if (azione!=null && azione.equals("invalida")) {
                s.invalidate();
                out.println("<p>Sessione invalidata!</p>");
                out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");
            }
            else {
                out.print("<p>Stato della sessione: ");
                if (s.isNew())
                    out.println(" nuova sessione</p>");
                else out.println(" vecchia sessione</p>");
                out.println("<p>ID di sessione: "+s.getId() + "</p>");
                out.println("<p>Data di creazione: " + new Date(s.getCreationTime()) + "</p>");
                out.println("<p><a href=\"" + index + "\"> Tornare al login</a></p>");
                //out.println("<p>Max inactive time interval (in secondi): "
                // + s.getMaxInactiveInterval() + "</p>");
                //out.println("<p>Invalida <a href=\"" + url + "?action=invalida\"> la sessione</a></p>");
                //out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
