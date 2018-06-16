package com.musiccollector.api.model.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.musiccollector.api.model.Database;
import com.musiccollector.api.model.database.entities.Cassettes;
import com.musiccollector.api.model.database.entities.Vinyls;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

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

    public long getCollectionId() {return collectionId; }

    public static JsonArray toJsonPreview (ArrayList<CollectionJava> collectionJava)
    {
        JsonArray jsonArray = new JsonArray();
        JsonObject jobj;

        HashSet<Long> cache = new HashSet<>();

        for (CollectionJava i : collectionJava)
        {
            if (!cache.contains(i.getCollectionId()))
            {
                cache.add(i.getCollectionId());

                jobj = new JsonObject();

                jobj.addProperty("title", i.getTitle());
                jobj.addProperty("description", i.getDescription());
                jobj.addProperty("isVinyl", i.isVinyl());
                jobj.addProperty("id", i.getCollectionId());

                jsonArray.add(jobj);
            }
        }

        return jsonArray;
    }

    public ArrayList<Vinyls> getVinylContent()
    {

        ArrayList<Vinyls> vinylArray = new ArrayList<>();
        for (long id : id_music)
        {
            try {
                ResultSet resultSet = Database.selectQuery(
                        "SELECT * FROM VINYLS WHERE ID_VINYL = ?", id);

                Vinyls vinyl = Vinyls.processResults(resultSet);

                if (vinyl != null)
                {
                    vinylArray.add(vinyl);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return vinylArray;
    }

    public ArrayList<Cassettes> getCassetesContent(CollectionJava collectionJava)
    {
        ArrayList<Cassettes> cassettesArray = new ArrayList<>();

        return cassettesArray;
    }

//    public static CollectionJava getCollectionByNameAndUID(String name, long uid)
//    {
//
//    }

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
