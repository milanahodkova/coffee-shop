package org.example.models;

public class CoffeeType {
    private int id;
    private String typeName;
    private double price;
    private char disabled;

    public CoffeeType(int id, String typeName, double price, char disabled) {
        this.id = id;
        this.typeName = typeName;
        this.price = price;
        this.disabled = disabled;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getDisabled() {
        return disabled;
    }

    public void setDisabled(char disabled) {
        this.disabled = disabled;
    }
}
