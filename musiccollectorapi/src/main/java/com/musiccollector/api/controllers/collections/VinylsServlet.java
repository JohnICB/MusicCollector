package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.entities.Vinyls;
import com.musiccollector.api.model.database.user.Users;

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
            long id = 1;

            for (Cookie c : cookies)
            {
                if (c.getName().equals("user")) {
                    id = Users.getIDbyName(c.getValue());
                    break;}

                }

            if (isLogged)
            {
                String colName = request.getParameter("colName");

                ArrayList<Vinyls> vinylsArray;


            }
        }

        response.setContentType("application/json");
        response.getWriter().write("");
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

