package org.example.daoImpl;

import com.mysql.cj.xdevapi.PreparableStatement;
import org.example.dao.CoffeeOrderDAO;
import org.example.dao.CoffeeTypeDAO;
import org.example.models.CoffeeType;
import org.example.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeTypeDAOImpl implements CoffeeTypeDAO {

    @Override
    public CoffeeType getById(int id) {
        return null;
    }

    @Override
    public List<CoffeeType> getAll() {
        List<CoffeeType> coffeeTypes = new ArrayList<>();
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM coffeetype WHERE disabled != 'Y' " +
                    "OR disabled IS NULL");
            ResultSet rs = stmt.executeQuery()
        ) {
            while(rs.next()){
                int id = rs.getInt(1);
                String typeName = rs.getString(2);
                double price = rs.getDouble(3);
                String disabledStr = rs.getString(4);
                char disabled = disabledStr != null && !disabledStr.isEmpty() ? disabledStr.charAt(0) : ' ';

                CoffeeType coffeeType = new CoffeeType(id, typeName, price, disabled);
                coffeeTypes.add(coffeeType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  coffeeTypes;
    }

    @Override
    public List<String> getAllTypeNames() {
        List<String> typeNames = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT type_name FROM coffeetype WHERE disabled != 'Y' OR disabled IS NULL");
             ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                String typeName = rs.getString("type_name");
                typeNames.add(typeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeNames;
    }

    @Override
    public int getIdByName(String coffeeTypeName) {
        int coffeeTypeId = -1;

        try(Connection connection = DatabaseUtil.getConnection()){
            String query = "SELECT id FROM coffeetype WHERE type_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coffeeTypeName);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    coffeeTypeId = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coffeeTypeId;
    }

    @Override
    public void add(CoffeeType coffeeType) {

    }

    @Override
    public void update(CoffeeType coffeeType) {

    }

    @Override
    public void delete(int id) {

    }
}
