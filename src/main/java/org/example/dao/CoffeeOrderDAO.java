package org.example.dao;

import org.example.models.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderDAO {
    int createAndGetId(CoffeeOrder coffeeOrder);
    CoffeeOrder getById(int id);
    List<CoffeeOrder> getAll();
    void update(CoffeeOrder coffeeOrder);
    void delete(int id);
}
