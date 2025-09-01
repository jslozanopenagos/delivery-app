package com.solvd.delivery.model.foodEstablishments;

import java.util.*;

public abstract class FoodEstablishment {
    private final long id;
    private String name;
    private String address;
    private int phone;
    private String openingHours;
    private boolean isOpen;
    private String description;
    private Set<MenuItem> menuItems;
    private float rating;

    public FoodEstablishment(
            long id, String name,
            String address, int phone,
            String openingHours, boolean isOpen,
            String description, Set<MenuItem> menuItems, float rating
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.openingHours = openingHours;
        this.isOpen = isOpen;
        this.description = description;
        this.menuItems = (menuItems != null) ? menuItems : new HashSet<>();
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MenuItem> getMenuItems() {
        return new HashSet<>(menuItems);
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = new HashSet<>(menuItems);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEstablishment foodEstablishment = (FoodEstablishment) o;
        return id == foodEstablishment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\n---Food establishment information---\n" +
                "\nEstablishment name: " + name +
                "\nEstablishment address: " + address +
                "\nEstablishment phone" + phone +
                "\nEstablishment opening hours: " + openingHours;
    }
}