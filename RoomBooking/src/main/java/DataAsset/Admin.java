package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.StaticBooking;

public class Admin extends Connect {

    public static boolean Login(String email, String id) throws ClassNotFoundException {
        boolean canLogin = false;
        try {
            String sqlQuery = "SELECT * FROM Users WHERE Email = ? AND IDStudent = ?";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setString(1, email);
            st.setString(2, id);

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                canLogin = true;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            canLogin = false;
        }
        return canLogin;
    }

    public static List<StaticBooking> GetNumberBookingAndCancel(int year) throws ClassNotFoundException {
        List<StaticBooking> list = new ArrayList<>();
        try {
            String sqlQuery = "DECLARE @month INT\n"
                    + "DECLARE @result TABLE (month INT, rooms_booked INT, rooms_cancelled INT, cancel_percentage DECIMAL(5, 2))\n"
                    + "\n"
                    + "SET @month = 1\n"
                    + "WHILE @month <= 12\n"
                    + "BEGIN\n"
                    + "    INSERT INTO @result (month, rooms_booked, rooms_cancelled, cancel_percentage)\n"
                    + "    SELECT\n"
                    + "        @month,\n"
                    + "        (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month),\n"
                    + "        (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(CancelationDate) = ? AND MONTH(CancelationDate) = @month),\n"
                    + "        CASE\n"
                    + "            WHEN (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month) > 0\n"
                    + "            THEN\n"
                    + "                (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(CancelationDate) = ? AND MONTH(CancelationDate) = @month) * 100.0 /\n"
                    + "                (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month)\n"
                    + "            ELSE\n"
                    + "                0\n"
                    + "        END\n"
                    + "\n"
                    + "    SET @month = @month + 1\n"
                    + "END\n"
                    + "\n"
                    + "SELECT * FROM @result";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, year);
            st.setInt(2, year);
            st.setInt(3, year);
            st.setInt(4, year);
            st.setInt(5, year);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                list.add(new StaticBooking(resultSet.getInt("rooms_booked"), resultSet.getInt("rooms_cancelled")));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
