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


/**
 * @author Ciobanu Ionut
 *
 *  Pentru GET:
 *  -trimite un array de json uri cu toate collectiile utilizatorului logat
 *
 *  Pentru POST:
 *  -primeste un json cu titlu, descrierea si tipul unei noi collectii
 *  -o creeaza si o adauga in baza de date
 *  -returneaza id ul collectiei ce va fi pus ca parametru in link pentru a fi utilizat mai tarziu pentru preluarea
 *   obiectelor din acea collectie
 *
 *  Pentru PUT:
 *  -primeste un json cu datele unei cassette / unui vinyl si un id al unei collectii
 *  -creeaza vinyl ul / cassetta si o baga in baza de date
 *  -adauga in baza de date in collectiaa respectiva vinylul / casseta
 *  -returneaza succes / fail
 *
 *  Pentru DELETE:
 *  -primeste un ID ale unei collectii si verifica daca apartine userului logat
 *  -sterge collectia din baza de date
 *
 */


@WebServlet(urlPatterns = "/collections")
public class CollectionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("get /collections");

        if (LoginService.isUserLoggedIn(request.getCookies()))
        {
            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            //facem un vector de jsonuri din toate collectiile pe care le are userul la user_id ul pe care il luam
            //de la contul conectat

            if (uid > 0)
            {
                JsonArray collectionsJson = CollectionJava.toJsonPreview(Collections.getCollections(uid));

                response.setContentType("application/json");
                response.getWriter().write(collectionsJson.toString());
            }
            else response.getWriter().write("internal server error, try again later");
        }

//        Cookie[] cookies = request.getCookies();
//
//            System.out.println("/collections");
//
//        if (cookies != null)
//        {
//            for (Cookie c : cookies)
//            {
//                if (c.getName().equals("user"))
//                {
//                        long id = Users.getIDbyName(c.getValue());
//
//                        if (id  < 0)
//                        {
//                            break;
//                        }
//                        ArrayList<CollectionJava> collectionsJavas = Collections.getCollections(id);
//                        JsonArray jsonArray = CollectionJava.toJsonPreview(collectionsJavas);
//
//                    JsonObject collectionsJson = new JsonObject();
//
//                    if (jsonArray.size() > 0)
//                    {
//                        collectionsJson.addProperty("status", "succes");
//                    }
//                    else
//                    {
//                        collectionsJson.addProperty("status", "empty");
//                    }
//
//                    collectionsJson.add("data", jsonArray);
//
//                    System.out.println(collectionsJson.toString());
//
//                    response.setContentType("application/json");
//                    response.getWriter().write(collectionsJson.toString());
//
//                }
//            }
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        if(LoginService.isUserLoggedIn(request.getCookies()))
        {
            BufferedReader payLoad = request.getReader();
            JsonObject jsonPayload = new JsonParser().parse(payLoad.readLine()).getAsJsonObject();

            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            String title = jsonPayload.get("title").getAsString();
            String description = jsonPayload.get("description").getAsString();
            boolean isVinyl = jsonPayload.get("isVinyl").getAsBoolean();

            System.out.println( "Am primit ");
            System.out.println(jsonPayload.toString());

            long idCol = Collections.insert(uid, title, isVinyl, description);

            JsonObject responseJson = new JsonObject();
            if (idCol > 0)
            {
                responseJson.addProperty("id", idCol);
                responseJson.addProperty("message", "You succesfuly added this collection");
            }
            else
            {
                responseJson.addProperty("id", "-1");
                responseJson.addProperty("message", "Internal server error. Try again later");
            }

            response.setContentType("application/json");
            response.getWriter().write(responseJson.toString());

            System.out.println("Am trimis");
            System.out.println(responseJson.toString());

        }


        //TODO: Acum ca ai jsonul verifica ce e mai jos si baga in baza de date si returneaza ultimul id cu care a fost bagat
        // apoi cand da get la idul ala returnezi pagina de colectie cu vinylurile la idul ala


//        String name = "";
//
//        JsonObject jobj = new JsonObject();
//
//        if (isLoggedIn)
//        {
//            for (Cookie c : request.getCookies())
//            {
//                if (c.getName().equals("user"))
//                    name = c.getValue();
//            }
//
//            long uid = Users.getIDbyName(name);
//            String title = request.getParameter("title");
//            String isvin = request.getParameter("isVinyl");
//            boolean isVinyl = Objects.requireNonNull(isvin).equals("true");
//
//            String description = request.getParameter("description");
//            if (description == null)
//                description = "";
//
//            String reqType = request.getParameter("reqType");
//            System.out.println(reqType + reqType.equals("add") + " - " + isVinyl);
//            if (reqType.equals("new"))
//            {
//                Collections.insert(uid, title, isVinyl, description);
//                jobj.addProperty("status","success");
//            }
//            else {
//                if (reqType.equals("add"))
//                {
//                    if (isVinyl)
//                    {
//                        System.out.println("add vinyl /collection");
//                        Vinyls vinyl  = new Vinyls(
//                        request.getParameter("mbid"),
//                        request.getParameter("musicTitle"),
//                        request.getParameter("artists"),
//                        request.getParameter("region"),
//                        request.getParameter("age"),
//                        request.getParameter("album"),
//                        request.getParameter("size"),
//                        Boolean.parseBoolean(request.getParameter("isColored")),
//                        Boolean.parseBoolean(request.getParameter("isStereo")),
//                        Boolean.parseBoolean(request.getParameter("isSpecialEdition")),
//                        request.getParameter("duration"),
//                        request.getParameter("genre"),
//                        request.getParameter("rarity"),
//                        request.getParameter("releaseDate"));
//
//                        vinyl.insert();
//
//                        Collections.insert(uid, vinyl.getIdVinyl(), title, true,description);
//                        jobj.addProperty("status","success");
//                    }
//                    else
//                    {
//                        System.out.println("add cassettes /collections");
//
//                        Cassettes cassette = new Cassettes(
//                                request.getParameter("mbid"),
//                                request.getParameter("musicTitle"),
//                                request.getParameter("album"),
//                                request.getParameter("artists"),
//                                request.getParameter("genre"),
//                                request.getParameter("duration"),
//                                request.getParameter("age"),
//                                request.getParameter("usageGrade"),
//                                request.getParameter("releaseDate")
//                        );
//
//                        cassette.insert();
//                        Collections.insert(uid, cassette.getIdCassettes(), title, true,description);
//                        jobj.addProperty("status","success");
//                    }
//                }
//            }
//
//
//        }
//        else
//        {
//            jobj.addProperty("status","fail");
//        }
//
//
//        if (Math.random() > 0.5f)
//            response.sendRedirect("welcome");
//        else {
//            request.getRequestDispatcher("/WEB-INF/views/TEMPLATE2.jsp").forward(request, response);
//        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPut(httpServletRequest, httpServletResponse);

        if (LoginService.isUserLoggedIn(request.getCookies()))
        {
            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            //facem un vector de jsonuri din toate collectiile pe care le are userul la user_id ul pe care il luam
            //de la contul conectat

            if (uid > 0)
            {
                BufferedReader payLoad = request.getReader();
                JsonObject jsonPayload = new JsonParser().parse(payLoad.readLine()).getAsJsonObject();

                long collectionID = -1;
                collectionID = jsonPayload.get("colID").getAsLong();

                if (collectionID < 0)
                {
                    response.setContentType("application/json");
                    response.getWriter().write("fail");
                    return;
                }

                if (Collections.isVinylCol(collectionID))
                {
                    Vinyls newVinyl = Vinyls.fromJson(jsonPayload);
                    newVinyl.insert();

                    Collections.insertByColID(collectionID, newVinyl.getIdVinyl());
                }
                else
                {
                    Cassettes newCassettes = Cassettes.fromJson(jsonPayload);
                    newCassettes.insert();

                    Collections.insertByColID(collectionID, newCassettes.getIdCassettes());
                }

            }


            response.setContentType("application/json");
            response.getWriter().write("success");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (LoginService.isUserLoggedIn(request.getCookies()))
        {
            long uid = ConnectedUsers.getUserIDByCookies(request.getCookies());
            //facem un vector de jsonuri din toate collectiile pe care le are userul la user_id ul pe care il luam
            //de la contul conectat

            if (uid > 0) {
                BufferedReader payLoad = request.getReader();
                JsonObject jsonPayload = new JsonParser().parse(payLoad.readLine()).getAsJsonObject();

                long collectionID = -1;
                collectionID = jsonPayload.get("colID").getAsLong();
                System.out.println("collectionID: "+collectionID+Collections.hasUser(uid, collectionID));
                if (collectionID < 0 || !Collections.hasUser(uid, collectionID))
                {
                    response.setContentType("application/json");
                    response.getWriter().write("fail");
                    return;
                }

                    Collections.delete(collectionID);

            }
        }
        response.setContentType("application/json");
        response.getWriter().write("succes");

    }
}