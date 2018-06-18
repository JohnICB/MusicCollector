package com.musiccollector.api.controllers.register;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musiccollector.api.controllers.login.LoginService;
import com.mysql.cj.log.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

//        request.setAttribute("registerStatus","\"NOTHING YET\"");

        System.out.println("/Get Register");
        if (!LoginService.isUserLoggedIn(request.getCookies()))
        {
            request.getRequestDispatcher("/WEB-INF/views/registration.html").forward(request, response);
        }
        else {
            System.out.println("redirect");
            response.sendRedirect("/welcome");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        BufferedReader payLoad = request.getReader();
        JsonObject jsonPayload = new JsonParser().parse(payLoad.readLine()).getAsJsonObject();

        System.out.println(jsonPayload.toString());

//        response.getWriter().write("yes");

        String name = jsonPayload.get("username").getAsString();
        String password = jsonPayload.get("psw").getAsString();
        String email = jsonPayload.get("email").getAsString();
        String account_type = jsonPayload.get("account-type").getAsString();



        int isPerson = 0;
        if (account_type.equalsIgnoreCase("personal"))
        {
            isPerson = 1;
        }

        RegisterService registerService = new RegisterService();
        String json = registerService.getResponseJson(name, email, password, isPerson).toString();

        System.out.println(name + " " + password + " " + email + " " + account_type);
        System.out.println(json);
        response.getWriter().write(json);

    }

}
