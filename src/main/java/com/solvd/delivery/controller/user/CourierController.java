package com.solvd.delivery.controller.user;

import com.solvd.delivery.enums.VehicleType;
import com.solvd.delivery.model.users.Courier;
import com.solvd.delivery.controller.session.DashboardLoaderController;

import com.solvd.delivery.exceptions.DashboardNotFoundException;

import java.util.*;

public class CourierController extends UserController {
    private long courierId = 1;
    private static final Set<Courier> COURIERS = new TreeSet<>(Comparator.comparingLong(Courier::getId));

    public void registerCourier() {
        long id = courierId++;

        System.out.println("Registering a new courier user...");

        String courierName = personalInfoInput("Full name: ");
        String courierEmail = userEmailChecker();
        String courierPassword = personalInfoInput("Password: ");
        String courierPhone = userPhoneChecker();

        String vehicleTypeMenu;
        try {
            vehicleTypeMenu = DashboardLoaderController.genericDashboard("courierVehicleTypeRegistration.txt");
            LOGGER.info(vehicleTypeMenu);
        } catch (DashboardNotFoundException e) {
            LOGGER.error("Failed to load dashboard: {}", e.getMessage(), e);
        }

        String vehicleChoice = SCANNER.nextLine();
        VehicleType vehicleType;

        switch (vehicleChoice) {
            case "1" -> vehicleType = VehicleType.WALKING;
            case "2" -> vehicleType = VehicleType.BICYCLE;
            case "3" -> vehicleType = VehicleType.CAR;
            case "4" -> vehicleType = VehicleType.SCOOTER;
            case "5" -> vehicleType = VehicleType.STEP_VAN;
            default -> {
                System.out.println("Invalid vehicle type. Defaulting to WALKING.");
                vehicleType = VehicleType.WALKING;
            }
        }

        String plate;

        if (vehicleChoice.equals("1") || vehicleChoice.equals("2")) {
            plate = "N.A";
        } else {
            System.out.print("License Plate: ");
            plate = SCANNER.nextLine();
        }

        float rating = 0.0f;

        Courier courier = new Courier(
                id, courierName,
                courierPassword, courierEmail,
                courierPhone, new PriorityQueue<>(),
                true, vehicleType, null, plate, rating
        );

        COURIERS.add(courier);
        USERS.add(courier);

        LOGGER.info("Courier registered successfully.");
    }
}