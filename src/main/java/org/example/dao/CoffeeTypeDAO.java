package org.example.dao;

import org.example.models.CoffeeType;

import java.util.List;

public interface CoffeeTypeDAO {
    CoffeeType getById(int id);
    List<CoffeeType> getAll();
    int getIdByName(String coffeeTypeName);
    List<String> getAllTypeNames();
    void add(CoffeeType coffeeType);
    void update(CoffeeType coffeeType);
    void delete(int id);
}
