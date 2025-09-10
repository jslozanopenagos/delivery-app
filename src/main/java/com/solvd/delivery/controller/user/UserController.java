package com.solvd.delivery.controller.user;

import com.solvd.delivery.model.users.*;
import com.solvd.delivery.interfaces.IValidator;
import com.solvd.delivery.interfaces.ITransformer;
import com.solvd.delivery.interfaces.IActionHandler;
import com.solvd.delivery.controller.session.DashboardLoaderController;

import com.solvd.delivery.exceptions.DashboardNotFoundException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import java.util.ArrayList;

public abstract class UserController {
    protected static final List<User> USERS = new ArrayList<>();
    protected static final Scanner SCANNER = new Scanner(System.in);
    protected static final Logger LOGGER = LogManager.getLogger(UserController.class);

    public void registerUserActions() throws DashboardNotFoundException {
        boolean backToMain = false;

        while (!backToMain) {
            String menuDashboard;
            try {
                menuDashboard = DashboardLoaderController.genericDashboard("userRegistration.txt");
                LOGGER.info(menuDashboard);
            } catch (DashboardNotFoundException e) {
                LOGGER.error("Failed to load dashboard: {}", e.getMessage(), e);
                break;
            }


            String roleInput = SCANNER.nextLine().trim();

            switch (roleInput) {
                case "1", "01", "customer" -> {
                    new CustomerController().registerCustomer();
                    backToMain = true;
                }
                case "2", "02", "courier" -> {
                    new CourierController().registerCourier();
                    backToMain = true;
                }
                case "3", "03", "manager" -> {
                    new ManagerController().registerManager();
                    backToMain = true;
                }
                case "4", "exit", "back" -> backToMain = true;
                default -> LOGGER.warn("Invalid role option, no user was saved!\n");
            }
        }
    }

    public Optional<User> loginUser() {
        System.out.print("Enter username: ");
        String username = SCANNER.nextLine();

        System.out.print("Enter password: ");
        String password = SCANNER.nextLine();

        IValidator<User> loginValidator = user ->
                user.getFullName().equals(username) && user.getPassword().equals(password);

        IActionHandler<User> loginSuccessful = user ->
                LOGGER.info("Login successful! Welcome, {}", user.getFullName());

        ITransformer<User, String> sessionTokenGenerator = user ->
                        user.getFullName().replaceAll("\\s+", "").toLowerCase() +
                        "_" + System.currentTimeMillis();

        return USERS.stream()
                .filter(loginValidator::validate)
                .findFirst()
                .map(user -> {
                    loginSuccessful.handle(user);
                    String sessionToken = sessionTokenGenerator.transform(user);
                    LOGGER.info("Generated session token for {}: {}", user.getFullName(), sessionToken);
                    return user;
                })
                .or(() -> {
                    LOGGER.warn("Login failed for username '{}'", username);
                    return Optional.empty();
                });
    }

    public String personalInfoInput(String label) {
        System.out.print(label);
        String input = SCANNER.nextLine();

        while (input.isBlank()) {
            LOGGER.warn("Input cannot be empty. Try again: ");
            input = SCANNER.nextLine();
        }

        return input;
    }

    public String userEmailChecker() {
        System.out.print("Email: ");
        String email = SCANNER.nextLine();

        while (email.isBlank() ||
                email.chars().filter(ch -> ch == '@').count() != 1 ||
                !email.substring(email.indexOf('@')).contains(".")) {
            LOGGER.warn("Invalid email. Must contain exactly one '@' and at least one '.' after it. Try again:\n");
            email = SCANNER.nextLine();
        }

        return email;
    }

    public String userPhoneChecker() {
        System.out.print("Phone: ");
        String phone = SCANNER.nextLine();

        while (!phone.matches("\\d+")) {
            LOGGER.warn("Invalid phone number. Only digits allowed. Try again: ");
            phone = SCANNER.nextLine();
        }

        return phone;
    }
}