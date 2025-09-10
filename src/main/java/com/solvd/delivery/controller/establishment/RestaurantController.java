package com.solvd.delivery.controller.establishment;

import com.solvd.delivery.model.users.Manager;
import com.solvd.delivery.annotation.RequiresLogin;
import com.solvd.delivery.model.foodEstablishments.MenuItem;
import com.solvd.delivery.model.foodEstablishments.Restaurant;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;
import com.solvd.delivery.model.foodEstablishments.RestaurantMenuItem;

import java.util.*;


public class RestaurantController extends FoodEstablishmentController {

    private long restaurantId = 1;
    private static final Set<FoodEstablishment> RESTAURANTS = new LinkedHashSet<>();

    @RequiresLogin(roles = {"FOOD_ESTABLISHMENT_MANAGER"})
    public void createRestaurant(Manager manager) {
        System.out.println("--- Register a new restaurant ---");

        System.out.print("Name: ");
        String name = nonEmptyInput();

        System.out.print("Address: ");
        String address = nonEmptyInput();

        System.out.print("Phone (numbers only): ");
        String phoneInput = nonEmptyInput();
        int phone = parsePhone(phoneInput);

        System.out.print("Opening Hours (e.g., 9AM - 10PM): ");
        String openingHours = nonEmptyInput();

        boolean isOpen =  checkYesOrNo("is your establishment open");

        System.out.print("Cuisine type: ");
        String cuisineType = nonEmptyInput();

        System.out.print("Description: ");
        String description = nonEmptyInput();

        Set<MenuItem> menuItems = new HashSet<>();
        float rating = 0.0f;

        Restaurant restaurant = new Restaurant(
                restaurantId++, name,
                address, phone,
                openingHours, isOpen,
                description, menuItems,
                rating, cuisineType
        );

        manager.addFoodEstablishment(restaurant);
        RESTAURANTS.add(restaurant);

        LOGGER.info("Restaurant registered successfully.");
    }

    @RequiresLogin(roles = {"FOOD_ESTABLISHMENT_MANAGER"})
    public void createRestaurantMenuItem(Manager manager) {
        Restaurant restaurant = (Restaurant) selectFoodEstablishment(new ArrayList<>(RESTAURANTS));

        if (restaurant == null) return;

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

        boolean isVegetarian = checkYesOrNo("is vegetarian");

        System.out.print("Enter menu item ingredients (comma separated): ");

        List<String> ingredients = Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        System.out.print("Enter menu item possible extras (comma separated): ");
        List<String> extras = new ArrayList<>();
        String[] inputExtras = SCANNER.nextLine().split(",");

        for (String extra : inputExtras) {
            extras.add(extra.trim());
        }

        RestaurantMenuItem newItem = new RestaurantMenuItem(
                menuItemId++, restaurant.getId(),
                name, description,
                price, quantity,
                true, false,
                isVegetarian, ingredients, extras
        ) {
        };

        manager.addMenuItemToEstablishment(restaurant, newItem);

        LOGGER.info("Menu item added successfully to {} !", restaurant.getName());
    }

    public Set<FoodEstablishment> getAllRestaurants() {
        return this.RESTAURANTS;
    }
}