package DataAsset;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class UserDAO extends Utils.Connect {

    public User InsertUser(User user) throws ClassNotFoundException {
    User userCheck = null;
    try {
        CallableStatement cs = getConnection().prepareCall("{call dbo.InsertUserIfNotExists(?, ?, ?, ?, ?)}");
        cs.setString(1, user.getName());
        cs.setString(2, user.getEmail());
        cs.setInt(3, user.getRoleid());
        cs.setString(4, user.getIdStudent());
        cs.setString(5, user.getImage());

        boolean hasResults = cs.execute();

        if (hasResults) {
            ResultSet resultSet = cs.getResultSet();
            if (resultSet.next()) {
                userCheck = new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Email"),
                    resultSet.getInt("RoleID"),
                    resultSet.getString("IDStudent"),
                    resultSet.getString("Image")
                );
            }
            resultSet.close();
        }

        cs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userCheck;
}

}
