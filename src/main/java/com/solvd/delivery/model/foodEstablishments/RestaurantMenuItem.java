package com.solvd.delivery.model.foodEstablishments;

import java.util.List;
import java.util.ArrayList;

public class RestaurantMenuItem extends MenuItem {

    boolean isVegetarian;
    List<String> ingredients;
    List<String> possibleExtras;

    public RestaurantMenuItem(
            long menuItemId, long restaurantId,
            String name, String description,
            double price, int quantity,
            boolean available, boolean selected,
            boolean isVegetarian, List<String> ingredients, List<String> possibleExtras
    ){
        super(menuItemId, restaurantId, name, description, price, quantity, available, selected);
        this.isVegetarian = isVegetarian;
        this.ingredients = (ingredients != null) ? ingredients : new ArrayList<String>();
        this.possibleExtras = (possibleExtras != null) ? possibleExtras : new ArrayList<>();
    }

    public boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public List<String> getPossibleExtras() {
        return new ArrayList<>(possibleExtras);
    }

    public void setPossibleExtras(List<String> possibleExtras) {
        this.possibleExtras = new ArrayList<>(possibleExtras);
    }
}