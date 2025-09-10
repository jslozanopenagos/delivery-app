package com.solvd.delivery.controller.establishment;

import com.solvd.delivery.annotation.RequiresLogin;
import com.solvd.delivery.model.users.Manager;
import com.solvd.delivery.model.foodEstablishments.Supermarket;
import com.solvd.delivery.model.foodEstablishments.SupermarketItem;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import java.util.*;

public class SupermarketController extends FoodEstablishmentController {

    private long supermarketId = 1;
    private static final Set<FoodEstablishment> SUPERMARKETS = new LinkedHashSet<>();

    @RequiresLogin(roles = {"FOOD_ESTABLISHMENT_MANAGER"})
    public void createSupermarket(Manager manager) {
        System.out.println("--- Register a new supermarket ---");
        System.out.print("Name: ");
        String name = nonEmptyInput();

        System.out.print("Address: ");
        String address = nonEmptyInput();

        System.out.print("Phone: ");
        int phone = Integer.parseInt(nonEmptyInput());

        System.out.print("Opening Hours: ");
        String openingHours = nonEmptyInput();

        System.out.print("Description: ");
        String description = nonEmptyInput();

        System.out.print("Enter product categories (comma separated): ");
        List<String> categories = Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        boolean isOpen =  checkYesOrNo("is your establishment open");
        boolean offersBulkDiscounts = checkYesOrNo("Offers bulk discounts");

        Supermarket supermarket = new Supermarket(
                supermarketId++, name,
                address, phone,
                openingHours, isOpen,
                description, new HashSet<>(),
                0.0f, categories, offersBulkDiscounts
        );

        manager.addFoodEstablishment(supermarket);
        SUPERMARKETS.add(supermarket);

        LOGGER.info("Supermarket registered successfully!");
    }

    @RequiresLogin(roles = {"FOOD_ESTABLISHMENT_MANAGER"})
    public void createSupermarketMenuItem(Manager manager) {
        Supermarket supermarket = (Supermarket) selectFoodEstablishment(new ArrayList<>(SUPERMARKETS));

        if (supermarket == null) return;

        System.out.println("\n--- Add Menu Item ---");

        System.out.print("Item name: ");
        String name = SCANNER.nextLine();

        System.out.print("Description: ");
        String description = SCANNER.nextLine();

        double price = -1;

        while (price < 0) {
            LOGGER.warn("Price (must be greater than zero): ");
            String priceInput = SCANNER.nextLine();
            if (priceInput.matches("[0-9]+(\\.[0-9]+)?")) {
                price = Double.parseDouble(priceInput);
            } else {
                LOGGER.warn("Invalid price. Try again.");
            }
        }

        int quantity = -1;

        while (quantity < 0) {
            System.out.print("Quantity: ");
            String quantityInput = SCANNER.nextLine();
            if (quantityInput.matches("\\d+")) {
                quantity = Integer.parseInt(quantityInput);
            } else {
                LOGGER.warn("Invalid quantity. Try again.");
            }
        }

        System.out.print("Enter the possible categories for this product(comma separated): ");

        List<String> productCategories = Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        SupermarketItem newItem = new SupermarketItem(
                menuItemId++, supermarket.getId(),
                name, description,
                price, quantity,
                true, false, productCategories
        ) {
        };

        manager.addMenuItemToEstablishment(supermarket, newItem);

        LOGGER.info("Product item added successfully to {} !",supermarket.getName());
    }

    public Set<FoodEstablishment> getAllSupermarkets() {
        return this.SUPERMARKETS;
    }
}