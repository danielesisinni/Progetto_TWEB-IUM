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

@WebServlet(name = "logout", value = "/logout")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            Cookie loginCookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println(cookie.getName()+" "+ cookie.getValue());
                    if (cookie.getName().equals(session.getAttribute("userName"))) {
                        System.out.println("entro?");
                        loginCookie = cookie;
                        break;
                    }
                }
            }
            if (loginCookie != null) {
                System.out.println("entro????");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
            }
            session.removeAttribute("logonSessData"); //logonSessData
            session.invalidate();
            //response.sendRedirect("index.html");
        } catch (Exception sqle) {
            System.out.println("error UserValidateServlet message : " + sqle.getMessage());
            System.out.println("error UserValidateServlet exception : " + sqle);
        }
    }
}