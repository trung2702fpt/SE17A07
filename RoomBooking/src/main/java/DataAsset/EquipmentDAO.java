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
import model.Equipment;
import model.Room;
import model.typeEquipment;

/**
 *
 * @author thong
 */
public class EquipmentDAO extends Connect {

    public List<Equipment> GetEquipments() throws ClassNotFoundException {
        List<Equipment> equipments = new ArrayList<Equipment>();
        try {
            String sqlQuery = "SELECT * FROM Equipment, TypeEquipment\n"
                    + "where Equipment.TypeID = TypeEquipment.TypeID";
            PreparedStatement st = getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                typeEquipment type = new typeEquipment(resultSet.getInt("TypeID"), resultSet.getString("Name"));
                Equipment eq = new Equipment(resultSet.getInt("EquipmentID"),
                         resultSet.getString("EquipmentName"),
                         resultSet.getString("Description"),
                         resultSet.getDouble("Price"),
                         type);
                equipments.add(eq);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipments;
    }
}
