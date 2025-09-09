package com.solvd.delivery.controller.establishment;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public abstract class FoodEstablishmentController {
    protected long menuItemId = 1;

    protected static final Scanner SCANNER = new Scanner(System.in);
    protected static final Logger LOGGER = LogManager.getLogger(FoodEstablishmentController.class);

    protected FoodEstablishment selectFoodEstablishment(List<FoodEstablishment> establishments) {
        if (establishments.isEmpty()) {
            LOGGER.warn("You must first create at least one food establishment before adding items.");
            return null;
        }

        System.out.println("\n--- Select a food establishment ---");

        IntStream.range(0, establishments.size())
                .forEach(i -> System.out.println((i + 1) + ". " + establishments.get(i).getName()));


        System.out.print("Choose a food establishment [1-" + establishments.size() + "]: ");
        String input = SCANNER.nextLine();

        if (!input.matches("\\d+")) {
            LOGGER.warn("Invalid input. Please enter a number.");
            return null;
        }

        int selectedIndex = Integer.parseInt(input) - 1;
        if (selectedIndex < 0 || selectedIndex >= establishments.size()) {
            LOGGER.warn("Invalid selection! Please try again.");
            return null;
        }

        return establishments.get(selectedIndex);
    }

    public int parsePhone(String phoneInput) {

        while (!phoneInput.matches("\\d+")) {
            LOGGER.warn("Invalid phone. Digits only. Try again: ");
            phoneInput = SCANNER.nextLine();
        }
        return Integer.parseInt(phoneInput);
    }

    public String nonEmptyInput() {
        String input = SCANNER.nextLine();

        while (input.isBlank()) {
            LOGGER.warn("Input cannot be empty. Try again: ");
            input = SCANNER.nextLine();
        }

        return input;
    }

    public boolean checkYesOrNo(String label) {
        String input;
        boolean isValid;

        do {
            System.out.print(label + "? (yes/no): ");
            input = SCANNER.nextLine().trim().toLowerCase();

            isValid = input.equals("yes") || input.equals("no");

            if (!isValid) {
                LOGGER.warn("Please type 'yes' or 'no'.");
            }

        } while (!isValid);

        return input.equals("yes");
    }
}