package com.solvd.delivery.enums;

public enum UserRole {
    CUSTOMER, COURIER, FOOD_ESTABLISHMENT_MANAGER;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}