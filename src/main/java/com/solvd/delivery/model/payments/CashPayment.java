package com.solvd.delivery.model.payments;

import com.solvd.delivery.enums.OrderStatus;
import com.solvd.delivery.enums.PaymentMethod;
import com.solvd.delivery.model.users.Courier;
import com.solvd.delivery.model.users.Customer;

public class CashPayment extends Payment{
    Customer customer;
    Courier courier;
    boolean cashReceived;

    public CashPayment(
            long paymentID, long orderID,
            double amount, PaymentMethod paymentMethod,
            OrderStatus status, Customer customer,
            Courier courier, boolean cashReceived
    ){
        super(paymentID, orderID, amount, paymentMethod, status);
        this.customer = customer;
        this.courier = courier;
        this.cashReceived = cashReceived;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Courier getCourier(){
        return courier;
    }

    public void setCourier(Courier courier){
        this.courier = courier;
    }

    public boolean getIsCashReceived(){
        return cashReceived;
    }

    public void setIsCashReceived(boolean cashReceived){
        this.cashReceived = cashReceived;
    }

    @Override
    public void processPayment(double cashReceived, double amountToPay){
        if (cashReceived != amountToPay){
            System.out.println("the cash received is incorrect");
        } else {
            System.out.println("Processing cash payment of $" + amountToPay);
            setStatus(OrderStatus.ACCEPTED);
        }

    }
}