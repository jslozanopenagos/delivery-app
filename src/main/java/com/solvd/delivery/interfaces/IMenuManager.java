package com.solvd.delivery.interfaces;

import com.solvd.delivery.model.foodEstablishments.MenuItem;
import com.solvd.delivery.model.foodEstablishments.FoodEstablishment;

import java.util.Set;

public interface IMenuManager {
    void addMenuItemToEstablishment(FoodEstablishment establishment, MenuItem item);

    void removeMenuItemFromEstablishment(FoodEstablishment establishment, String itemName);

    void updateMenuItemInEstablishment(FoodEstablishment establishment, MenuItem updatedItem);

    Set<MenuItem> getMenuFromEstablishment(FoodEstablishment establishment);
}
