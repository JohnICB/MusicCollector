package com.musiccollector.api.musicbrainzapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class TrackQueryBuilder {

    private GetJson getJson = new GetJson();
    private final String json = "&format=json";
    private final String api = "http://ws.audioscrobbler.com/2.0/";
    private final String api_key = "&api_key=1016e6d54356a89b797df8223c93f1db";

    public String getInfo(String artist, String track,String autocorrect){

        JsonObject nou = new JsonObject();
        String url =api+"?method=track.getinfo"+api_key+"&artist="+artist+"&track="+track+"&autocorrect="+autocorrect+json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("track").getAsJsonObject().get("name"));
            nou.add("mbid", jsonObject.get("track").getAsJsonObject().get("mbid"));
            nou.add("artist", jsonObject.get("track").getAsJsonObject().get("artist"));
            nou.add("album", jsonObject.get("track").getAsJsonObject().get("album"));



            System.out.println("this is: "+nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nou.toString();
    }
    public String getInfo(String mbid){



        JsonObject nou = new JsonObject();
        String url =api+"?method=track.getinfo"+api_key+"&mbid="+mbid+json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("track").getAsJsonObject().get("name"));
            nou.add("mbid", jsonObject.get("track").getAsJsonObject().get("mbid"));
            nou.add("artist", jsonObject.get("track").getAsJsonObject().get("artist"));
            nou.add("album", jsonObject.get("track").getAsJsonObject().get("album"));



            System.out.println("this is: "+nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nou.toString();
    }

    public String search(String track){

        JsonObject nou = new JsonObject();
        JsonObject ret = new JsonObject();
        JsonArray trackmatches = new JsonArray();
        String url =api+"?method=track.search"+"&track="+track+api_key+json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("trackmatches", jsonObject.get("results").getAsJsonObject().get("trackmatches"));


            for(JsonElement j :  jsonObject.get("results").getAsJsonObject().get("trackmatches").getAsJsonObject().get("track").getAsJsonArray()){

                trackmatches.add(j);
                System.out.println("this is a matched track:"+j.toString());


            }
           // nou.add("track ",trackmatches);

            System.out.println("this is track search:" + nou.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("this is after catch:" + nou.toString());
        return nou.toString();

    }
    public String search(String track, String artist){

        return api+"?method=track.search"+api_key+"&track="+track+"&artist="+artist+json;
    }


}
