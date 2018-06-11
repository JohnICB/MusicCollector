package com.musiccollector.api.model.database.entities;

import com.google.gson.JsonObject;
import com.musiccollector.api.model.Database;

import java.sql.*;
import java.util.Objects;

public class Vinyls {

  private long idVinyl;
  private String externalId;
  private String title;
  private String artists;
  private String region;
  private String age;
  private String album;
  private String size;
  private boolean isColored;
  private boolean isStereo;
  private boolean isSpecialEdition;
  private java.sql.Time duration;
  private String genre;
  private String rarity;
  private java.sql.Date releaseDate;

  public Vinyls()
  {

  }

    public Vinyls(String externalId, String title, String artists, String region, String age,
                  String album, String size, boolean isColored, boolean isStereo, boolean isSpecialEdition,
                  Time duration, String genre, String rarity, Date releaseDate) {
        this.externalId = externalId;
        this.title = title;
        this.artists = artists;
        this.region = region;
        this.age = age;
        this.album = album;
        this.size = size;
        this.isColored = isColored;
        this.isStereo = isStereo;
        this.isSpecialEdition = isSpecialEdition;
        this.duration = duration;
        this.genre = genre;
        this.rarity = rarity;
        this.releaseDate = releaseDate;
    }

    public Vinyls(long idVinyl, String externalId, String title, String artists, String region, String age, String album, String size, boolean isColored, boolean isStereo, boolean isSpecialEdition, Time duration, String genre, String rarity, Date releaseDate) {
        this.idVinyl = idVinyl;
        this.externalId = externalId;
        this.title = title;
        this.artists = artists;
        this.region = region;
        this.age = age;
        this.album = album;
        this.size = size;
        this.isColored = isColored;
        this.isStereo = isStereo;
        this.isSpecialEdition = isSpecialEdition;
        this.duration = duration;
        this.genre = genre;
        this.rarity = rarity;
        this.releaseDate = releaseDate;
    }

    public static Vinyls processResults(ResultSet resultSet) //select * from vinyls where id = ?
    {

        try {
            if (resultSet == null || !resultSet.next())
            {
                return null;
            }
            else
            {
                long idVinyl = resultSet.getLong(1);
                String externalID = resultSet.getString(2);
                String title = resultSet.getString(3);
                String artists = resultSet.getString(4);
                String region = resultSet.getString(5);
                String age = resultSet.getString(6);
                String album = resultSet.getString(7);
                String size = resultSet.getString(8);
                boolean isColored = resultSet.getBoolean(9);
                boolean isStereo = resultSet.getBoolean(10);
                boolean isSpecialEdition = resultSet.getBoolean(11);
                java.sql.Time duration = resultSet.getTime(12);
                String genre = resultSet.getString(13);
                String rarity = resultSet.getString(14);
                java.sql.Date releaseDate = resultSet.getDate(15);

                return new Vinyls(idVinyl, externalID, title, artists, region,age, album, size, isColored, isStereo, isSpecialEdition, duration, genre, rarity, releaseDate);


            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject toJson()
    {
        JsonObject vinylJson = new JsonObject();

        vinylJson.addProperty("title", Objects.requireNonNullElse(title, ""));
        vinylJson.addProperty("artists", Objects.requireNonNullElse(this.artists, ""));
        vinylJson.addProperty("region",  Objects.requireNonNullElse(this.region, ""));
        vinylJson.addProperty("age",  Objects.requireNonNullElse(this.age, ""));
        vinylJson.addProperty("album",  Objects.requireNonNullElse(this.album, ""));
        vinylJson.addProperty("size",  Objects.requireNonNullElse(this.size, ""));
        vinylJson.addProperty("isColored",  Objects.requireNonNullElse(this.isColored, false));
        vinylJson.addProperty("isStereo",  Objects.requireNonNullElse(this.isStereo, false));
        vinylJson.addProperty("isSpecialEdition",  Objects.requireNonNullElse(this.isSpecialEdition, false));
        vinylJson.addProperty("duration",  Objects.requireNonNullElse(this.duration.toString(), ""));
        vinylJson.addProperty("genre",  Objects.requireNonNullElse(this.genre, ""));
        vinylJson.addProperty("rarity",  Objects.requireNonNullElse(this.rarity, ""));
        vinylJson.addProperty("releaseDate",  Objects.requireNonNullElse(this.releaseDate.toString(), ""));

        return vinylJson;

    }


    public void insert() {

        Connection connection = null;
        try {
            connection = Database.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO vinyls (EXTERNAL_ID, TITLE, ARTISTS, REGION" +
                        ", AGE, ALBUM, SIZE, IS_COLORED, IS_STEREO, IS_SPECIAL_EDITION," +
                        "DURATION, GENRE, RARITY, RELEASE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, this.externalId);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.artists);
            preparedStatement.setString(4, this.region);
            preparedStatement.setString(5, this.age);
            preparedStatement.setString(6, this.album);
            preparedStatement.setString(7, this.size);
            preparedStatement.setBoolean(8, this.isColored);
            preparedStatement.setBoolean(9, this.isStereo);
            preparedStatement.setBoolean(10, this.isSpecialEdition);
            preparedStatement.setTime(11, this.duration);
            preparedStatement.setString(12, this.genre);
            preparedStatement.setString(13, this.rarity);
            preparedStatement.setDate(14, this.releaseDate);

            preparedStatement.executeUpdate();

            ResultSet r = connection.prepareStatement("SELECT LAST_INSERT_ID() from vinyls").executeQuery();

            this.idVinyl = r.getLong(1);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getIdVinyl() {
        return idVinyl;
    }
}

