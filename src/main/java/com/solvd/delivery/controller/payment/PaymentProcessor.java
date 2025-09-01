package com.solvd.delivery.controller.payment;

import com.solvd.delivery.model.payments.Payment;

public interface PaymentProcessor<T extends Payment> {
    void processPayment(T payment);
}
