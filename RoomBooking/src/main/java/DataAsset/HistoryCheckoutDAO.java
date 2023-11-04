/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.HistoryCheckout;

/**
 *
 * @author dungnguyen
 */
public class HistoryCheckoutDAO extends Connect {

    public static List<HistoryCheckout> GetHistoryCheckout() throws ClassNotFoundException, SQLException {
        List<HistoryCheckout> historys = new ArrayList<>();
        try {
            String sqlQuery = """
                              SELECT BHA.ID, BHA.RoomID, BHA.BookingDate, BHA.CancelationDate, U.Name
                              FROM BookingHistoryAction BHA
                              INNER JOIN Users U ON BHA.UserID = U.UserID;""";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            try ( ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    HistoryCheckout historyCheckout = new HistoryCheckout(
                            resultSet.getString("Name"),
                            resultSet.getInt("RoomID"),
                            resultSet.getDate("BookingDate"),
                            resultSet.getDate("CancelationDate")
                    );

                    historys.add(historyCheckout);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historys;
    }
}
