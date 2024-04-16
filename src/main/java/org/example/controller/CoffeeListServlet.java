package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.daoImpl.CoffeeTypeDAOImpl;
import org.example.daoImpl.ConfigurationDAOImpl;
import org.example.exceptions.InvalidQuantityException;
import org.example.models.CoffeeType;
import org.example.service.CoffeeTypeService;
import org.example.service.ConfigurationService;
import org.example.service.PricingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/")
public class CoffeeListServlet extends HttpServlet {

    private CoffeeTypeService coffeeTypeService;
    private PricingService pricingService;

    @Override
    public void init() throws ServletException {
        super.init();
        coffeeTypeService = new CoffeeTypeService(new CoffeeTypeDAOImpl());
        pricingService = new PricingService(new ConfigurationService(new ConfigurationDAOImpl()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CoffeeType> coffeeTypes = coffeeTypeService.getAllCoffeeTypes();
        request.setAttribute("coffeeTypes", coffeeTypes);
        request.getRequestDispatcher("/jsp/coffeelist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<String> coffeeTypes = coffeeTypeService.getAllTypeNames();
        List<String> selectedCoffeeTypes = Arrays.asList(req.getParameterValues("coffeeType"));
        List<String> prices = Arrays.asList(req.getParameterValues("price"));
        List<String> quantities = Arrays.asList(req.getParameterValues("quantity"));

        List<String> result = coffeeTypeService.createSelectedCoffeeList(coffeeTypes, selectedCoffeeTypes);
        List<String> coffeeList = new ArrayList<>();
        List<Double> pricesList = new ArrayList<>();
        List<Integer> quantitiesList = new ArrayList<>();
        List<Double> sumOfEach = new ArrayList<>();

      try{
          pricingService.validateQuantities(quantities);
      }catch(InvalidQuantityException e){
          req.setAttribute("error", e.getMessage());
          req.getRequestDispatcher("jsp/coffeelist.jsp").forward(req, resp);
          return;
      }
        pricingService.fillOrderLists(result, prices, quantities, coffeeList, pricesList, quantitiesList, sumOfEach);
        double totalPrice = pricingService.calculateTotalPrice(coffeeList, quantitiesList, pricesList);
        double delivery = pricingService.getDeliveryCostByTotalPrice(totalPrice);
        List<Integer> freeCups = pricingService.calculateFreeCups(coffeeList, quantitiesList);

        req.getSession().setAttribute("coffeeTypes", coffeeList);
        req.getSession().setAttribute("prices", pricesList);
        req.getSession().setAttribute("quantities", quantitiesList);
        req.getSession().setAttribute("sum", sumOfEach);
        req.getSession().setAttribute("totalPrice", totalPrice);
        req.getSession().setAttribute("delivery", delivery);
        req.getSession().setAttribute("finalPrice", totalPrice + delivery);
        req.getSession().setAttribute("freeCups", freeCups);

        req.getRequestDispatcher("jsp/orderlist.jsp").forward(req, resp);
    }
}