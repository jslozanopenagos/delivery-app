package com.solvd.delivery.model.users;

import com.solvd.delivery.enums.UserRole;
import com.solvd.delivery.interfaces.IMenuHandler;
import com.solvd.delivery.model.foodEstablishments.MenuItem;
import com.solvd.delivery.model.foodEstablishments.Restaurant;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Manager extends User implements IMenuHandler {
    private boolean isVerified;
    private List<FoodEstablishment> foodEstablishmentList;
    private static final Logger LOGGER = LogManager.getLogger(Manager.class);

    public Manager(
            long id, String fullName,
            String password, String email,
            String phone, List<FoodEstablishment> restaurantList,
            boolean isVerified
    ) {
        super(id, fullName, password, email, phone, UserRole.FOOD_ESTABLISHMENT_MANAGER);
        this.isVerified = isVerified;
        this.foodEstablishmentList = (restaurantList != null) ? restaurantList : new ArrayList<>();
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean verified) {
        isVerified = verified;
    }

    public List<FoodEstablishment> getRestaurantList() {
        return new ArrayList<>(foodEstablishmentList);
    }

    public void setRestaurantsList(List<Restaurant>restaurantList) {
        this.foodEstablishmentList = new ArrayList<>(restaurantList);
    }

    public void addFoodEstablishment(FoodEstablishment foodEstablishment) {
        foodEstablishmentList.add(foodEstablishment);
    }

    @Override
    public void addMenuItemToEstablishment(FoodEstablishment establishment, MenuItem item) {
        Set<MenuItem> items = establishment.getMenuItems();

        boolean exists = items.stream()
                .anyMatch(existingItem ->
                        existingItem.getName().equalsIgnoreCase(item.getName())
                );

        if (exists) {
            LOGGER.warn("Item with the same name already exists!");
            return;
        }

        items.add(item);
        establishment.setMenuItems(items);
    }

    @Override
    public void removeMenuItemFromEstablishment(FoodEstablishment establishment, String itemName) {
        Set<MenuItem> items = establishment.getMenuItems();
        items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
        establishment.setMenuItems(items);
    }

    @Override
    public void updateMenuItemInEstablishment(FoodEstablishment establishment, MenuItem updatedItem) {
        if (establishment.getMenuItems().remove(updatedItem)) {
            establishment.getMenuItems().add(updatedItem);
        } else {
            System.out.println("Item not found to update.");
        }
    }

    @Override
    public Set<MenuItem> getMenuFromEstablishment(FoodEstablishment establishment) {
        return establishment.getMenuItems();
    }

    @Override
    public String toString() {
        return super.toString() + "FoodEstablishmentManager [isVerified=" + isVerified;
    }
}