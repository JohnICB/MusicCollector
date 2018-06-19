package com.musiccollector.api.model.database.entities;


import com.google.gson.JsonObject;
import com.musiccollector.api.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Cassettes {

    private long idCassettes;
    private String externalId;
    private String title;
    private String album;
    private String artists;
    private String genre;
    private String duration;
    private String age;
    private String usageGrade;
    private String releaseDate;
    private String region;

    public Cassettes(String externalId, String title, String album, String artists, String genre, String duration, String age, String usageGrade, String releaseDate, String region) {
        this.externalId = externalId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.genre = genre;
        this.duration = duration;
        this.age = age;
        this.usageGrade = usageGrade;
        this.releaseDate = releaseDate;
        this.region = region;
    }

    public Cassettes(long idCassettes, String externalId, String title, String album, String artists, String genre, String duration, String age, String usageGrade, String releaseDate, String region) {
        this.idCassettes = idCassettes;
        this.externalId = externalId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.genre = genre;
        this.duration = duration;
        this.age = age;
        this.usageGrade = usageGrade;
        this.releaseDate = releaseDate;
        this.region = region;
    }

    public Cassettes(String title, String album, String artists,
                     String genre, String duration, String age,
                     String usageGrade, String releaseDate, String region) {
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.genre = genre;
        this.duration = duration;
        this.age = age;
        this.usageGrade = usageGrade;
        this.releaseDate = releaseDate;
        this.region = region;
    }

    public static Cassettes fromJson(JsonObject jsonPayload) {
        String title = jsonPayload.get("title").getAsString();
        String duration = jsonPayload.get("duration").getAsString();
        String artists = jsonPayload.get("artists").getAsString();
        String region = jsonPayload.get("region").getAsString();
        String age = jsonPayload.get("age").getAsString();
        String album = jsonPayload.get("album").getAsString();
        String usageGrade = jsonPayload.get("usageGrade").getAsString();
        String genre = jsonPayload.get("genre").getAsString();
        String releaseDate = jsonPayload.get("releaseDate").getAsString();
        String externalID = jsonPayload.get("mbid").getAsString();

        return new Cassettes(externalID, title, album, artists, genre, duration, age,
                usageGrade, releaseDate, region);
    }

    public static Cassettes processResults(ResultSet resultSet)
    {
        try {
            if (resultSet == null || !resultSet.next()) {
                return null;
            } else {
                long idCassette = resultSet.getLong(1);
                String externalID = resultSet.getString(2);
                String title = resultSet.getString(3);
                String album = resultSet.getString(4);
                String artists = resultSet.getString(5);
                String genre = resultSet.getString(6);
                String duration = resultSet.getString(7);
                String age = resultSet.getString(8);
                String usageGrade = resultSet.getString(9);
                String releaseDate = resultSet.getString(10);
                String region = resultSet.getString(11);

                return new Cassettes(idCassette, externalID,title,album,artists,genre,
                        duration,age,usageGrade,releaseDate,region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Cassettes getCassetteByID(long musicID) {

        try {
            ResultSet resultSet = Database.selectQuery(
                    "SELECT * FROM CASSETTES WHERE ID_CASSETTES = ?", musicID);

            return Cassettes.processResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    return null;
    }

    public long getIdCassettes() {
        return idCassettes;
    }

    public void setIdCassettes(long idCassettes) {
        this.idCassettes = idCassettes;
    }


    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getUsageGrade() {
        return usageGrade;
    }

    public void setUsageGrade(String usageGrade) {
        this.usageGrade = usageGrade;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void insert() {

        if (checkIfDuplicate(externalId)) {
            return;
        }


        Connection connection = null;
        try {
            connection = Database.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO cassettes (EXTERNAL_ID, TITLE, ARTISTS" +
                            ", AGE, ALBUM, DURATION, GENRE, RELEASE_DATE, REGION, USAGE_GRADE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

            preparedStatement.setString(1, this.externalId);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.artists);
            preparedStatement.setString(4, this.age);
            preparedStatement.setString(5, this.album);
            preparedStatement.setString(6, this.duration);
            preparedStatement.setString(7, this.genre);
            preparedStatement.setString(8, this.releaseDate);
            preparedStatement.setString(9, this.region);
            preparedStatement.setString(10, this.usageGrade);

            preparedStatement.executeUpdate();

            ResultSet r = connection.prepareStatement("SELECT LAST_INSERT_ID() from cassettes").executeQuery();

            if (r.next())
                this.idCassettes = r.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfDuplicate(String externalId) {

        try {
            ResultSet rs = Database.selectQuery(Database.getConnection(),
                    "SELECT ID_CASSETTES FROM CASSETTES WHERE EXTERNAL_ID LIKE ?", externalId);

            if (!rs.next()) {
                return false;
            }

            this.idCassettes = rs.getLong(1);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public JsonObject toJson() {
        JsonObject vinylJson = new JsonObject();

        vinylJson.addProperty("id", this.idCassettes);
        vinylJson.addProperty("title", Objects.requireNonNullElse(this.title, ""));
        vinylJson.addProperty("artists", Objects.requireNonNullElse(this.artists, ""));
        vinylJson.addProperty("region", Objects.requireNonNullElse(this.region, ""));
        vinylJson.addProperty("age", Objects.requireNonNullElse(this.age, ""));
        vinylJson.addProperty("album", Objects.requireNonNullElse(this.album, ""));
        vinylJson.addProperty("duration", Objects.requireNonNullElse(this.duration, ""));
        vinylJson.addProperty("genre", Objects.requireNonNullElse(this.genre, ""));
        vinylJson.addProperty("age", Objects.requireNonNullElse(this.age, ""));
        vinylJson.addProperty("usageGrade", Objects.requireNonNullElse(this.usageGrade, ""));
        vinylJson.addProperty("releaseDate", Objects.requireNonNullElse(this.releaseDate, ""));

        return vinylJson;

    }
}