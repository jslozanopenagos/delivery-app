package com.solvd.delivery.model.users;

import com.solvd.delivery.enums.UserRole;
import com.solvd.delivery.model.interfaces.MenuManageable;
import com.solvd.delivery.model.foodEstablishments.MenuItem;
import com.solvd.delivery.model.foodEstablishments.Restaurant;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Manager extends User implements MenuManageable {
    private boolean isVerified;
    private List<FoodEstablishment> foodEstablishmentList;

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
        for (MenuItem existingItem : items) {
            if (existingItem.getName().equalsIgnoreCase(item.getName())) {
                System.out.println("Item with the same name already exists!");
                return;
            }
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
        Set<MenuItem> items = establishment.getMenuItems();

        MenuItem toRemove = null;
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(updatedItem.getName())) {
                toRemove = item;
                break;
            }
        }

        if (toRemove != null) {
            items.remove(toRemove);
            items.add(updatedItem);
            establishment.setMenuItems(items);
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