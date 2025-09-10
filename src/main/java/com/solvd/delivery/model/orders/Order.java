package com.solvd.delivery.model.orders;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.enums.DeliveryStatus;

import java.util.List;
import java.time.LocalDateTime;

public record Order (
        long orderID,
        long costumerID,
        long foodEstablishmentID,
        long courierId,
        List<OrderItem> orderItems,
        LocalDateTime deliveryTime,
        OrderStatus orderStatus,
        DeliveryStatus deliveryStatus,
        double totalPrice,
        String deliveryAddress){}