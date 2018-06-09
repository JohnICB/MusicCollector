package com.musiccollector.api.model.database.entities;


public class Concerts {

  private long idConcert;
  private String title;
  private String description;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String url;


  public long getIdConcert() {
    return idConcert;
  }

  public void setIdConcert(long idConcert) {
    this.idConcert = idConcert;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
