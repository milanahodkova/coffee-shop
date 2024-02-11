package org.example.daoImpl;

import org.example.dao.CoffeeOrderItemDAO;
import org.example.models.CoffeeOrderItem;
import org.example.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CoffeeOrderItemDAOImpl implements CoffeeOrderItemDAO {
    @Override
    public void create(CoffeeOrderItem coffeeOrderItem) {
        try(Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO coffeeorderitem (type_id, order_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement  = connection.prepareStatement(query);
            preparedStatement.setInt(1, coffeeOrderItem.getTypeId());
            preparedStatement.setInt(2, coffeeOrderItem.getOrderId());
            preparedStatement.setInt(3, coffeeOrderItem.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
