package com.solvd.delivery.model.foodEstablishments;

import com.solvd.delivery.interfaces.IRateHandler;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Supermarket extends FoodEstablishment implements IRateHandler {
    private float totalRating = 0f;
    private int ratingCount = 0;

    private List<String> productCategories;
    private boolean bulkDiscounts;

    public Supermarket(
            long id, String name,
            String address, int phone,
            String openingHours, boolean isOpen,
            String description, Set<MenuItem> menuItems,
            float rating, List<String> productCategories, boolean bulkDiscounts
    ) {
        super(id, name, address, phone, openingHours, isOpen, description, menuItems, rating );
        this.productCategories = (productCategories != null) ? productCategories : new ArrayList<String>();
        this.bulkDiscounts = bulkDiscounts;
    }

    public List<String> getProductCategories() {
        return new ArrayList<>(productCategories);
    }

    public void setProductCategories(List<String> productCategories) {
        this.productCategories = new ArrayList<>(productCategories);
    }

    public boolean isBulkDiscounts() {
        return bulkDiscounts;
    }

    public void setBulkDiscounts(boolean bulkDiscounts) {
        this.bulkDiscounts = bulkDiscounts;
    }

    public void addCategory(String category) {
        productCategories.add(category);
    }

    @Override
    public float getTotalRating() {
        return totalRating;
    }

    @Override
    public int getRatingCount() {
        return ratingCount;
    }

    @Override
    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    @Override
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return super.toString() + "\nProduct categories: " + productCategories;
    }
}