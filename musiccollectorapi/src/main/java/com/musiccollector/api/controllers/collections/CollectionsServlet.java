package com.musiccollector.api.controllers.collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Cassettes;
import com.musiccollector.api.model.database.entities.Collections;
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
import java.util.Objects;

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


        boolean isLoggedIn = LoginService.isUserLoggedIn(request.getCookies());
        String name = "";

        if (isLoggedIn)
        {
            for (Cookie c : request.getCookies())
            {
                if (c.getName().equals("user"))
                    name = c.getValue();
            }

            long uid = Users.getIDbyName(name);
            String title = request.getParameter("title");
            String isvin = request.getParameter("isVinyl");
            boolean isVinyl = Objects.requireNonNull(isvin).equals("true");

            String description = request.getParameter("description");
            if (description == null)
                description = "";

            String reqType = request.getParameter("reqType");
            System.out.println(reqType + reqType.equals("add") + " - " + isVinyl);
            if (reqType.equals("new"))
            {
                Collections.insert(uid, title, isVinyl, description);
            }
            else {
                if (reqType.equals("add"))
                {
                    if (isVinyl)
                    {
                        System.out.println("add vinyl /collection");
                        Vinyls vinyl  = new Vinyls(
                        request.getParameter("mbid"),
                        request.getParameter("musicTitle"),
                        request.getParameter("artists"),
                        request.getParameter("region"),
                        request.getParameter("age"),
                        request.getParameter("album"),
                        request.getParameter("size"),
                        Boolean.parseBoolean(request.getParameter("isColored")),
                        Boolean.parseBoolean(request.getParameter("isStereo")),
                        Boolean.parseBoolean(request.getParameter("isSpecialEdition")),
                        request.getParameter("duration"),
                        request.getParameter("genre"),
                        request.getParameter("rarity"),
                        request.getParameter("releaseDate"));

                        vinyl.insert();

                        Collections.insert(uid, vinyl.getIdVinyl(), title, true,description);
                    }
                    else
                    {
                        System.out.println("add cassettes /collections");

                        Cassettes cassette = new Cassettes(
                                request.getParameter("mbid"),
                                request.getParameter("musicTitle"),
                                request.getParameter("album"),
                                request.getParameter("artists"),
                                request.getParameter("genre"),
                                request.getParameter("duration"),
                                request.getParameter("age"),
                                request.getParameter("usageGrade"),
                                request.getParameter("releaseDate")
                        );

                        cassette.insert();
                        Collections.insert(uid, cassette.getIdCassettes(), title, true,description);
                    }
                }
            }


        }


        if (Math.random() > 0.5f)
            response.sendRedirect("welcome");
        else {
            request.getRequestDispatcher("/WEB-INF/views/TEMPLATE2.jsp").forward(request, response);
        }
    }

}