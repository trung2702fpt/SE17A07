package DataAsset;

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
                        SELECT DISTINCT RoomID, BookingDate, CancelationDate, SlotID,
                            CASE
                            	WHEN GETDATE() > BookingDate THEN 1
                            ELSE 0
                            END AS isUsed,
                            CASE
                            	WHEN CancelationDate IS NOT NULL THEN 1
                            ELSE 0
                            END AS isCanceled
                            
                            FROM BookingHistoryAction
                            WHERE UserID = ?
                            ORDER BY BookingDate DESC""";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, id);
            try ( ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    History booking = new History(
                            resultSet.getInt("RoomID"),
                            resultSet.getTimestamp("BookingDate"),
                            resultSet.getTimestamp("CancelationDate")
                    );
                    booking.setIsCancel(resultSet.getInt("isCanceled") == 1);
                    booking.setIsUsed(resultSet.getInt("isUsed") == 1);
                    booking.setSlotID(resultSet.getInt("SlotID"));
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
