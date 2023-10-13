package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class UserDAO extends Utils.Connect {

    public User checkUser(User user) throws ClassNotFoundException {
        User userCheck = null;
        try {
            String sqlQuery = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setString(1, user.getEmail());
            ResultSet resultSet = st.executeQuery();

            if (resultSet.isBeforeFirst()) {
                userCheck = user;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCheck;
    }

    public boolean InsertUser(User user) throws ClassNotFoundException {
        try {
            String sqlQuery = "INSERT INTO Users VALUES(?,?,?,?,?)";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setInt(3, user.getRoleid());
            st.setString(4, user.getIdStudent());
            st.setString(5, user.getImage());
            ResultSet resultSet = st.executeQuery();
            int rowsInserted = st.executeUpdate();

            if (rowsInserted == 1) {
                resultSet.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void test() {
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
