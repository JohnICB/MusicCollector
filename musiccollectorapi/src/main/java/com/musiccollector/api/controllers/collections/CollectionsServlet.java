package com.musiccollector.api.controllers.collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Collections;
import com.musiccollector.api.model.database.user.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/collections")

public class CollectionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Cookie[] cookies = request.getCookies();

        System.out.println("/collections");

        if (cookies != null)
        {
            for (Cookie c : cookies)
            {
                if (c.getName().equals("user"))
                {
                        long id = Users.getIDbyName(c.getValue());

                        if (id  < 0)
                        {
                            break;
                        }
                        ArrayList<CollectionJava> collectionsJavas = Collections.getCollections(id);
                        JsonArray jsonArray = CollectionJava.toJsonPreview(collectionsJavas);

                    JsonObject collectionsJson = new JsonObject();

                    if (jsonArray.size() > 0)
                    {
                        collectionsJson.addProperty("status", "succes");
                    }
                    else
                    {
                        collectionsJson.addProperty("status", "empty");
                    }

                    collectionsJson.add("data", jsonArray);

                    System.out.println(collectionsJson.toString());

                    response.getWriter();
                    response.setContentType("application/json");
                    response.getWriter().write(collectionsJson.toString());

                }
            }
        }

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