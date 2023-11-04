package DataAsset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import model.Report;

public class ReportDAO extends BaseDataAsset<Report> {

    public static boolean insertReport(Report report) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Random random = new Random();
        UserDAO userdao = new UserDAO();
        try {
            if (userdao.read(report.getUserID()) == null) {
                System.out.println("User không tồn tại trong cơ sở dữ liệu.");
                return false;
            }
            conn = Utils.Connect.getConnection();
            String sql = "INSERT INTO [FPTBooking].[dbo].[Report] ([ReportID], [UserID], [Time], [Title], [Content], [Status], [Reply]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            int randomReportID = random.nextInt(1000000); // Số nguyên ngẫu nhiên trong khoảng 0-999999
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, randomReportID);
            stmt.setInt(2, report.getUserID());
            stmt.setString(3, report.getTime());
            stmt.setString(4, report.getTitle());
            stmt.setString(5, report.getContent());
            stmt.setBoolean(6, report.getStatus());
            stmt.setString(7, report.getReply());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
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

    @Override
    public List<Report> getList() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Report data) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Report read(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(int id, Report newData) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
