/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author Asus-FPT
 */
public class Connect {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=FPTBooking;trustServerCertificate=true";
        String username = "sa";
        String password = "123456";
        // Set the trustCertificate property to true
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Execute a SQL query (replace with your query)
            String sqlQuery = "SELECT * FROM Users";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the query results
            while (resultSet.next()) {
                // Access columns by name or index
                int id = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                // Print or process the retrieved data as needed
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
