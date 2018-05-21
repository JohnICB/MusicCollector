package com.musiccollector.api.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("/Register");
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("username");
        String password = request.getParameter("psw");
        String email = request.getParameter("email");
        String account_type = request.getParameter("account-type");

        

        //TODO: Hash password
        //TODO: Check if reienter password is == password

        System.out.println(name + " " + password + " " + email + " " + account_type);

//        if (newUserIsValid() && isNotDuplicated())
        {
//            request.getRequestDispatcher("/WEB-INF/views/registrationSucces.jsp").forward(request, response);
        }
//        else
        {
//            request.getRequestDispatcher("/WEB-INF/views/registrationFail.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
