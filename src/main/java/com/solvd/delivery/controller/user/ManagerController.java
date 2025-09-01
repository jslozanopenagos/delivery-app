package com.solvd.delivery.controller.user;

import com.solvd.delivery.model.users.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerController extends UserController {
    private long managerId = 1;
    private static final List<Manager> MANAGERS = new ArrayList<>();

    public void registerManager() {
        long id = managerId++;

        System.out.println("Registering a new restaurant manager user...");

        String managerName = personalInfoInput("Full Name: ");
        String managerEmail = userEmailChecker();
        String managerPassword = personalInfoInput("Password: ");
        String managerPhone = userPhoneChecker();

        Manager manager = new Manager(
                id, managerName,
                managerPassword, managerEmail,
                managerPhone, null, false
        );

        MANAGERS.add(manager);
        USERS.add(manager);

        LOGGER.info("Manager registered successfully.");
    }
}