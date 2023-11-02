package DataAsset;

import Utils.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataAsset<T> extends Connect {

    public ResultSet executeQuery(String SQLQuery) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connection;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQLQuery);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return resultSet;
    }

    public abstract List<T> getList() throws SQLException, ClassNotFoundException;

    public abstract void create(T data) throws SQLException, ClassNotFoundException;

    public abstract T read(int id) throws SQLException, ClassNotFoundException;

    public abstract void update(int id, T newData) throws SQLException, ClassNotFoundException;

    public abstract void delete(int id) throws SQLException, ClassNotFoundException;

    public List<T> readByCondition(String condition) throws SQLException, ClassNotFoundException {
        List<T> dataList = new ArrayList<>();
        
        return dataList;
    }
}
