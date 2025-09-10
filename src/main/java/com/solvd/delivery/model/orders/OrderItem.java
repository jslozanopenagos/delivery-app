package com.solvd.delivery.model.orders;

public record OrderItem(
        long orderItemID,
        long orderID,
        long restaurantID,
        long menuItemID,
        int quantity) {}