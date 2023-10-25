package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdmin extends Connect {

    public static boolean Login(String email, String id) throws ClassNotFoundException {
        boolean canLogin = false;
        try {
            String sqlQuery = "SELECT * FROM Users WHERE Email = ? AND IDStudent = ?";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setString(1, email);
            st.setString(2, id);

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                canLogin = true;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            canLogin = false;
        }
        return canLogin;
    }
}
