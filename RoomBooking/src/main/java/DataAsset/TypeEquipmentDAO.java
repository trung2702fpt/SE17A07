package DataAsset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.typeEquipment;

public class TypeEquipmentDAO extends BaseDataAsset<typeEquipment> {

    public static List<typeEquipment> types;
    public TypeEquipmentDAO() {
        if(types == null){
            types = new ArrayList<>();
        }
    }

    @Override
    public List<typeEquipment> getList() throws SQLException, ClassNotFoundException {
        if(types.size() <= 0 ){
            try {
                String sqlQuery = "SELECT * FROM TypeEquipment";
                PreparedStatement st = getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = st.executeQuery();

                while (resultSet.next()) {
                    types.add(new typeEquipment(resultSet.getInt("TypeID"), resultSet.getString("Name")));
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return types;
    }

    @Override
    public void create(typeEquipment data) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public typeEquipment read(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(int id, typeEquipment newData) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
