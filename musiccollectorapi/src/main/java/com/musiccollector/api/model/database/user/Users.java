package com.musiccollector.api.model.database.user;

import com.google.common.hash.Hashing;
import com.musiccollector.api.model.Database;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Users {

  private long idUser;
  private String email;
  private String firstName;
  private String lastName;
  private int isPerson;
  private String password;
  private String username;


  public Users(String email, int isPerson, String password, String username) {
    this.email = email;
    this.isPerson = isPerson;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    System.out.println(password);
    this.username = username;
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
}
