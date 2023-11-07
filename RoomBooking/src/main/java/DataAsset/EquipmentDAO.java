package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Equipment;
import model.Room;
import model.typeEquipment;

public class EquipmentDAO extends BaseDataAsset<Equipment> {

    public List<Equipment> equipments;

    public EquipmentDAO() {
        if (equipments == null) {
            equipments = new ArrayList<>();
        }
    }

    private List<Equipment> Equipments() {
        equipments.clear();
        try {
            String sqlQuery = """
                                  SELECT * FROM Equipment, TypeEquipment
                                  where Equipment.TypeID = TypeEquipment.TypeID""";
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipments;
    }

    @Override
    public List<Equipment> getList() {
        if (equipments.size() <= 0) {
            Equipments();
        }
        return equipments;
    }

    @Override
    public void create(Equipment data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Equipment read(int id) {
        Equipment equipment = null;
        String searchSql = """
                           SELECT EquipmentID, EquipmentName, Description, Price, t.TypeID, t.Name as TypeName
                           FROM Equipment AS e
                           JOIN TypeEquipment AS t ON e.TypeID = t.TypeID
                           WHERE e.EquipmentID = ?;""";
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                equipment = new Equipment(resultSet.getInt("EquipmentID"),
                        resultSet.getString("EquipmentName"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("Price"),
                        new typeEquipment(resultSet.getInt("TypeID"), resultSet.getString("TypeName")));
                return equipment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipment;
    }

    @Override
    public boolean update(int id, Equipment newData) {
        String searchSql = """
                           UPDATE [dbo].[Equipment]
                              SET [EquipmentName] = ?
                                 ,[Description] = ?
                                 ,[Price] = ?
                                 ,[TypeID] = ?
                            WHERE EquipmentID = ? """;
        try {
            PreparedStatement st = getConnection().prepareStatement(searchSql);
            st.setString(1, newData.getName());
            st.setString(2, newData.getDes());
            st.setDouble(3, newData.getPrice());
            st.setInt(4, newData.getType().getId());
            st.setInt(5, id);

            boolean result = st.executeUpdate() > 1;
            if (result) {
                Equipments();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
