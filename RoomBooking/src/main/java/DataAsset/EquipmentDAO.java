package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Equipment;
import model.typeEquipment;

public class EquipmentDAO extends BaseDataAsset<Equipment> {

    public static List<Equipment> equipments;

    public EquipmentDAO() {
        if (equipments == null) {
            equipments = new ArrayList<>();
        }
    }

    @Override
    public List<Equipment> getList() {
        if (equipments.size() <= 0) {
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
        }
        return equipments;
    }

    @Override
    public void create(Equipment data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Equipment read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(int id, Equipment newData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
