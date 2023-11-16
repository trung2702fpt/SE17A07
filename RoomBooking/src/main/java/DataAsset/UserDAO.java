package DataAsset;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.User;

public class UserDAO extends BaseDataAsset<User> {

    public int getLastId(){
        String sql = """
                     select top 1 UserID 
                     from Users
                     order by UserID desc
                     """;
        try {
            PreparedStatement stm = getConnection().prepareCall(sql);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("UserID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public User isExisted(User user) {
        User newUser = null;
        String sql = """
                     select * 
                     from Users
                     where Email = ?
                     """;
        try {
            PreparedStatement stm = getConnection().prepareCall(sql);
            stm.setString(1, user.getEmail());
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                newUser = new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getInt("RoleID"),
                        resultSet.getString("IDStudent"),
                        resultSet.getString("Image")
                );
                return newUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCountUser() throws ClassNotFoundException{
        int count = 0;
        try {
            String sql = """
                     Select COUNT(UserID) as countUser
                     FROM Users
                     """;
            PreparedStatement stm = getConnection().prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                count = rs.getInt("countUser");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public User InsertUser(User user) throws ClassNotFoundException {
        User userCheck = null;
        try {
            try ( CallableStatement cs = getConnection().prepareCall("{call dbo.InsertUserIfNotExists(?, ?, ?, ?, ?)}")) {
                cs.setString(1, user.getName());
                cs.setString(2, user.getEmail());
                cs.setInt(3, user.getRoleid());
                cs.setString(4, user.getIdStudent());
                cs.setString(5, user.getImage());

                boolean hasResults = cs.execute();

                if (hasResults) {
                    try ( ResultSet resultSet = cs.getResultSet()) {
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
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCheck;
    }

    @Override
    public List<User> getList() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(User data) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([Name]
                                ,[Email]
                                ,[RoleID]
                                ,[IDStudent]
                                ,[Image])
                          VALUES
                                (?,?,?,?,?)
                     """;
        try {
            PreparedStatement stm = getConnection().prepareCall(sql);
            stm.setString(1, data.getName());
            stm.setString(2, data.getEmail());
            stm.setInt(3, data.getRoleid());
            stm.setString(4, data.getIdStudent());
            stm.setString(5, data.getImage());
            stm.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        User userCheck = null;
        try {
            conn = Utils.Connect.getConnection();
            String sql = "SELECT 1 FROM [dbo].[Users] WHERE [UserID] = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            resultSet = stmt.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
            return userCheck;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            return userCheck;
        }

    }

    @Override
    public boolean update(int id, User newData) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
