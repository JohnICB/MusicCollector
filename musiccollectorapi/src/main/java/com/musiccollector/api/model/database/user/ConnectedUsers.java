package com.musiccollector.api.model.database.user;


import com.musiccollector.api.generators.TokenGenerator;
import com.musiccollector.api.model.Database;

import java.sql.SQLException;

public class ConnectedUsers {

  private long id;
  private String username;
  private String token;
  private java.sql.Timestamp creationTime;

  public ConnectedUsers(String username)
  {
      this.username = username;
      try {
          this.token = TokenGenerator.getToken(64);
      } catch (SQLException e) {
          e.printStackTrace();
      }

  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public java.sql.Timestamp getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(java.sql.Timestamp creationTime) {
    this.creationTime = creationTime;
  }

    public void insert() {

        try {

            Database.updateOperation(
                    "DELETE FROM CONNECTED_USERS WHERE USERNAME LIKE ?", this.username
            );

            Database.updateOperation(
                    "INSERT INTO CONNECTED_USERS (username, token) VALUES(?, ?)",
                    this.username, this.token
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
