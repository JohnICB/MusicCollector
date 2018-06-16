package com.musiccollector.api.model.database.entities;


import com.musiccollector.api.model.Database;
import com.musiccollector.api.model.database.CollectionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                    "SELECT ID_MUSIC, ID_USER, DESCRIPTION, IS_VINYL, TITLE FROM COLLECTIONS WHERE ID_COLLECTION = ?", id);



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

    public static long insert(long uid, String title, boolean isVinyl, String description) {

        try {
            Connection connection = Database.getConnection();

            ResultSet rs
                    = connection.prepareStatement
                    ("SELECT MAX(ID_COLLECTION) FROM COLLECTIONS").
                    executeQuery();

            long colID = -1;
            if (rs.next())
                colID = rs.getLong(1) + 1;

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO collections (ID_USER, DESCRIPTION, IS_VINYL, TITLE, ID_COLLECTION) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setLong(1, uid);
            preparedStatement.setString(2, description);
            preparedStatement.setBoolean(3, isVinyl);
            preparedStatement.setString(4, title);
            preparedStatement.setLong(5, colID);

            preparedStatement.executeUpdate();

            return colID;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void insertByColID(long colID, long musicID)
    {
        try {
            ResultSet rs = Database.selectQuery(
                    "SELECT ID_USER, TITLE, DESCRIPTIOM, IS_VINYL FROM" +
                            " COLLECTIONS WHERE ID_COLLECTION = ?", colID);

            if (!rs.next()) { return;}

            long uid = rs.getLong(1);
            String title = rs.getString(2);
            String description = rs.getString(3);
            boolean isVinyl = rs.getBoolean(4);

            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(
                    "INSERT INTO collections (ID_USER, DESCRIPTION, IS_VINYL, TITLE, ID_COLLECTION) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setLong(1, uid);
            preparedStatement.setString(2, description);
            preparedStatement.setBoolean(3, isVinyl);
            preparedStatement.setString(4, title);
            preparedStatement.setLong(5, colID);

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Deprecated
    public static void insert(long uid, long idMusic, String title, boolean isVinyl, String description) {

        System.out.println("id music " + idMusic);

        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO collections (ID_USER, DESCRIPTION, IS_VINYL, TITLE, ID_MUSIC) VALUES (? ,?, ?, ?, ?)");

            preparedStatement.setLong(1,uid);
            preparedStatement.setString(2,description);
            preparedStatement.setBoolean(3,isVinyl);
            preparedStatement.setString(4,title);
            preparedStatement.setLong(5,idMusic);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static boolean isVinylCol(long colID)
    {

        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(
                    "SELECT IS_VINYL FROM collections" +
                            " WHERE ID_COLLECTION = ?"
            );

            ps.setLong(1, colID);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getBoolean(1);
            else throw new SQLException("VINYL TYPE FOUND ACI");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void delete(long collectionID) {
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(
                    "DELETE FROM collections" +
                            " WHERE ID_COLLECTION = ?"
            );

            ps.setLong(1, collectionID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasUser(long uid, long idCollection) {

        try {
            ResultSet rs = Database.selectQuery("SELECT ID_USER FROM COLLECTIONS WHERE" +
                    " ID_COLLECTION = ?", idCollection);

            if (!rs.next()) return false;

            while (rs.next())
            {
                if (rs.getLong(1) == uid)
                    return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
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
