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

    public List<History> GetBookings() throws ClassNotFoundException, SQLException {
        List<History> historys = new ArrayList<History>();
        try {
            String sqlQuery = "SELECT R.RoomID, B.BookingDate, B.SlotID, R.Price\n"
                    + "FROM Rooms R\n"
                    + "INNER JOIN Bookings B ON R.RoomID = B.RoomID;";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                History booking = new History(
                        resultSet.getInt("RoomID"),
                        resultSet.getDate("BookingDate"),
                        resultSet.getInt("SlotID"),
                        resultSet.getFloat("Price")
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
