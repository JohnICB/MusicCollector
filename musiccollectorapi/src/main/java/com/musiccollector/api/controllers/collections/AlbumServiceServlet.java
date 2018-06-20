package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.musicbrainzapi.AlbumQueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/aboutalbum")
public class AlbumServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        String mbid = request.getParameter("mbid");

        AlbumQueryBuilder aqb = new AlbumQueryBuilder();


        response.setContentType("application/json");
        response.getWriter().write(aqb.getInfo(mbid));

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

//tomcat7:run -Dmaven.tomcat.port=8081