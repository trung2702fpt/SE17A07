package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.History;

public class HistoryDAO extends Connect {

    public List<History> GetBookings(int id) throws ClassNotFoundException, SQLException {
         List<History> historys = new ArrayList<History>();
        try {
            String sqlQuery = "SELECT R.RoomID, H.BookingDate, B.SlotID, H.CancelationDate   \n"
                    + "FROM Rooms R\n"
                    + "INNER JOIN Bookings B ON R.RoomID = B.RoomID "
                    + "INNER JOIN BookingHistoryAction H ON R.RoomID = H.RoomID;"
                    + "WHERE H.UserID = ?";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                History booking = new History(
                        resultSet.getInt("RoomID"),
                        resultSet.getDate("BookingDate"),
                        resultSet.getInt("SlotID"),
                        resultSet.getFloat("Price"),
                        resultSet.getBoolean("UserID"),
                        resultSet.getDate("CancelationDate")
                );
                historys.add(booking);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historys;
    }
}
