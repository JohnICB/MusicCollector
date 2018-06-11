package com.musiccollector.api.model.database.user;

import com.google.common.hash.Hashing;
import com.musiccollector.api.model.Database;
import com.musiccollector.api.model.database.CollectionJava;
import com.musiccollector.api.model.database.entities.Collections;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Users {

  private long idUser = -1;
  private String email;
  private String firstName;
  private String lastName;
  private int isPerson;
  private String password;
  private String username;

  private ArrayList<CollectionJava> collections;


  public Users(String email, int isPerson, String password, String username) {
    this.email = email;
    this.isPerson = isPerson;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    System.out.println(password);
    this.username = username;

    collections = new ArrayList<>();

  }

  public void insert() throws SQLException {
    Connection connection = Database.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO users (EMAIL, USERNAME, PASSWORD,IS_PERSON) VALUES (?, ?, ?, ?)");

    preparedStatement.setString(1, email);
    preparedStatement.setString(2, username);
    preparedStatement.setString(3, password);
    preparedStatement.setInt(4, isPerson);

    preparedStatement.executeUpdate();

//    connection.close();

  }

  public long getIdUser() {

    if (this.idUser == -1)
    {
      try {
        ResultSet resultSet = Database.selectQuery(Database.getConnection(),
                "SELECT ID_USER FROM USERS WHERE USERNAME LIKE ? AND EMAIL LIKE ?",
                this.username, this.email);

        if (resultSet.next())
        {
          this.idUser = resultSet.getLong(1);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return idUser;
  }

  private long getIdUserByUsername(String username)
  {
    if (this.idUser == -1)
    {
      try {
        ResultSet resultSet = Database.selectQuery(Database.getConnection(),
                "SELECT ID_USER FROM USERS WHERE USERNAME LIKE ?",
                username);

        if (resultSet.next())
        {
          this.idUser = resultSet.getLong(1);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return idUser;
  }

  public static long getIDbyName(String username)
  {
    long id = -1;
    try {
      ResultSet resultSet = Database.selectQuery(Database.getConnection(),
              "SELECT ID_USER FROM USERS WHERE USERNAME LIKE ?",
              username);

      if (resultSet.next())
      {
        id = resultSet.getLong(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return id;
  }
  private long getIdUserByEmail(String email)
  {
    if (this.idUser == -1)
    {
      try {
        ResultSet resultSet = Database.selectQuery(Database.getConnection(),
                "SELECT ID_USER FROM USERS WHERE EMAIL LIKE ?",
                email);

        if (resultSet.next())
        {
          this.idUser = resultSet.getLong(1);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return idUser;
  }

  public ArrayList<CollectionJava> getCollections()
  {
    this.collections = Collections.getCollections(this.idUser);
    return collections;
  }
}
