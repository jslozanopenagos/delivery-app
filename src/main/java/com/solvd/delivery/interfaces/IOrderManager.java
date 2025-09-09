package com.solvd.delivery.interfaces;

import com.solvd.delivery.model.orders.Order;

import java.util.List;

public interface IOrderManager {
    void placeOrder(Order order);

    List<Order> getOrderHistory();
}
