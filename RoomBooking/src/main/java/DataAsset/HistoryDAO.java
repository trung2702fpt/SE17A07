package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.History;

public class HistoryDAO extends BaseDataAsset<History> {

    public List<History> GetBookings(int id) throws ClassNotFoundException, SQLException {
        List<History> historys = new ArrayList<>();
        try {
            String sqlQuery = """
                              SELECT
                                  R.RoomID,
                                  H.BookingDate,
                                  B.SlotID,
                                  H.CancelationDate,
                                  CASE
                                      WHEN GETDATE() > H.BookingDate THEN 1
                                      ELSE 0
                                  END AS isUsed
                              FROM
                                  Rooms R
                                  INNER JOIN Bookings B ON R.RoomID = B.RoomID
                                  INNER JOIN BookingHistoryAction H ON R.RoomID = H.RoomID
                              WHERE
                                  H.UserID = ?""";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    History booking = new History(
                            resultSet.getInt("RoomID"),
                            resultSet.getTimestamp("BookingDate"),
                            resultSet.getInt("SlotID"),
                            resultSet.getTimestamp("CancelationDate")
                    );
                    
                    booking.setIsUsed(resultSet.getInt("isUsed") == 1);
                    historys.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historys;
    }

    @Override
    public List<History> getList() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(History data) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public History read(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(int id, History newData) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
