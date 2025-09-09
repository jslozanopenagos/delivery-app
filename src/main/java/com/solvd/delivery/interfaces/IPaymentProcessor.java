package com.solvd.delivery.interfaces;

import com.solvd.delivery.model.payments.Payment;

public interface IPaymentProcessor<T extends Payment> {
    void processPayment(T payment);
}
