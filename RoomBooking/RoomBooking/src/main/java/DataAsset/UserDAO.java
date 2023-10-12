

package DataAsset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAO extends Utils.Connect{
    
    //luôn luôn khởi tạo UserDao để có được connection
    public UserDAO(){
        getConnection();
    }
    
    private void test(){
        try {
            Statement statement = getCreateStatement();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
