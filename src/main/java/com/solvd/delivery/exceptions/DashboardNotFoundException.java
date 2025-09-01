package com.solvd.delivery.exceptions;

public class DashboardNotFoundException extends Exception {

    public DashboardNotFoundException(String message) {
        super(message);
    }

    public DashboardNotFoundException(String message, Exception e) {
        super(message);
    }
}