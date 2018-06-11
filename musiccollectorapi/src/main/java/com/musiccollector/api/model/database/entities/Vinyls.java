package com.musiccollector.api.model.database.entities;

import java.sql.Date;
import java.sql.Time;

public class Vinyls {

  private long idVinyl;
  private String externalId;
  private String title;
  private String artists;
  private String region;
  private String age;
  private String album;
  private String size;
  private String isColored;
  private String isStereo;
  private String isSpecialEdition;
  private java.sql.Time duration;
  private String genre;
  private String rarity;
  private java.sql.Date releaseDate;

  public Vinyls(String externalId, String title, String album, String artists)
  {

  }

    public Vinyls(String externalId, String title, String artists, String region, String age,
                  String album, String size, String isColored, String isStereo, String isSpecialEdition,
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


    public long getIdVinyl() {
    return idVinyl;
  }

  public void setIdVinyl(long idVinyl) {
    this.idVinyl = idVinyl;
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


  public String getArtists() {
    return artists;
  }

  public void setArtists(String artists) {
    this.artists = artists;
  }


  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }


  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }


  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }


  public String getIsColored() {
    return isColored;
  }

  public void setIsColored(String isColored) {
    this.isColored = isColored;
  }


  public String getIsStereo() {
    return isStereo;
  }

  public void setIsStereo(String isStereo) {
    this.isStereo = isStereo;
  }


  public String getIsSpecialEdition() {
    return isSpecialEdition;
  }

  public void setIsSpecialEdition(String isSpecialEdition) {
    this.isSpecialEdition = isSpecialEdition;
  }


  public java.sql.Time getDuration() {
    return duration;
  }

  public void setDuration(java.sql.Time duration) {
    this.duration = duration;
  }


  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }


  public String getRarity() {
    return rarity;
  }

  public void setRarity(String rarity) {
    this.rarity = rarity;
  }


  public java.sql.Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(java.sql.Date releaseDate) {
    this.releaseDate = releaseDate;
  }

}
