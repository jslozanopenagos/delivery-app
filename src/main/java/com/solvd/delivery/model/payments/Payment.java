package com.solvd.delivery.model.payments;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.enums.PaymentMethod;
import com.solvd.delivery.interfaces.IPaymentHandler;

public abstract class Payment implements IPaymentHandler {
    private final long paymentID;
    private final long orderID;
    private final double amount;
    private PaymentMethod paymentMethod;
    private OrderStatus status;

    public Payment(
            long paymentID, long orderID,
            double amount, PaymentMethod paymentMethod, OrderStatus status
    ) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public long getPaymentID() {
        return paymentID;
    }

    public long getOrderID() {
        return orderID;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}