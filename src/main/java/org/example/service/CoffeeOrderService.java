package org.example.service;

import org.example.dao.CoffeeOrderDAO;
import org.example.dao.CoffeeOrderItemDAO;
import org.example.dao.CoffeeTypeDAO;
import org.example.daoImpl.CoffeeOrderDAOImpl;
import org.example.daoImpl.CoffeeOrderItemDAOImpl;
import org.example.daoImpl.CoffeeTypeDAOImpl;
import org.example.models.CoffeeOrder;
import org.example.models.CoffeeOrderItem;
import org.example.utils.DatabaseUtil;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderService {
    private CoffeeOrderDAO coffeeOrderDAO;
    private CoffeeTypeDAO coffeeTypeDAO;
    private CoffeeOrderItemDAO coffeeOrderItemDAO;

    public CoffeeOrderService() {
        coffeeOrderDAO = new CoffeeOrderDAOImpl();
        coffeeTypeDAO = new CoffeeTypeDAOImpl();
        coffeeOrderItemDAO = new CoffeeOrderItemDAOImpl();
    }

    public void createCoffeeOrder(String fullName, String address, Double cost, List<String> coffeeTypes, List<Integer> quantitiesList) {
        CoffeeOrder coffeeOrder = new CoffeeOrder(LocalDateTime.now(), fullName, address, cost);
        int orderId = coffeeOrderDAO.createAndGetId(coffeeOrder);

        for (int i = 0; i < coffeeTypes.size(); i++) {
            String coffeeType = coffeeTypes.get(i);
            int quantity = quantitiesList.get(i);
            int coffeeTypeId = coffeeTypeDAO.getIdByName(coffeeType);
            CoffeeOrderItem coffeeOrderItem = new CoffeeOrderItem(coffeeTypeId, orderId, quantity);
            coffeeOrderItemDAO.create(coffeeOrderItem);
        }
    }
}
