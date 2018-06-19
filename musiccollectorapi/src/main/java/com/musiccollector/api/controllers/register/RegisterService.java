package com.musiccollector.api.controllers.register;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.musiccollector.api.model.Database;
import com.musiccollector.api.model.database.user.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterService {

    private int checkValidCredentials(String username, String email, String password) {
        if (checkUsernameInUse(username)) {
            return 1;
        }
        if (checkEmailInUse(email)) {
            return 2;
        }
        if (password.length() < 5) {
            return 3;
        }
        if (username.length() < 5) {
            return 4;
        }

        return 0;
    }

    private boolean checkUsernameInUse(String username) {
        try {
            ResultSet resultSet = Database.selectQuery(Database.getConnection(),
                    "SELECT ID_USER FROM jbcdb.users WHERE USERNAME LIKE ?", username);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkEmailInUse(String email) {
        try {
            ResultSet resultSet = Database.selectQuery(Database.getConnection(),
                    "SELECT ID_USER FROM jbcdb.users WHERE EMAIL LIKE ?", email);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean insertUser(String username, String email, String password, int isPerson) {
        Users users = new Users(email, isPerson, password, username);
        try {
            users.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public JsonObject getResponseJson(String username, String email, String password, int isPerson) {
        boolean isError = true;
        String text = "You successfully registered!";

        switch (checkValidCredentials(username, email, password)) {
            case 0:
                if (insertUser(username, email, password, isPerson)) {
                    isError = false;
                } else {
                    text = "Internal database error, please try again later";
                }
                break;
            case 1:
                text = "Username already in use";
                break;
            case 2:
                text = "Email already in use";
                break;
            case 3:
                text = "Password too short! Must be at least 4 characters";
                break;
            case 4:
                text = "Username too short! Must be at least 4 characters";
                break;
            default:
                text = "Internal server error";
                break;
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isError", isError);
        jsonObject.addProperty("text", text);

        return jsonObject;

    }
}
