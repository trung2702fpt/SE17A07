/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

    private static String username = "sa";
    private static String password = "123456";
    private static String databaseName = "FPTBooking";
    private static String serverName = "localhost";
    private static int port = 1433;
    private static String jdbcUrl = "jdbc:sqlserver://" + serverName + ":" + port + ";databaseName=" + databaseName + ";trustServerCertificate=true";
    private static Connection connection;

    protected static Connection getConnection() throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getCreateStatement() throws SQLException {
        return connection.createStatement();
    }
}
