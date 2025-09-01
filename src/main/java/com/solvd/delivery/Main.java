package com.solvd.delivery;

import com.solvd.delivery.controller.user.UserController;
import com.solvd.delivery.controller.order.OrderController;
import com.solvd.delivery.controller.session.SessionController;
import com.solvd.delivery.controller.session.GenericUserController;
import com.solvd.delivery.controller.session.DashboardLoaderController;
import com.solvd.delivery.controller.establishment.RestaurantController;
import com.solvd.delivery.controller.establishment.SupermarketController;

import static com.solvd.delivery.fileexample.WordCounterFromFile.wordCounter;

import com.solvd.delivery.exceptions.DashboardNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        UserController userController = new GenericUserController();
        OrderController orderController = new OrderController();
        RestaurantController restaurantService = new RestaurantController();
        SupermarketController supermarketService = new SupermarketController();

        SessionController sessionController = new SessionController(
                orderController,
                restaurantService,
                supermarketService
        );

        boolean running = true;

        while (running) {
            String menuDashboard;
            try {
                menuDashboard = DashboardLoaderController.genericDashboard("mainMenu.txt");
                LOGGER.info(menuDashboard);
            } catch (DashboardNotFoundException e) {
                LOGGER.error("Failed to load dashboard: {}", e.getMessage(), e);
                break;
            }

            String input = SCANNER.nextLine();

            switch (input) {
                case "1" -> {
                    try {
                        userController.registerUserActions();
                    } catch (DashboardNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2" ->
                        userController.loginUser().ifPresentOrElse(
                                sessionController::startSession,
                                () -> LOGGER.warn("Invalid username or password.")
                        );
                case "3" -> {
                    String inputFile = "poems.txt";
                    String outputFile = "./src/main/outputFiles/wordCounter.txt";

                    LOGGER.info("Enter the word you want to search in the file poems.txt: ");
                    String keyword = SCANNER.nextLine().trim();

                    int count = wordCounter(inputFile, outputFile, keyword);

                    LOGGER.info("Search finished. The word '{}' was found {} time(s) in '{}'. Result also saved in '{}', search in 'outputFiles' folder.",
                            keyword, count, inputFile, outputFile);
                }
                case "4" -> {
                    LOGGER.info("Exiting the app. Goodbye!");
                    running = false;
                }
                default -> LOGGER.warn("Invalid option. Please try again.\n");
            }
        }
    }
}