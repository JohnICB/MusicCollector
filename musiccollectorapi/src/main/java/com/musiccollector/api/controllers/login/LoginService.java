package com.musiccollector.api.controllers.login;

import com.musiccollector.api.model.Database;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    public boolean validateLoginCredentials(String user, String password) {

        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID_USER FROM jbcdb.USERS WHERE USERNAME LIKE ? AND PASSWORD LIKE ?");

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                System.out.println("logging as " + user);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
        @return
        1 = e logat
        0 = nu e logat
        2 = erroare
     */
    public static boolean isUserLoggedIn(Cookie cookies[])
    {
        Connection connection;
        try {
             connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("CANT GET CONNECTION IN LOGIN SERVICE");
            return false;
        }

        ResultSet resultSet = null;
        String loginToken ="";
        String username = "";


        for (Cookie c : cookies)
        {
            if (c.getName().equals("loginToken"))
            {
                loginToken = c.getValue();
            }
            if (c.getName().equals("user"))
            {
                username = c.getValue();
            }

            if (!username.equals("") && !loginToken.equals(""))
            {
                System.out.println("found: " + username + " " + loginToken);
                break;
            }
        }

        try {
             resultSet = Database.selectQuery(connection,
                    "SELECT ID FROM CONNECTED_USERS WHERE TOKEN LIKE ? AND USERNAME LIKE ?",
                    loginToken, username);

                     if (resultSet.next()) return true;

//            System.out.println('o');
//
//            boolean b = false;
//            while (resultSet.next())
//            {
//                System.out.println("ola");
//                System.out.println(resultSet.getBigDecimal(1));
//                b = true;
//            }
//            return b;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean validateUsername(String username)
    {
        try {
            Connection connection = Database.getConnection();
            ResultSet resultSet = Database.selectQuery(connection,"SELECT ID_USER FROM jbcdb.USERS WHERE USERNAME LIKE ?", username);

            if (resultSet.next())
            {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}