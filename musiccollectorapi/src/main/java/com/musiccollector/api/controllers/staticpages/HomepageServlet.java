package com.musiccollector.api.controllers.staticpages;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.user.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/welcome")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Cookie[] coockie = request.getCookies();


        if (LoginService.isUserLoggedIn(coockie))
            request.getRequestDispatcher("/WEB-INF/index-logat.html").forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/index.html").forward(request, response);

    }

}