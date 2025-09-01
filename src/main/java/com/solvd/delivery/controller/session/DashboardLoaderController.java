package com.solvd.delivery.controller.session;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.solvd.delivery.exceptions.DashboardNotFoundException;

public class DashboardLoaderController {

    private static final Logger LOGGER = LogManager.getLogger(DashboardLoaderController.class);

    public static String userDashboard(String role) throws DashboardNotFoundException {
        StringBuilder dashboard = new StringBuilder();

        try (InputStream inputStream = DashboardLoaderController
                .class.getClassLoader()
                .getResourceAsStream("dashboards.txt")
        ){
            if (inputStream == null) {
                LOGGER.error("dashboards.txt not found in resources folder");
                throw new DashboardNotFoundException("dashboards.txt not found in resources folder");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                boolean roleSection = false;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().equalsIgnoreCase("[" + role + "]")) {
                        roleSection = true;
                        continue;
                    }
                    if (roleSection && line.startsWith("[")) {
                        break;
                    }
                    if (roleSection) {
                        dashboard.append(line).append(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error reading dashboards.txt: {}", e.getMessage());
            throw new DashboardNotFoundException("Error reading dashboards.txt: " + e.getMessage(), e);
        }

        if (dashboard.length() == 0) {
            LOGGER.warn("Dashboard for role '{}' not found", role);
            throw new DashboardNotFoundException("Dashboard for role '" + role + "' not found.");
        }

        LOGGER.info("Dashboard for role '{}' loaded successfully", role);
        return dashboard.toString();
    }

    public static String genericDashboard(String fileName) throws DashboardNotFoundException {
        StringBuilder dashboard = new StringBuilder();

        try(InputStream inputStream = DashboardLoaderController
                .class.getClassLoader()
                .getResourceAsStream(fileName)){

            if (inputStream == null) {
                LOGGER.error("{} not found in resources folder", fileName);
                throw new DashboardNotFoundException(fileName + " not found in resources folder");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    dashboard.append(line).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error reading {}: {}", fileName, e.getMessage());
            throw new DashboardNotFoundException("Error reading " + fileName + ": " + e.getMessage(), e);
        }

        if (dashboard.length() == 0) {
            LOGGER.warn("Dashboard {} is empty", fileName);
            throw new DashboardNotFoundException("Dashboard is empty: " + fileName);
        }

        LOGGER.info("Dashboard {} loaded successfully", fileName);

        return dashboard.toString();
    }
}