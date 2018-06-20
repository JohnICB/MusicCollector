package com.musiccollector.api.controllers.apisearch;

import com.musiccollector.api.musicbrainzapi.AlbumQueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/albuminfo")
public class AlbumInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String artist = request.getParameter("artist");
        String album = request.getParameter("album");

        System.out.println("artist: " + artist + " album: " + album);
        AlbumQueryBuilder aqb = new AlbumQueryBuilder();
        String info = aqb.getInfo(album, artist, "1");
        System.out.println("Album info : " + info);

        response.setContentType("application/json");
        response.getWriter().write(info);
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