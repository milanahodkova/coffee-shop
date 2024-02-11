package org.example.models;

public class CoffeeOrderItem {
    private int id;
    private int typeId;
    private int orderId;
    private int quantity;

    public CoffeeOrderItem() {
    }

    public CoffeeOrderItem(int typeId, int orderId, int quantity) {
        this.typeId = typeId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
