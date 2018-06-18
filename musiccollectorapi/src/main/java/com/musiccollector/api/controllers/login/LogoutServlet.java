package com.musiccollector.api.controllers.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        for (Cookie c: request.getCookies())
        {
            if (c.getName().equals("user") || c.getName().equals("loginToken"))
            {
                c.setValue("");
                c.setMaxAge(0);
                c.setPath("/");

                response.addCookie(c);
            }
        }

        request.getRequestDispatcher("/WEB-INF/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        doGet(request, response);
    }

}