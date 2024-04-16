package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.CoffeeOrderService;

import java.io.IOException;
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
