package com.solvd.delivery.model.locations;

import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import java.util.List;
import java.util.ArrayList;

public class EstablishmentLocation extends Location {
    boolean isOpen;
    List<FoodEstablishment> foodEstablishments;

    public EstablishmentLocation(
            double latitude, double longitude,
            String address, String city,
            String country, boolean isOpen,
            List<FoodEstablishment> foodEstablishments
    ) {
        super(latitude, longitude, address, city, country);
        this.isOpen = isOpen;
        this.foodEstablishments = (foodEstablishments != null) ? foodEstablishments : new ArrayList<>();
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public List<FoodEstablishment> getFoodEstablishments() {
        return new ArrayList<FoodEstablishment>(foodEstablishments);
    }

    public void setFoodEstablishments(List<FoodEstablishment> foodEstablishments) {
        this.foodEstablishments = new ArrayList<>(foodEstablishments);
    }
}
