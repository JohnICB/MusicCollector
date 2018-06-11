package com.musiccollector.api.model.database.entities;


import com.musiccollector.api.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Cassettes(String externalId, String title, String album, String artists, String genre, String duration, String age, String usageGrade, String releaseDate) {
        this.externalId = externalId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.genre = genre;
        this.duration = duration;
        this.age = age;
        this.usageGrade = usageGrade;
        this.releaseDate = releaseDate;
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
        Connection connection = null;
        try {
            connection = Database.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO cassettes (EXTERNAL_ID, TITLE, ARTISTS" +
                            ", AGE, ALBUM, DURATION, GENRE, RELEASE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, this.externalId);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.artists);
            preparedStatement.setString(4, this.age);
            preparedStatement.setString(5, this.album);
            preparedStatement.setString(6, this.duration);
            preparedStatement.setString(7, this.genre);
            preparedStatement.setString(8, this.releaseDate);

            preparedStatement.executeUpdate();

            ResultSet r = connection.prepareStatement("SELECT LAST_INSERT_ID() from cassettes").executeQuery();

            if (r.next())
                this.idCassettes = r.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}