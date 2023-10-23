package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Room;

public class RoomDAO extends Connect {

    public RoomDAO() {

    }

    public List<Room> GetRooms() throws ClassNotFoundException {
        List<Room> rooms = new ArrayList<Room>();
        try {
            String sqlQuery = "SELECT * FROM Rooms";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                rooms.add(new Room(resultSet.getInt("RoomID"), resultSet.getString("RoomNumber"), resultSet.getDouble("Price")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public List<Room> searchByDateAndSlOT(int slot, String date) {
        List<Room> listRooms = new ArrayList<>();
        String searchSql = "SELECT r.RoomId, r.RoomNumber, r.Price\n"
                + "FROM rooms r\n"
                + "WHERE r.RoomId NOT IN (\n"
                + "    SELECT b.RoomId\n"
                + "    FROM Bookings b\n"
                + "    WHERE b.BookingDate = ? AND b.SlotID = ? \n"
                + ");";
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setString(1,date);
            st.setInt(2, slot);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
               listRooms.add(new Room(resultSet.getInt("RoomID")
                       , resultSet.getString("RoomNumber")
                       , resultSet.getDouble("Price")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRooms;
    }
}
