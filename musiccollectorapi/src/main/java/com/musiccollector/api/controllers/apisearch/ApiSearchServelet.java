package com.musiccollector.api.controllers.apisearch;

import com.musiccollector.api.musicbrainzapi.AlbumQueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/searchapi")
public class ApiSearchServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        String artist = request.getParameter("seacthtype");
        String searchcontent = request.getParameter("searchContent");

        System.out.println("cauta " + searchcontent);

        AlbumQueryBuilder tqb = new AlbumQueryBuilder();


        response.setContentType("application/json");
        response.getWriter().write(tqb.search(searchcontent));
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