package com.musiccollector.api.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Check cookies
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        boolean isValidUser = service.validateUser(name, password);

        if (isValidUser) {
//            request.setAttribute("name", name);
//            request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
            Cookie nameCookie = new Cookie("name", name);
            nameCookie.setMaxAge(-1);
            response.addCookie(nameCookie);

//            request.getSession().setAttribute("name", name);
            response.sendRedirect("welcome");
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

}