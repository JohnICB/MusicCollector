package com.musiccollector.api.controllers.collections;

import com.google.gson.JsonArray;
import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Collections;
import com.musiccollector.api.model.database.entities.Vinyls;
import com.musiccollector.api.model.database.user.ConnectedUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/vinylsService")
public class VinylsServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("/vinylsGET");


        if (LoginService.isUserLoggedIn(request.getCookies())) {
            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            long colID = Long.parseLong(request.getParameter("id"));

            CollectionJava collectionJava = Collections.getCollectionByID(colID);

            if (collectionJava == null || !Collections.hasUser(uid, colID)) {
                response.setContentType("application/json");
                response.getWriter().write("fail");
                return;
            }

            ArrayList<Vinyls> vinyls = collectionJava.getVinylContent();

            JsonArray jarray = new JsonArray();
            for (Vinyls v : vinyls) {
                jarray.add(v.toJson());
            }

            response.setContentType("application/json");
            response.getWriter().write(jarray.toString());

        }

        //response.setContentType("application/json");
        //response.getWriter().write("");
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

