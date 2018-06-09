package com.musiccollector.api.controllers.register;

import com.musiccollector.api.controllers.login.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("registerStatus","\"NOTHING YET\"");

        Cookie cookies[] = request.getCookies();
//        System.out.println(cookies.length);
        boolean isConnected = false;

        if (cookies != null)
        {
            System.out.println("happening");
            isConnected = LoginService.isUserLoggedIn(cookies);
        }
//            System.out.println(loginToken + " " + user);

        System.out.println("/Register");
        if (!isConnected)
        {
            request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
        }
        else {
            System.out.println("redirect");
            response.sendRedirect("/welcome");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("username");
        String password = request.getParameter("psw");
        String email = request.getParameter("email");
        String account_type = request.getParameter("account-type");

        int isPerson = 0;
        if (account_type.equalsIgnoreCase("personal"))
        {
            isPerson = 1;
        }

        RegisterService registerService = new RegisterService();
        String json = registerService.getResponseJson(name, email, password, isPerson);

        System.out.println(name + " " + password + " " + email + " " + account_type);

//        if (newUserIsValid() && isNotDuplicated())
        {
//            request.getRequestDispatcher("/WEB-INF/views/registrationSucces.jsp").forward(request, response);
        }
//        else
        {
//            request.getRequestDispatcher("/WEB-INF/views/registrationFail.jsp").forward(request, response);
        }

        System.out.println(json);

        request.setAttribute("registerStatus", json);

        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
