package org.example.daoImpl;

import org.example.dao.CoffeeOrderDAO;
import org.example.models.CoffeeOrder;
import org.example.utils.DatabaseUtil;

import java.sql.*;
import java.util.List;

public class CoffeeOrderDAOImpl implements CoffeeOrderDAO {

    @Override
    public int createAndGetId(CoffeeOrder coffeeOrder) {
        String query = "INSERT INTO coffeeorder (order_date, name, delivery_address, cost) VALUES (?, ?, ?, ?)";
        int orderId = -1; // Идентификатор созданного заказа
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(coffeeOrder.getOrderDate()));
            preparedStatement.setString(2, coffeeOrder.getName());
            preparedStatement.setString(3, coffeeOrder.getDeliveryAddress());
            preparedStatement.setDouble(4, coffeeOrder.getCost());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public CoffeeOrder getById(int id) {
        return null;
    }

    @Override
    public List<CoffeeOrder> getAll() {
        return null;
    }

    @Override
    public void update(CoffeeOrder coffeeOrder) {

    }

    @Override
    public void delete(int id) {

    }

}
