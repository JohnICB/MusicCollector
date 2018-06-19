package com.musiccollector.api.controllers.collections;

import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.entities.Cassettes;
import com.musiccollector.api.model.database.entities.Collections;
import com.musiccollector.api.model.database.entities.Vinyls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/previewElement")
public class ElementPreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        long musicID = -1;
        musicID = Long.parseLong(request.getParameter("id"));
        boolean isVinyl = Boolean.parseBoolean(request.getParameter("isVinyl"));
        System.out.println("get prv elm " + musicID + " " +isVinyl);

        if (musicID < 0 || !LoginService.isUserLoggedIn(request.getCookies())) return;

        if (isVinyl)
        {
            Vinyls v = Vinyls.getVinylByID(musicID);

            if (v != null)
            {
                response.setContentType("application/json");
                response.getWriter().write(v.toJson().toString());
                System.out.println(v.toJson().toString());
                return;
            }
        }
        else
        {
            Cassettes c = Cassettes.getCassetteByID(musicID);

            if (c!=null)
            {
                System.out.println(c.toJson().toString());
                response.setContentType("application/json");
                response.getWriter().write(c.toJson().toString());
                return;
            }
        }

        response.setContentType("application/json");
        response.getWriter().write("error");


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