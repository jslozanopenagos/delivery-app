package com.solvd.delivery.controller.payment;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.interfaces.IPaymentProcessor;
import com.solvd.delivery.model.payments.DigitalPayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DigitalPaymentProcessor implements IPaymentProcessor<DigitalPayment> {
    private static final Logger LOGGER = LogManager.getLogger(DigitalPaymentProcessor.class);

    @Override
    public void processPayment(DigitalPayment payment) {
        LOGGER.info("Processing digital payment of ${} using {}"
                , payment.getAmount()
                , payment.getPaymentProvider());

        payment.setStatus(OrderStatus.ACCEPTED);

        boolean accepted = payment.getStatus() == OrderStatus.ACCEPTED;
        LOGGER.info("Payment accepted: {}", accepted);
    }
}