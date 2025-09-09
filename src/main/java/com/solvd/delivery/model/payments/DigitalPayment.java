package com.solvd.delivery.model.payments;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.enums.PaymentMethod;
import com.solvd.delivery.model.users.Courier;
import com.solvd.delivery.model.users.Customer;
import com.solvd.delivery.interfaces.Payable;

import com.solvd.delivery.exceptions.PaymentProcessingException;

public class DigitalPayment extends Payment implements Payable {
    Customer customer;

    Courier courier;
    String paymentProvider;

    public DigitalPayment(
            long paymentID, long orderID,
            double amount, PaymentMethod paymentMethod,
            OrderStatus status, Customer customer,
            Courier courier, String paymentProvider
    ){
        super(paymentID, orderID, amount, paymentMethod, status);
        this.customer = customer;
        this.courier = courier;
        this.paymentProvider = paymentProvider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    @Override
    public void processPayment(double amountReceived, double amountToPay) throws PaymentProcessingException {
        if(!Payable.isValidAmount(amountReceived)){
            throw new PaymentProcessingException("Invalid amount of money.");
        }

        System.out.println("Processing digital payment of $" + amountReceived + " using " + paymentProvider);
        setStatus(OrderStatus.ACCEPTED);

        boolean accepted = isPaymentComplete(getStatus());
        System.out.println("Payment accepted: " + accepted);
    }
}