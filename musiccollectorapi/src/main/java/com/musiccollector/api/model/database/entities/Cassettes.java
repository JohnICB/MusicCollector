package com.musiccollector.api.model.database.entities;


public class Cassettes {

  private long idCassettes;
  private String externalId;
  private String title;
  private String album;
  private String artists;
  private String genre;
  private java.sql.Time duration;
  private String age;
  private String usageGrade;
  private String releaseDate;



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


  public java.sql.Time getDuration() {
    return duration;
  }

  public void setDuration(java.sql.Time duration) {
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

}
