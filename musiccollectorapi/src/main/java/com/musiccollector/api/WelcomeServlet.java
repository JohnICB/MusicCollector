package com.musiccollector.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/welcomeTest")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//      String name = (String) request.getSession().getAttribute("name");

        String name = "";
        String cookieName = "name";

        Cookie[] cookies = request.getCookies();
        Boolean isLogged = false;

        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
                System.out.println("am gasit " + c.getValue());
                name = c.getValue();
                isLogged = true;
            }
        }

        if (isLogged) {
            request.setAttribute("name", name);
            request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
        } else
            response.sendRedirect("login");

    }
}
