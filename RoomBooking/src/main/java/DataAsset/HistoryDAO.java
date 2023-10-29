package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.History;

public class HistoryDAO extends Connect {

    public List<History> GetBookings(int id) throws ClassNotFoundException, SQLException {
        List<History> historys = new ArrayList<History>();
        try {
            String sqlQuery = "SELECT\n"
                    + "    R.RoomID,\n"
                    + "    H.BookingDate,\n"
                    + "    B.SlotID,\n"
                    + "    H.CancelationDate,\n"
                    + "    CASE\n"
                    + "        WHEN GETDATE() > H.BookingDate THEN 1\n"
                    + "        ELSE 0\n"
                    + "    END AS isUsed\n"
                    + "FROM\n"
                    + "    Rooms R\n"
                    + "    INNER JOIN Bookings B ON R.RoomID = B.RoomID\n"
                    + "    INNER JOIN BookingHistoryAction H ON R.RoomID = H.RoomID\n"
                    + "WHERE\n"
                    + "    H.UserID = ?";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                History booking = new History(
                        resultSet.getInt("RoomID"),
                        resultSet.getTimestamp("BookingDate"),
                        resultSet.getInt("SlotID")
                );
                Timestamp timeCancel = resultSet.getTimestamp("CancelationDate");
                
                booking.setIsUsed(resultSet.getInt("isUsed") == 1);
                historys.add(booking);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historys;
    }
}
