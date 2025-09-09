package com.solvd.delivery.interfaces;

import com.solvd.delivery.enums.OrderStatus;

public interface IPaymentManager {
    void processPayment(double amountReceived, double amountToPay);

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    default boolean isPaymentComplete(OrderStatus status) {
        return status == OrderStatus.ACCEPTED;
    }
}
