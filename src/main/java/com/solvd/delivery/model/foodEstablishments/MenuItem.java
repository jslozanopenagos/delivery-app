package com.solvd.delivery.model.foodEstablishments;

import java.util.Objects;

public abstract class MenuItem {
    private final long menuItemId;
    private final long foodEstablishmentId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private boolean available;
    private boolean selected;

    public MenuItem(
            long menuItemId, long restaurantId,
            String name, String description,
            double price, int quantity,
            boolean available, boolean selected
    ) {
        this.menuItemId = menuItemId;
        this.foodEstablishmentId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.selected = selected;
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public long getFoodEstablishmentId() {
        return foodEstablishmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return menuItemId == menuItem.menuItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemId);
    }
}