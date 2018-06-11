package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Vinyls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/vinyls")
public class VinylsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Cookie[] cookies = request.getCookies();

        System.out.println("/vinylsGET");

        if (cookies != null)
        {
            boolean isLogged = LoginService.isUserLoggedIn(cookies);

            if (isLogged)
            {
                ArrayList<Vinyls> vinylsArray;
                CollectionJava collectionJava ;

            }
        }

        request.getRequestDispatcher("/WEB-INF/views/TEMPLATE.jsp").forward(request, response);
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

