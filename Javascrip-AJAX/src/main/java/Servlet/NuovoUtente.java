package Servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.*;
import com.google.gson.Gson;

@WebServlet(name = "nuovoutente", value = "/nuovoutente")
public class NuovoUtente extends HttpServlet {
    private Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if( action!= null){
            switch (action){
                case "Crea":
                    String newacc = request.getParameter("newaccount");
                    String newpass = request.getParameter("newpassword");
                    DAO.insertUsers(newacc, newpass);
                    processRequest(request, response);
                    break;
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("<span class=\"badge badge-success\">Success</span> Account creato");
        out.flush();
        if (out!=null)
            out.close();
        }
}