package com.solvd.delivery.controller.payment;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.model.payments.DigitalPayment;

public class DigitalPaymentProcessor implements PaymentProcessor<DigitalPayment> {
    @Override
    public void processPayment(DigitalPayment payment) {
        System.out.println("Processing digital payment of $" + payment.getAmount()
                + " using " + payment.getPaymentProvider());

        payment.setStatus(OrderStatus.ACCEPTED);

        boolean accepted = payment.getStatus() == OrderStatus.ACCEPTED;
        System.out.println("Payment accepted: " + accepted);
    }
}