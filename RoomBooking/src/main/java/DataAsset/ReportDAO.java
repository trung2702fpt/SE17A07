package DataAsset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;

public class ReportDAO extends BaseDataAsset<Report> {

    public List<Report> reports;

    public ReportDAO() {
        if (reports == null) {
            reports = new ArrayList<>();
        }
    }

    private int GetLastId() throws ClassNotFoundException {
        reports.clear();
        int id = 0;
        try {
            Connection conn = Utils.Connect.getConnection();
            String sql = """
                         SELECT TOP 1 ReportID
                         FROM Report
                         ORDER BY ReportID DESC;""";
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ReportID");
            } else {
                id = 1;
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Report> Reports() throws ClassNotFoundException {
        try {
            Connection conn = Utils.Connect.getConnection();
            String sql = "Select * from Report order by Time Desc";
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                reports.add(new Report(rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getString("Time"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getBoolean("Status"),
                        rs.getString("Reply")));
            }
            return reports;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<Report> getList() throws SQLException, ClassNotFoundException {
        if (reports.size() <= 0) {
            Reports();
        }
        return reports;
    }

    @Override
    public void create(Report data) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO [FPTBooking].[dbo].[Report] "
                    + "([ReportID], [UserID], [Time], [Title], [Content], [Status]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            int id = GetLastId();
            if (id == 0) {
                throw new Exception();
            }
           
            stmt.setInt(1, id + 1);
            stmt.setInt(2, data.getUserID());
            stmt.setString(3, data.getTime());
            stmt.setString(4, data.getTitle());
            stmt.setString(5, data.getContent());
            stmt.setBoolean(6, data.getStatus());

            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Report read(int id) throws SQLException, ClassNotFoundException {
        Report report = null;
        try {
            String sql = """
                         Select ReportID, re.UserID, Time, Title, Content, Status, Reply, u.Email
                         from Report as re
                         JOIN Users AS u ON u.UserID = re.UserID
                         where ReportID = ?""";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                report = new Report(rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getString("Time"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getBoolean("Status"),
                        rs.getString("Reply"),
                        rs.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public boolean update(int id, Report newData) throws SQLException, ClassNotFoundException {
        String searchSql = """
                           UPDATE [dbo].[Report]
                              SET [Status] = ?
                                 ,[Reply] = ?
                            WHERE ReportID = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setBoolean(1, newData.getStatus());
            st.setString(2, newData.getReply());
            st.setInt(3, id);
            boolean result = st.executeUpdate() > 1;
            if (result) {
                Reports();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
