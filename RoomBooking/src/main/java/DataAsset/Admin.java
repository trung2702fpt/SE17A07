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

            try ( ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    canLogin = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            canLogin = false;
        }
        return canLogin;
    }

    public static List<StaticBooking> GetNumberBookingAndCancel(int year) throws ClassNotFoundException {
        List<StaticBooking> list = new ArrayList<>();
        try {
            String sqlQuery = """
                              DECLARE @month INT
                              DECLARE @result TABLE (month INT, rooms_booked INT, rooms_cancelled INT, cancel_percentage DECIMAL(5, 2))
                              
                              SET @month = 1
                              WHILE @month <= 12
                              BEGIN
                                  INSERT INTO @result (month, rooms_booked, rooms_cancelled, cancel_percentage)
                                  SELECT
                                      @month,
                                      (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month),
                                      (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(CancelationDate) = ? AND MONTH(CancelationDate) = @month),
                                      CASE
                                          WHEN (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month) > 0
                                          THEN
                                              (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(CancelationDate) = ? AND MONTH(CancelationDate) = @month) * 100.0 /
                                              (SELECT COUNT(*) FROM BookingHistoryAction WHERE YEAR(BookingDate) = ? AND MONTH(BookingDate) = @month)
                                          ELSE
                                              0
                                      END
                              
                                  SET @month = @month + 1
                              END
                              
                              SELECT * FROM @result""";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            st.setInt(1, year);
            st.setInt(2, year);
            st.setInt(3, year);
            st.setInt(4, year);
            st.setInt(5, year);
            try ( ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new StaticBooking(resultSet.getInt("rooms_booked"), resultSet.getInt("rooms_cancelled")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getBookedDailyCount(String date) {
        int bookedDaily = 0;
        String sql = "SELECT COUNT(ID) AS BookedDaily\n"
                + "FROM BookingHistoryAction\n"
                + "WHERE BookingDate > ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, date);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                bookedDaily = resultSet.getInt("BookedDaily");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookedDaily;
    }

    public int getBookedYearCount() {
        int bookedYear = 0;
        String sql = "SELECT COUNT(ID) AS BookedYear\n"
                + "	FROM BookingHistoryAction";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                bookedYear = resultSet.getInt("BookedYear");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookedYear;
    }

    public int getpPersenCancel() {
        int booked = 0;
        int cancel = 0;
        int result = 0;
        String sql = "SELECT COUNT(ID) AS Booked,\n"
                + "	(SELECT COUNT(ID)\n"
                + "	FROM BookingHistoryAction\n"
                + "	WHERE CancelationDate is NOT NULL\n"
                + "	) as cancel\n"
                + "	FROM BookingHistoryAction";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                booked = resultSet.getInt("Booked");
                cancel = resultSet.getInt("cancel");
                result = (cancel / booked) * 100;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return booked;
    }

    public int getFeedbackInDay(String date) {
        int count = 0;
        String sql = "SELECT COUNT(ReportID) AS countReport\n"
                + "	FROM Report\n"
                + "	WHERE Time > ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, date);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("countReport");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public int getFeedbackInYear() {
        int count = 0;
        String sql = "SELECT COUNT(ReportID) AS countReport\n"
                + "	FROM Report\n";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("countReport");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public int getBookedUsed(String date, String currentTime) {
        int bookedUsedInDay = 0;
        String sql = "SELECT COUNT(ID) AS bookedUsedInDay\n"
                + "FROM BookingHistoryAction\n"
                + "WHERE BookingDate > ? AND BookingDate < ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, date);
            st.setString(2, currentTime);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                bookedUsedInDay = resultSet.getInt("bookedUsedInDay");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookedUsedInDay;
    }
}
