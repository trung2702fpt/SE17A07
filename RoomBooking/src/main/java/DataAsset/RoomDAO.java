package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;

public class RoomDAO extends BaseDataAsset<Room> {

    public static List<Room> rooms;
    public RoomDAO() {
        if(rooms == null){
            rooms = new ArrayList<>();
        }
    }

    public List<Room> searchByDateAndSlOT(int slot, String date) {
        List<Room> listRooms = new ArrayList<>();
        String searchSql = """
                           SELECT r.RoomId, r.RoomNumber, r.Price
                           FROM rooms r
                           WHERE r.RoomId NOT IN (
                               SELECT b.RoomId
                               FROM Bookings b
                               WHERE b.BookingDate = ? AND b.SlotID = ? 
                           );""";
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

    @Override
    public List<Room> getList() throws ClassNotFoundException {
        if(rooms.size() <= 0 ){
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
        }
        return rooms;
    }

    @Override
    public void create(Room data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Room read(int id) {
       Room room = null;
        String searchSql = """
                           SELECT *
                           FROM rooms
                           WHERE RoomId = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setInt(1,id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
               room = new Room(resultSet.getInt("RoomID")
                       , resultSet.getString("RoomNumber")
                       , resultSet.getDouble("Price"));
               return room;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public boolean update(int id, Room newData) {
        String searchSql = """
                           UPDATE Rooms
                           SET Price = ?
                           WHERE RoomID = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setDouble(1,newData.getPrice());
            st.setInt(2,newData.id);
            boolean result = st.executeUpdate() > 1;
            if(result){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
