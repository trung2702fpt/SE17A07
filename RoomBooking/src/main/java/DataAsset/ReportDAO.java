package DataAsset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;

public class ReportDAO extends Utils.Connect {
    
    public static boolean isUserExists(int userId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Utils.Connect.getConnection();
            String sql = "SELECT 1 FROM [dbo].[Users] WHERE [UserID] = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            rs = stmt.executeQuery();

            return rs.next(); // Nếu có kết quả trả về từ truy vấn, tức là User tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertReport(Report report) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Random random = new Random();
        try {
            // Kiểm tra sự tồn tại của UserID trước khi thêm báo cáo
            if (!isUserExists(report.getUserID())) {
                System.out.println("User không tồn tại trong cơ sở dữ liệu.");
                return false;
            }
             conn = Utils.Connect.getConnection();
            String sql = "INSERT INTO [FPTBooking].[dbo].[Report] ([ReportID], [UserID], [Time], [Title], [Content], [Status], [Reply]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            // Tạo một số nguyên ngẫu nhiên cho ReportID
            int randomReportID = random.nextInt(1000000); // Số nguyên ngẫu nhiên trong khoảng 0-999999
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, randomReportID );
            stmt.setInt(2, report.getUserID());
            stmt.setString(3, report.getTime());
            stmt.setString(4, report.getTitle());
            stmt.setString(5, report.getContent());
            stmt.setBoolean(6, report.getStatus()); // Sử dụng setBoolean() cho kiểu boolean
            stmt.setString(7, report.getReply());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu đã thêm thành công, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String time = LocalDate.now().toString();
        Report report = new Report(1, 2, time, "Title", "Content2", true, "Reply"); // Sử dụng giá trị boolean thay vì chuỗi "Status"
        try {
            System.out.println(insertReport(report));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
