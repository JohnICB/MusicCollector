package com.musiccollector.api.generators;

import com.musiccollector.api.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TokenGenerator {
    private static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numbers = "0123456789";

    private static String getTokenDomain() {
        return letters + numbers + letters.toLowerCase();
    }

    private static String generateToken(int size) {
        String domain = getTokenDomain();
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomIndex = Math.abs(random.nextInt()) % domain.length();
            token.append(domain.charAt(randomIndex));
        }

        return token.toString();
    }

    private static boolean isTokenAlreadyUsed(String token) throws SQLException {
        boolean isTokenUsed = false;
        Connection connection;

        connection = Database.getConnection();
        String query = "SELECT * FROM connected_users WHERE token = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            isTokenUsed = true;
        }

        //connection.close();
        return isTokenUsed;
    }

    public static String getToken(int size) throws SQLException {
        String generatedToken;
        do {
            generatedToken = generateToken(size);
        } while (isTokenAlreadyUsed(generatedToken));

        return generatedToken;
    }

}

//lion