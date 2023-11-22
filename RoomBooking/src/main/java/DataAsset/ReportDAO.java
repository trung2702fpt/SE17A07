package DataAsset;

import Utils.StringExtention;
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
import model.Comment;
import model.Report;

public class ReportDAO extends BaseDataAsset<Report> {

    public List<Report> reports;

    public ReportDAO() {
        if (reports == null) {
            reports = new ArrayList<>();
        }
    }

    private int GetLastId() throws ClassNotFoundException {
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
                        rs.getBoolean("IsRead"),
                        rs.getBoolean("IsNewComment")));
            }
            return reports;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<Comment> getComments(int id) throws ClassNotFoundException {
    List<Comment> comments = new ArrayList<>();
    try {
        Connection conn = Utils.Connect.getConnection();
        String sql = """
                     SELECT [Id],[ReportID],[IsReply],[Content],[Time]
                     FROM [dbo].[Comment]
                     WHERE ReportID = ? 
                     """;
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String time = StringExtention.ConverDateToString(rs.getTimestamp("Time"));
            Comment comment = new Comment(
                    rs.getInt("Id"),
                    rs.getInt("ReportID"),
                    rs.getBoolean("IsReply"),
                    rs.getString("Content"),
                    time);
            comments.add(comment);
        }
        return comments;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comments;
}

    public boolean createComment(Comment data, int idReport) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO [dbo].[Comment] VALUES (?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            int id = idReport;
            if (idReport == 0) {
                id = GetLastId();
                if (id == 0) {
                    throw new Exception();
                }
            }

            stmt.setInt(1, id);
            stmt.setBoolean(2, data.isIsReply());
            stmt.setString(3, data.getContent());
            stmt.setString(4, data.getTime());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
            String sql = "INSERT INTO [dbo].[Report]\n"
                    + "           ([UserID],[Time],[Title],[IsRead])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            stmt.setInt(1, data.getUserID());
            stmt.setString(2, data.getTime());
            stmt.setString(3, data.getTitle());
            stmt.setBoolean(4, data.isIsReaded());

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
                         Select ReportID, re.UserID, Time, Title, u.Email
                         from Report as re
                         JOIN Users AS u ON u.UserID = re.UserID
                         where ReportID = ?""";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                report = new Report(rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getString("Time"),
                        rs.getString("Title"),
                        rs.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }
    
    public boolean reply(int id, boolean isUser) throws SQLException, ClassNotFoundException {
        int bitData = isUser ? 1 : 0;
        String searchSql = """
                           UPDATE [dbo].[Report]
                              SET [IsRead] = ?,
                              IsNewComment = ?
                            WHERE ReportID = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setInt(1, bitData);
            st.setInt(2, bitData);
            st.setInt(3, id);
            boolean result = st.executeUpdate() > 0;
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
    public boolean update(int id, Report newData) throws SQLException, ClassNotFoundException {
        String searchSql = """
                           UPDATE [dbo].[Report]
                              SET [IsRead] = false
                                 ,[Reply] = ?
                            WHERE ReportID = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setInt(3, id);
            boolean result = st.executeUpdate() > 0;
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
