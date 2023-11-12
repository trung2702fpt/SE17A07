package DataAsset;

import Utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String sql = "INSERT INTO [dbo].[Bookings] VALUES(?,?,?,?)";
            try ( PreparedStatement stmt = getConnection().prepareStatement(sql)) {
                stmt.setInt(1, booking.getIdRoom());
                stmt.setInt(2, booking.getUserId());
                stmt.setString(3, booking.getTime());
                stmt.setInt(4, booking.getSlotId());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
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
    
    public boolean cancelBooking(int id) {
        try {
            String sql = """
                         delete EquipmentBookings where BookingID = ?
                         
                         delete Payments where BookingID = ? 
                         
                         delete Bookings  where BookingID = ?                         
                         """;
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            stmt.setInt(3, id);

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
    
    public boolean updateAction(String dateCancel,String DateBoooking, int idUser ) {
        try {
            String sql = """
                         UPDATE [dbo].[BookingHistoryAction]
                              SET [CancelationDate] = ?
                            WHERE BookingDate = ? AND UserID = ?""";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            
            stmt.setString(1, dateCancel);
            stmt.setString(2, DateBoooking);
            stmt.setInt(3, idUser);

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
}
