package com.musiccollector.api.controllers.collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musiccollector.api.controllers.login.LoginService;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Cassettes;
import com.musiccollector.api.model.database.entities.Collections;
import com.musiccollector.api.model.database.entities.Vinyls;
import com.musiccollector.api.model.database.user.ConnectedUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/collectionsService")
public class CollectionsServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("/collections service");

        if (LoginService.isUserLoggedIn(request.getCookies())) {
            boolean ok = false;
            System.out.println("get la collection service");
            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            long colID = Long.parseLong(request.getParameter("id"));

            CollectionJava collectionJava = Collections.getCollectionByID(colID);

            if (collectionJava == null || !Collections.hasUser(uid, colID)) {
                response.setContentType("application/json");
                response.getWriter().write("fail");
                return;
            }

            JsonArray jarray = new JsonArray();

            System.out.println("add obiecte to jarray");
            if (collectionJava.isVinyl()) {
                ok = true;
                ArrayList<Vinyls> vinyls = collectionJava.getVinylContent();
                for (Vinyls v : vinyls) {
                    jarray.add(v.toJson());
                    ok = false;
                }
            } else {
                ArrayList<Cassettes> cassettes = collectionJava.getCassetesContent();
                for (Cassettes c : cassettes) {
                    jarray.add(c.toJson());
                    ok = false;
                }
            }

            if (ok && jarray.size() == 0) {


                JsonObject job = new JsonObject();
                job.addProperty("isColored", "");
                jarray.add(job);

            }

            System.out.println("trimit " + jarray.toString());
            response.setContentType("application/json");
            response.getWriter().write(jarray.toString());

        }

        //response.setContentType("application/json");
        //response.getWriter().write("");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (LoginService.isUserLoggedIn(request.getCookies())) {
            BufferedReader payLoad = request.getReader();
            JsonObject jsonPayload = new JsonParser().parse(payLoad.readLine()).getAsJsonObject();

            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            long collectionID = jsonPayload.get("colID").getAsLong();
            long musicID = jsonPayload.get("musicID").getAsLong();

            if (collectionID < 0 || !Collections.hasUser(uid, collectionID) || musicID < 0) {
                response.setContentType("application/json");
                response.getWriter().write("fail");
                return;
            }

            Collections.deleteVinylOrCassette(musicID);
            response.setContentType("application/json");
            response.getWriter().write("success");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("mustBeLoggedIn");
        }

    }
}

