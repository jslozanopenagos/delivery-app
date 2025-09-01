package com.solvd.delivery.controller.user;

import com.solvd.delivery.model.users.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController extends UserController {
    private long customerId = 1;
    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    public void registerCustomer() {
        long id = customerId++;

        System.out.println("Registering a new customer user...");

        String customerName = personalInfoInput("Full name: ");
        String customerEmail = userEmailChecker();
        String customerPassword = personalInfoInput("Password: ");
        String customerPhone = userPhoneChecker();

        System.out.print("Preferred address: ");
        String address = SCANNER.nextLine();

        boolean hasActiveOrder = false;

        Customer customer = new Customer(
                id, customerName,
                customerPassword, customerEmail,
                customerPhone, hasActiveOrder,
                new ArrayList<>(), address, new ArrayList<>()
        );

        CUSTOMERS.add(customer);
        USERS.add(customer);

        LOGGER.info("Customer registered successfully.");
    }
}