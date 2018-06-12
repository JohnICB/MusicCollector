package com.musiccollector.api.controllers.apisearch;

import com.musiccollector.api.musicbrainzapi.TrackQueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/search")
public class ApiSearchServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        String artist =request.getParameter("seacthtype");
        String track  =request.getParameter("searchcontent");



        TrackQueryBuilder tqb = new TrackQueryBuilder();


        response.setContentType("application/json");
        response.getWriter().write(tqb.search(track));
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