package com.musiccollector.api.controllers.login;

import com.google.common.hash.Hashing;
import com.musiccollector.api.model.database.user.ConnectedUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Check cookies
//        request.getRequestDispatcher("/WEB-INF/views/index.html").forward(request, response);
        response.sendRedirect("/welcome");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        if (LoginService.isUserLoggedIn(request.getCookies()))
        {
            response.sendRedirect("/welcome");
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

        }

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValidUser = service.validateUser(name, Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());

        if (isValidUser) {
//            request.setAttribute("name", name);
//            request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);

            ConnectedUsers connectedUser = new ConnectedUsers(name);
            connectedUser.insert();

            Cookie nameCookie = new Cookie("user", name);
            Cookie tokenCookie = new Cookie("loginToken", connectedUser.getToken());

            nameCookie.setMaxAge(-1);
            tokenCookie.setMaxAge(-1);


            response.addCookie(nameCookie);
            response.addCookie(tokenCookie);


//            request.getSession().setAttribute("name", name);
            response.sendRedirect("welcome");
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

}