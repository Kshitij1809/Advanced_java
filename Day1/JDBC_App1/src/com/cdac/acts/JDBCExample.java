package com.cdac.acts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/day2"; // Change this based on your database
        String user = "root"; // Use your MySQL username
        String password = "cdac"; // Use your MySQL password


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver

            // Establish a connection
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            statement = connection.createStatement();

            // Execute a query
            resultSet = statement.executeQuery("SELECT * FROM emp");

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("empno");
                String name = resultSet.getString("ename");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

