package com.musiccollector.api.controllers.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.musiccollector.api.model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EventsService {

    private long rows;

    public EventsService(long rows) {
        this.rows = rows + 1;
    }


    public String getEvents() {
//        Map<String, String> eventsJson = new LinkedHashMap<>();
        JsonObject eventsJson = new JsonObject();
        eventsJson.addProperty("status", "empty");


        try {
            ResultSet resultSet = Database.selectQuery("SELECT * FROM (SELECT TITLE, DESCRIPTION, START_DATE, END_DATE, URL" +
                    " FROM CONCERTS ORDER BY START_DATE IS NULL, START_DATE) AS T LIMIT ? ", this.rows);

            if (!resultSet.next()) {
                return eventsJson.toString();
            }

            eventsJson.addProperty("status", "succes");

            JsonArray ja = new JsonArray();
            Integer resultsNr = 0;

            while (resultSet.next()) {
                resultsNr++;

                String title = resultSet.getString(1);
                String description = resultSet.getString(2);
                Date start = resultSet.getDate(3);
                Date end = resultSet.getDate(4);
                String url = resultSet.getString(5);

                String startDate = "";
                String endDate = "";


                if (start != null) {
                    startDate = start.toString();
                }
                if (end != null) {
                    endDate = end.toString();
                }

                JsonObject j = new JsonObject();

                j.addProperty("title", title);
                j.addProperty("description", description);
                j.addProperty("start_date", startDate);
                j.addProperty("end_date", endDate);
                j.addProperty("URL", url);

//                System.out.println("*******");
//                System.out.println(j.toString());
//                System.out.println("*******");

                ja.add(j);

            }//end while

            eventsJson.add("events", ja);
            eventsJson.addProperty("rows", resultsNr.toString());

//            System.out.println(eventsJson.toString());

            return eventsJson.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventsJson.toString();
    }


}
