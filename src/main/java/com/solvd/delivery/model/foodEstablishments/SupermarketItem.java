package com.solvd.delivery.model.foodEstablishments;

import java.util.List;
import java.util.ArrayList;

public class SupermarketItem extends MenuItem {
    List<String> productCategory;

    public SupermarketItem(
            long menuItemId, long restaurantId,
            String name, String description,
            double price, int quantity,
            boolean available, boolean selected, List<String> productCategory
    ) {
        super(menuItemId, restaurantId, name, description, price, quantity, available, selected);
        this.productCategory = (productCategory != null) ? productCategory : new ArrayList<>();
    }

    public List<String> getProductCategory() {
        return new ArrayList<String>(productCategory);
    }

    public void setProductCategory(List<String> productCategory) {
        this.productCategory = new ArrayList<>(productCategory);
    }
}