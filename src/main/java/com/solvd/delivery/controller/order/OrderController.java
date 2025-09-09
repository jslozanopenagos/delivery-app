package com.solvd.delivery.controller.order;

import com.solvd.delivery.model.foodEstablishments.MenuItem;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;
import com.solvd.delivery.exceptions.FoodEstablishmentNotFoundException;

import com.solvd.delivery.model.orders.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class OrderController {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
    private final PriorityQueue<Order> orderQueue = new PriorityQueue<>(
            Comparator.comparing(Order::getDeliveryTime)
    );

    public void browseFoodEstablishments(Set<FoodEstablishment> foodEstablishments,
                                         String label
    ) {
        selectAvailableFoodEstablishment(foodEstablishments, label).ifPresent(this::showMenu);
    }

    public void addToOrderQueue(Order order) {
        orderQueue.add(order);
        LOGGER.info("Order {} added", order.getOrderID());
    }

    public Order nextOrder() {
        return orderQueue.peek();
    }

    public Order processNextOrder() {
        Order nextOrder = orderQueue.poll();

        if (nextOrder != null) {
            LOGGER.info("Order {} processed", nextOrder.getOrderID());
        } else {
            LOGGER.info("No orders to process");
        }

        return nextOrder;
    }

    private Optional<FoodEstablishment> selectAvailableFoodEstablishment(
            Set<FoodEstablishment> foodEstablishments,
            String label
    ) throws FoodEstablishmentNotFoundException {
        try {
            if (foodEstablishments == null || foodEstablishments.isEmpty()) {
                throw new FoodEstablishmentNotFoundException("No " + label.toLowerCase() + " available.");
            }

            List<FoodEstablishment> establishmentsList = new ArrayList<>(foodEstablishments);

            System.out.println("\n--- Available " + label.toLowerCase() + " ---");

            IntStream.range(0, establishmentsList.size())
                    .forEach(i -> System.out.println(
                                    (i + 1) + ". " +
                                    establishmentsList.get(i).getName() +
                                    " - " + establishmentsList.get(i).getAddress()
                    ));

            System.out.print("Choose a " + label + " to see its menu (only numbers): ");
            String input = SCANNER.nextLine();

            if (!input.matches("[0-9]+")) {
                LOGGER.warn("Invalid input. Only numbers are allowed.\n");
                return Optional.empty();
            }

            int selectedIndex = Integer.parseInt(input) - 1;

            if (selectedIndex < 0 || selectedIndex >= foodEstablishments.size()) {
                LOGGER.warn("Invalid {} number\n", label);
                return Optional.empty();
            }

            return Optional.of(establishmentsList.get(selectedIndex));

        } catch (FoodEstablishmentNotFoundException e) {
            LOGGER.warn(e.getMessage());
            return Optional.empty();
        }
    }

    private void showMenu(FoodEstablishment foodEstablishment) {
        System.out.println("\n--- Menu of " + foodEstablishment.getName() + " ---");

        Set<MenuItem> menuItems = foodEstablishment.getMenuItems();

        if (menuItems.isEmpty()) {
            LOGGER.warn("This restaurant has no menu items.\n");
            return;
        }

        long availableItems = menuItems.stream()
                .filter(Objects::nonNull)
                .filter(MenuItem::isAvailable)
                .peek(item -> System.out.println(
                                "- " + item.getName() +
                                " ($" + item.getPrice() + "): " +
                                item.getDescription()
                ))
                .count();

        if (availableItems == 0) {
            LOGGER.warn("No available menu items at the moment.\n");
        }
    }
}