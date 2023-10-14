/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;

public class RoomDAO extends Connect{
    public List<Room> GetRooms() throws ClassNotFoundException {
        List<Room> rooms = new ArrayList<Room>();
        try {
            String sqlQuery = "SELECT * FROM Rooms";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                rooms.add(new Room(resultSet.getInt("RoomID"),resultSet.getString("RoomNumber"),resultSet.getDouble("Price")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
