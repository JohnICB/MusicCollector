package com.musiccollector.api.model.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class CollectionJava {
    private ArrayList<Long> idUser;
    private ArrayList<Long> id_music;
    private String description;
    private String title;
    private long collectionId;
    private boolean isVinyl;

    public CollectionJava(long collectionId, ArrayList<Long> idUser, ArrayList<Long> id_music, String description, boolean isVinyl, String title) {
        this.collectionId = collectionId;
        this.idUser = idUser;
        this.id_music = id_music;
        this.description = description;
        this.isVinyl = isVinyl;
        this.title = title;
    }

    public static JsonArray toJsonPreview (ArrayList<CollectionJava> collectionJava)
    {
        JsonArray jsonArray = new JsonArray();
        JsonObject jobj = new JsonObject();

        for (CollectionJava i : collectionJava)
        {
            jobj = new JsonObject();

            jobj.addProperty("title", i.getTitle());
            jobj.addProperty("description", i.getDescription());
            jobj.addProperty("isVinyl", i.isVinyl());

            jsonArray.add(jobj);
        }



        return jsonArray;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVinyl() {
        return isVinyl;
    }
}
