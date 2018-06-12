package com.musiccollector.api.musicbrainzapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class AlbumQueryBuilder {

    private GetJson getJson = new GetJson();
    private final String json = "&format=json";
    private final String api = "http://ws.audioscrobbler.com/2.0/";
    private final String api_key = "&api_key=1016e6d54356a89b797df8223c93f1db";

    public String getInfo(String artist, String album,String autocorrect){
        JsonObject nou = new JsonObject();
        String url = api+"?method=album.getinfo"+api_key+"&artist="+artist+"&album="+album+"&autocorrect="+autocorrect+json;
        try {
            JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("album").getAsJsonObject().get("name"));
            nou.add("artist", jsonObject.get("album").getAsJsonObject().get("artist"));
            nou.add("mbid", jsonObject.get("album").getAsJsonObject().get("mbid"));
            nou.add("image", jsonObject.get("album").getAsJsonObject().get("image").getAsJsonArray().get(2));
            nou.add("wiki", jsonObject.get("album").getAsJsonObject().get("wiki"));


            System.out.println("this is: "+nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nou.toString();
    }
    public String getInfo(String mbid){


        JsonObject nou = new JsonObject();
        String url = api+"?method=album.getinfo"+api_key+"&mbid="+mbid+json;
        try {
             JsonObject jsonObject = getJson.Retrieve(url);


            nou.add("name", jsonObject.get("album").getAsJsonObject().get("name"));
            nou.add("artist", jsonObject.get("album").getAsJsonObject().get("artist"));
            nou.add("mbid", jsonObject.get("album").getAsJsonObject().get("mbid"));
            nou.add("image", jsonObject.get("album").getAsJsonObject().get("image").getAsJsonArray().get(2));
            nou.add("wiki", jsonObject.get("album").getAsJsonObject().get("wiki"));


            System.out.println("this is: "+nou.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nou.toString();

    }

    public String search(String album){




        String url = api+"?method=album.search"+api_key+"&album="+album+json;
        JsonObject nou = new JsonObject();
        JsonObject ret = new JsonObject();
        JsonArray artists = new JsonArray();

        try {
            JsonObject jsonObject = getJson.Retrieve(url);





            for(JsonElement j :  jsonObject.get("results").getAsJsonObject().get("albummatches").getAsJsonObject().getAsJsonArray("album")){
                JsonObject obj = new JsonObject();
                obj.add("name",j.getAsJsonObject().get("name"));
                obj.add("artist",j.getAsJsonObject().get("artist"));
                obj.add("image",j.getAsJsonObject().getAsJsonArray("image").get(2));
                obj.add("mbid",j.getAsJsonObject().get("mbid"));

                artists.add(obj);



            }
            nou.add("artist",artists);



        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("this is after catch:" + nou.toString());
        return nou.toString();




    }
}
