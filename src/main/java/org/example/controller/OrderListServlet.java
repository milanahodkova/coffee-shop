package org.example.controller;

import org.example.dao.CoffeeOrderDAO;
import org.example.dao.CoffeeOrderItemDAO;
import org.example.dao.CoffeeTypeDAO;
import org.example.daoImpl.CoffeeOrderDAOImpl;
import org.example.daoImpl.CoffeeOrderItemDAOImpl;
import org.example.daoImpl.CoffeeTypeDAOImpl;
import org.example.models.CoffeeOrder;
import org.example.models.CoffeeOrderItem;
import org.example.service.CoffeeOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {
    private CoffeeOrderService coffeeOrderService;

    @Override
    public void init() throws ServletException {
        super.init();
        coffeeOrderService = new CoffeeOrderService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String address = req.getParameter("address");

        Double cost = (Double) req.getSession().getAttribute("finalPrice");

        List<String> coffeeTypes = (List<String>) req.getSession().getAttribute("coffeeTypes");
        List<Integer> quantitiesList = (List<Integer>) req.getSession().getAttribute("quantities");

        coffeeOrderService.createCoffeeOrder(fullName, address, cost, coffeeTypes, quantitiesList);

        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }
}
