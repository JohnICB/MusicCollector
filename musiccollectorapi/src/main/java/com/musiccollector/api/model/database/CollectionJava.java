package com.musiccollector.api.model.database;

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


}
