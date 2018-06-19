package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.entities.Vinyls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/preview")
public class ElementPreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        long musicID = -1;
        musicID = Long.parseLong(request.getParameter("id"));

        if (musicID < 0 || !LoginService.isUserLoggedIn(request.getCookies())) return;

        request.getRequestDispatcher("/WEB-INF/views/vinyl-preview.html").forward(request, response);
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