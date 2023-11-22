package DataAsset;

import Utils.Connect;
import Utils.StringExtention;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Equipment;
import model.requestBooking;

public class BookingDAO extends Connect {

    public BookingDAO() {
    }

    public int getLastID() {
        try {
            String sql = "SELECT TOP 1 BookingID FROM Bookings ORDER BY BookingID DESC";
            try ( PreparedStatement selectStmt = getConnection().prepareStatement(sql);  ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("BookingID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean insertRoomBooking(requestBooking booking) {
        try {
            String sql = "INSERT INTO [dbo].[Bookings] VALUES(?,?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, booking.getIdRoom());
            stmt.setInt(2, booking.getUserId());
            stmt.setString(3, booking.getTime());
            stmt.setInt(4, booking.getSlotId());
            stmt.setInt(5, 0);
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return false;
    }

    public boolean inssertEquipmentBooking(Equipment equipment) {
        try {
            String sql = "INSERT INTO [dbo].[EquipmentBookings] VALUES (?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, getLastID());
            stmt.setInt(2, equipment.getId());
            stmt.setInt(3, 1);
            stmt.setDouble(4, equipment.getPrice());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return false;
    }

    public boolean inssertPayment(requestBooking booking) {
        try {
            String sql = "INSERT INTO [dbo].[Payments] VALUES (?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            stmt.setInt(1, getLastID());

            stmt.setString(2, booking.getTimePayment());
            stmt.setDouble(3, booking.getAmount());
            stmt.setInt(4, booking.getUserId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAction(String dateCancel, String DateBoooking, int idUser, int idBooking) {
        try {
            String sql = """
                         UPDATE [dbo].[BookingHistoryAction]
                         SET [CancelationDate] = ?
                         WHERE ID = ? AND UserID = ?
                         
                         UPDATE Bookings
                         SET IsCancel = 1
                         WHERE UserID = ? AND BookingID =?
                         """;
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            stmt.setString(1, dateCancel);
            stmt.setInt(2, idBooking);
            stmt.setInt(3, idUser);
            
            stmt.setInt(4, idUser);
            stmt.setInt(5, idBooking);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getId(String date, int slot, int iduser, int roomid) {
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
    
    public Booking getDetailBooking(int idBooking){
        Booking booking = null;
        EquipmentDAO edao = new EquipmentDAO();
        String searchSql = """
                           SELECT *, 
                               CASE
                                   WHEN P.PaymentDate IS NOT NULL THEN 1 
                                   ELSE 0
                               END AS IsPaied,
                                CASE
                                    WHEN B.BookingDate < GETDATE() THEN 1
                                    ELSE 0
                           	END AS IsOutTime
                           FROM Bookings B
                           JOIN Payments P ON P.BookingID = B.BookingID
                           JOIN Rooms R ON R.RoomID = B.RoomID
                           WHERE B.BookingID = ? ;
                           """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setInt(1, idBooking);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
                String dateBooking = StringExtention.ConverDateToString(resultSet.getTimestamp("BookingDate"));
                String datePaied = StringExtention.ConverDateToString(resultSet.getTimestamp("PaymentDate"));
                booking = new Booking(idBooking, 
                        resultSet.getInt("RoomID"),
                        resultSet.getInt("SlotID"), 
                        resultSet.getInt("UserID"), 
                        dateBooking, 
                        resultSet.getBoolean("IsCancel"), 
                        resultSet.getBoolean("IsPaied"), 
                         datePaied, 
                        resultSet.getDouble("Amount"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("RoomNumber"));
                booking.setIsOutTime(resultSet.getBoolean("IsOutTime"));
                booking.setEquipments(edao.getListEquipmentByBookingId(idBooking));
                return booking;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
}
