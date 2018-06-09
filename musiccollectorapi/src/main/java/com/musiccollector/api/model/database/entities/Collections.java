package com.musiccollector.api.model.database.entities;


public class Collections {

  private long idCollection;
  private long idMusic;
  private long idUser;
  private String description;
  private String isVinyl;


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
