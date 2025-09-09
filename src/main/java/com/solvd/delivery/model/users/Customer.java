package com.solvd.delivery.model.users;

import com.solvd.delivery.enums.UserRole;
import com.solvd.delivery.model.orders.Order;
import com.solvd.delivery.enums.PaymentMethod;
import com.solvd.delivery.interfaces.IOrderHandler;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Customer extends User implements IOrderHandler {
    private boolean hasActiveOrder;
    private List<Order> orders;
    private String preferredAddress;
    private List<PaymentMethod> paymentMethods;

    public Customer(
            long id, String fullName,
            String password, String email,
            String phone, boolean hasActiveOrder,
            List<Order> orders, String preferredAddress, List<PaymentMethod> paymentMethods
    ) {
        super(id, fullName, password, email, phone, UserRole.CUSTOMER);
        this.hasActiveOrder = hasActiveOrder;
        this.orders = (orders != null) ? orders : new ArrayList<>();
        this.preferredAddress = preferredAddress;
        this.paymentMethods = paymentMethods;
    }

    public boolean getIsHasActiveOrder() {
        return hasActiveOrder;
    }

    public void setIsHasActiveOrder(boolean hasActiveOrder) {
        this.hasActiveOrder = hasActiveOrder;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public String getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(String preferredAddress) {
        this.preferredAddress = preferredAddress;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @Override
    public void placeOrder(Order order){
        orders.add(order);
    }

    @Override
    public List<Order> getOrderHistory(){
        return orders;
    }

    @Override
    public String toString() {
        return super.toString() + "\nUser preferred address: " + preferredAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false; // compare base fields first
        Customer customer = (Customer) o;
        return Objects.equals(preferredAddress, customer.preferredAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), preferredAddress);
    }
}