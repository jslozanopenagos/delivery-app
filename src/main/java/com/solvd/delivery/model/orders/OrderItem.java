package com.solvd.delivery.model.orders;

public class OrderItem {
    private final long orderItemID;
    private final long orderID;
    private long menuItemID;
    private int quantity;

    public OrderItem(
            long orderItemID, long orderID,
            long menuItemID, int quantity
    ) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.menuItemID = menuItemID;
        this.quantity = quantity;
    }

    public long getOrderItemID() {
        return orderItemID;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(long menuItemID) {
        this.menuItemID = menuItemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}