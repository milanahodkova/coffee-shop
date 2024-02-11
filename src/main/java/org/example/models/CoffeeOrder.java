package org.example.models;

import java.time.LocalDateTime;

public class CoffeeOrder {
    private int id;
    private LocalDateTime orderDate;
    private String name;
    private String deliveryAddress;
    private double cost;

    public CoffeeOrder(int id, LocalDateTime orderDate, String name, String deliveryAddress, double cost) {
        this.id = id;
        this.orderDate = orderDate;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.cost = cost;
    }

    public CoffeeOrder(LocalDateTime orderDate, String name, String deliveryAddress, double cost) {
        this.orderDate = orderDate;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
