package com.solvd.delivery.model.orders;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.enums.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private long orderID;
    private long costumerID;
    private long foodEstablishmentID;
    private long courierId;
    private List<OrderItem> orderItems;
    private LocalDateTime deliveryTime;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
    private double totalPrice;
    private String deliveryAddress;

    public Order(
            long orderID, long costumerID,
            long foodEstablishmentID, long courierId,
            List<OrderItem> orderItems, LocalDateTime deliveryTime,
            OrderStatus orderStatus, DeliveryStatus deliveryStatus,
            double totalPrice, String deliveryAddress
    ) {
        this.orderID = orderID;
        this.costumerID = costumerID;
        this.foodEstablishmentID = foodEstablishmentID;
        this.courierId = courierId;
        this.orderItems = (orderItems != null) ? orderItems : new ArrayList<>();
        this.deliveryTime = deliveryTime;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(long costumerID) {
        this.costumerID = costumerID;
    }

    public long getFoodEstablishmentID() {
        return foodEstablishmentID;
    }

    public void setFoodEstablishmentID(long foodEstablishmentID) {
        this.foodEstablishmentID = foodEstablishmentID;
    }

    public long getCourierId() {
        return courierId;
    }

    public void setCourierId(long courierId) {
        this.courierId = courierId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = new ArrayList<>(orderItems);
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}