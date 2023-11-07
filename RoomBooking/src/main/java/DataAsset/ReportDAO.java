package DataAsset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;

public class ReportDAO extends BaseDataAsset<Report> {

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

    @Override
    public List<Report> getList() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Report data) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO [FPTBooking].[dbo].[Report] ([ReportID], [UserID], [Time], [Title], [Content], [Status]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            int id = GetLastId();
            if (id == 0) {
                throw new Exception();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, h:mm:ss a");
            Date date = sdf.parse(data.getTime());
            Timestamp timestamp = new Timestamp(date.getTime());
            stmt.setInt(1, id + 1);
            stmt.setInt(2, data.getUserID());
            stmt.setTimestamp(3, timestamp);
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
