package com.musiccollector.api.controllers.events;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/eventsService")
public class EventsServiceServlet extends HttpServlet
{

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            String rows = request.getParameter("rows");
            System.out.println(rows + " rows");

            EventsService eventsService;

            if (rows != null)
            {
                eventsService = new EventsService(Long.parseLong(rows));
            }
            else
            {
                eventsService = new EventsService(10);
            }

            response.getWriter();
            response.setContentType("application/json");
            response.getWriter().write(eventsService.getEvents());

//            request.getRequestDispatcher("/WEB-INF/views/TEMPLATE.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

           String rows = request.getParameter("rows");
        }

}


