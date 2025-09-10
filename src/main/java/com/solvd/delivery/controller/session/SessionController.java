package com.solvd.delivery.controller.session;

import com.solvd.delivery.model.users.*;
import com.solvd.delivery.annotation.AnnotationProcessor;
import com.solvd.delivery.controller.order.OrderController;
import com.solvd.delivery.controller.establishment.RestaurantController;
import com.solvd.delivery.controller.establishment.SupermarketController;

import com.solvd.delivery.exceptions.DashboardNotFoundException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

public class SessionController {
    private final OrderController orderController;
    private final RestaurantController restaurantService;
    private final SupermarketController supermarketService;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(SessionController.class);

    public SessionController(OrderController orderController,
                             RestaurantController restaurantService,
                             SupermarketController supermarketService) {
        this.orderController = orderController;
        this.restaurantService = restaurantService;
        this.supermarketService = supermarketService;
    }

    public void startSession(User user) {
        boolean backToMain = false;

        while (!backToMain) {
            try {
                String dashboard = DashboardLoaderController.userDashboard(user.getRole().name());
                System.out.println(dashboard);
            } catch (DashboardNotFoundException e) {
                LOGGER.error("Failed to load dashboard: {}", e.getMessage(), e);
                break;
            }

            String choice = SCANNER.nextLine();
            backToMain = handleUserChoice(user, choice);
        }
    }

    private boolean handleUserChoice(User user, String choice) {
        if (user instanceof Customer customer) {
            return handleCustomerChoice(customer, choice);
        } else if (user instanceof Courier courier) {
            return handleCourierChoice(courier, choice);
        } else if (user instanceof Manager manager) {
            return handleManagerChoice(manager, choice);
        }
        return true;
    }

    private boolean handleCustomerChoice(Customer customer, String choice) {
        switch (choice) {
            case "1" -> {
                orderController.browseFoodEstablishments(restaurantService.getAllRestaurants(), "restaurants");
                orderController.browseFoodEstablishments(supermarketService.getAllSupermarkets(), "supermarkets");
            }
            case "2" -> LOGGER.info("View orders... feature coming soon");
            case "3" -> LOGGER.info("Update profile...feature coming soon");
            case "4" -> { return true; }
            default -> LOGGER.warn("Invalid option.");
        }

        return false;
    }

    private boolean handleCourierChoice(Courier courier, String choice) {
        switch (choice) {
            case "1" -> LOGGER.info("1. Available Deliveries....feature coming soon");
            case "2" -> LOGGER.info("2. Completed Deliveries....feature coming soon");
            case "3" -> { return true; }
            default -> LOGGER.warn("Invalid option.");
        }

        return false;
    }

    private boolean handleManagerChoice(Manager manager, String choice) {
        switch (choice) {
            case "1" -> {
                AnnotationProcessor processor = new AnnotationProcessor();
                processor.invoke(restaurantService, manager, "createRestaurant", manager);
            }
            case "2" -> {
                AnnotationProcessor processor = new AnnotationProcessor();
                processor.invoke(supermarketService, manager, "createSupermarket", manager);
            }
            case "3" -> System.out.println("manage restaurants feature coming soon...");
            case "4" -> restaurantService.createRestaurantMenuItem(manager);
            case "5" -> { return true; }
            default -> System.out.println("Invalid option.");
        }

        return false;
    }

}