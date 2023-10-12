/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thong
 */
public class Connect {
    private static Connection connection;
    private static String jdbcUrl ;
    private static String username = "sa";
    private static String password = "123456";
    private static String databaseName = "FPTBooking";
    private static String localHost = "1433";
    
    protected static Connection getConnection(){
        jdbcUrl = "jdbc:sqlserver://localhost:"+localHost+";databaseName="+databaseName+";trustServerCertificate=true";
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public static Statement getCreateStatement() throws SQLException{
        return connection.createStatement();
    }
}
