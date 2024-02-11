package org.example.service;

import org.example.dao.CoffeeTypeDAO;
import org.example.models.CoffeeType;

import java.util.ArrayList;
import java.util.List;

public class CoffeeTypeService {
    private CoffeeTypeDAO coffeeTypeDAO;

    public CoffeeTypeService(CoffeeTypeDAO coffeeTypeDAO) {
        this.coffeeTypeDAO = coffeeTypeDAO;
    }

    public List<CoffeeType> getAllCoffeeTypes() {
        return coffeeTypeDAO.getAll();
    }
    public List<String> getAllTypeNames(){return coffeeTypeDAO.getAllTypeNames();}
    public List<String> createSelectedCoffeeList(List<String> coffeeTypes, List<String> selectedCoffeeTypes) {
        List<String> result = new ArrayList<>();
        for (String coffeeType : coffeeTypes) {
            if (selectedCoffeeTypes.contains(coffeeType)) {
                result.add(coffeeType);
            } else {
                result.add("");
            }
        }
        return result;
    }
}