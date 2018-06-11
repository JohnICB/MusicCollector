package com.musiccollector.api.model;

import java.sql.*;


public class Database {

    private static Connection connection = null;

    private Database()
    {
    }

    public static Connection getConnection() throws SQLException
    {
        if (connection == null)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbcdb?autoReconnect=true&useSSL=false","root","root");
        }

        return connection;
    }

    public static ResultSet selectQuery(Connection connection, String query, String... parameters) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i <= parameters.length; ++i) {
            preparedStatement.setString(i, parameters[i - 1]);
        }

        return preparedStatement.executeQuery();
    }

    public static ResultSet selectQuery(String query, long ... longParameters) throws SQLException
    {
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
        for (int i = 1; i <= longParameters.length; ++i) {
            preparedStatement.setLong(i, longParameters[i - 1]);
        }

        return preparedStatement.executeQuery();
    }

    public static int updateOperation(String query, String ... parameters) throws SQLException {

        if (connection == null) connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i <= parameters.length; ++i) {
            preparedStatement.setString(i, parameters[i - 1]);
        }

//                connection.close();

        return preparedStatement.executeUpdate();
    }
}
