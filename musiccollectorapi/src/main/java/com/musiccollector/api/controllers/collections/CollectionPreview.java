package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.user.ConnectedUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/collection")
public class CollectionPreview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (LoginService.isUserLoggedIn(request.getCookies())) {
            request.getRequestDispatcher("/WEB-INF/views/vinyl.html").forward(request, response);
        } else
            response.sendRedirect("welcome");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (Math.random() > 0.5f)
            response.sendRedirect("welcome");
        else {
            request.getRequestDispatcher("/WEB-INF/views/TEMPLATE2.jsp").forward(request, response);
        }
    }

}

