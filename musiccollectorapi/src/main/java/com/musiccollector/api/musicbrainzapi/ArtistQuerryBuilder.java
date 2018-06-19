package com.musiccollector.api.musicbrainzapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class ArtistQuerryBuilder {

    private final String json = "&format=json";
    private final String api = "http://ws.audioscrobbler.com/2.0/";
    private final String api_key = "&api_key=1016e6d54356a89b797df8223c93f1db";
    private GetJson getJson = new GetJson();

    public String getInfo(String artist, String autocorrect) {


        JsonObject nou = new JsonObject();
        String url = api + "?method=artist.getinfo" + api_key + "&artist=" + artist + "&autocorrect=" + autocorrect + json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("artist").getAsJsonObject().get("name"));
            nou.add("mbid", jsonObject.get("artist").getAsJsonObject().get("mbid"));


            System.out.println("this is: " + nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nou.toString();
    }

    public String getInfo(String mbid) {


        JsonObject nou = new JsonObject();
        String url = api + "?method=artist.getinfo" + api_key + "&mbid=" + mbid + json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("artist").getAsJsonObject().get("name"));
            nou.add("mbid", jsonObject.get("artist").getAsJsonObject().get("mbid"));


            System.out.println("this is: " + nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nou.toString();
    }

    public String search(String artist) {


        String url = api + "?method=artist.search" + api_key + "&artist=" + artist + json;
        JsonObject nou = new JsonObject();
        JsonObject ret = new JsonObject();
        JsonArray artists = new JsonArray();

        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            //nou.add("trackmatches", jsonObject.get("results").getAsJsonObject().get("trackmatches"));


            for (JsonElement j : jsonObject.get("results").getAsJsonObject().get("artist").getAsJsonObject().getAsJsonArray()) {
                JsonObject obj = new JsonObject();
                obj.add("name", j.getAsJsonObject().get("name"));
                obj.add("mbid", j.getAsJsonObject().get("mbid"));
                obj.add("image", j.getAsJsonObject().getAsJsonArray("image").get(2));

                artists.add(obj);


            }
            nou.add("artist", artists);


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("this is after catch:" + nou.toString());
        return nou.toString();


    }


}
