package com.musiccollector.api.model.database.entities;


import com.musiccollector.api.model.Database;
import com.musiccollector.api.model.database.CollectionJava;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Collections {

  private long idCollection;
  private long idMusic;
  private long idUser;
  private String description;
  private String isVinyl;

    public static ArrayList<CollectionJava> getCollections(long idUser)
    {
        ArrayList<CollectionJava> collections = new ArrayList<>();

        try {
            ResultSet resultSet = Database.selectQuery(
                    "SELECT ID_COLLECTION FROM COLLECTIONS WHERE ID_USER = ? ",
                    idUser);

            if (!resultSet.next()) { return collections; }

//            resultSet.beforeFirst();

            collections = transformResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collections;
    }

    private static ArrayList<CollectionJava> transformResults(ResultSet resultSet) throws SQLException
    {
        ArrayList<Long> musicIDs = new ArrayList<>();
        ArrayList<Long> userIDs = new ArrayList<>();
        String title = "";
        String description = "";

        ArrayList<CollectionJava> collectionJavas= new ArrayList<>();
        boolean isVinyl = false;

        while (resultSet.next())
        {
            long id = resultSet.getLong(1);

            ResultSet newResults = Database.selectQuery(
                    "SELECT ID_MUSIC, ID_USER, DESCRIPTION, IS_VINYL, TITLE FROM COLLECTIONS" +
                            "WHERE COLLECTION ID = ?", id);

            if (!newResults.next()) { continue; }

            description = newResults.getString(3);
            isVinyl = newResults.getBoolean(4);
            title = newResults.getString(5);

            while (newResults.next())
            {
                musicIDs.add(newResults.getLong(1));
                userIDs.add(newResults.getLong(2));
            }

            collectionJavas.add(new CollectionJava(id, userIDs, musicIDs, description,
                    isVinyl, title));

        }


        return collectionJavas;

    }


    public long getIdCollection() {
    return idCollection;
  }

  public void setIdCollection(long idCollection) {
    this.idCollection = idCollection;
  }


  public long getIdMusic() {
    return idMusic;
  }

  public void setIdMusic(long idMusic) {
    this.idMusic = idMusic;
  }


  public long getIdUser() {
    return idUser;
  }

  public void setIdUser(long idUser) {
    this.idUser = idUser;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getIsVinyl() {
    return isVinyl;
  }

  public void setIsVinyl(String isVinyl) {
    this.isVinyl = isVinyl;
  }

}
