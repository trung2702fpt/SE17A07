package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;

public class RoomDAO extends BaseDataAsset<Room> {

    public List<Room> rooms;

    public RoomDAO() {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
    }

    public int getIdByDate(String date, int slot, int iduser, int roomid) {
        String searchSql = """
                           SELECT BookingID
                           FROM Bookings 
                           WHERE BookingDate = ?
                           AND SlotID = ?
                           AND UserID = ?
                           AND RoomID = ? ;""";
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setString(1, date);
            st.setInt(2, slot);
            st.setInt(3, iduser);
            st.setInt(4, roomid);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
             return resultSet.getInt("BookingID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Room> searchByDateAndSlOT(int slot, String date) {
        List<Room> listRooms = new ArrayList<>();
        String searchSql = """
                           SELECT r.RoomId, r.RoomNumber, r.Price
                           FROM rooms r
                           WHERE r.RoomId NOT IN (
                               SELECT b.RoomId
                               FROM Bookings b
                               WHERE b.BookingDate = ? AND b.SlotID = ? AND IsCancel = 0 
                           );""";
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setString(1, date);
            st.setInt(2, slot);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                listRooms.add(new Room(resultSet.getInt("RoomID"),
                         resultSet.getString("RoomNumber"),
                         resultSet.getDouble("Price")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRooms;
    }

    private List<Room> Rooms() throws ClassNotFoundException {
        rooms.clear();
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

    @Override
    public List<Room> getList() throws ClassNotFoundException {
        if (rooms.size() <= 0) {
            Rooms();
        }
        return rooms;
    }

    @Override
    public void create(Room data) {
        String sql = "INSERT INTO Rooms (RoomID, RoomNumber, Price) values (?, ?, ?)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, data.id);
            st.setString(2, data.roomNumber);
            st.setDouble(3, data.price);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet.getInt("RoomID"),
                         resultSet.getString("RoomNumber"),
                         resultSet.getDouble("Price"));
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
            st.setDouble(1, newData.getPrice());
            st.setInt(2, newData.id);
            boolean result = st.executeUpdate() > 1;
            if (result) {
                Rooms();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) {
       String sql = "DELETE FROM Rooms WHERE RoomID = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
