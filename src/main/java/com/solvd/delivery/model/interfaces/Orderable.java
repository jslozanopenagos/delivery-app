package com.solvd.delivery.model.interfaces;

import com.solvd.delivery.model.orders.Order;

import java.util.List;

public interface Orderable {
    void placeOrder(Order order);

    List<Order> getOrderHistory();
}
