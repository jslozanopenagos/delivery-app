package com.solvd.delivery.model.users;

import com.solvd.delivery.enums.UserRole;
import com.solvd.delivery.enums.VehicleType;
import com.solvd.delivery.model.orders.Order;
import com.solvd.delivery.model.locations.Location;
import com.solvd.delivery.interfaces.IRateHandler;

import java.util.*;


public class Courier extends User implements IRateHandler {
    private Queue<Order> orders;
    private boolean available;
    private VehicleType vehicleType;
    private Location currentLocation;
    private String licensePlateNumber;
    private float rating;

    private float totalRating = 0f;
    private int ratingCount = 0;

    public Courier(
            long id, String fullName,
            String password, String email,
            String phone, Queue<Order> orders,
            boolean available, VehicleType vehicleType,
            Location currentLocation, String licensePlateNumber, float rating
    ) {
        super(id, fullName, password, email, phone, UserRole.COURIER);
        this.orders = (orders != null) ? orders : new PriorityQueue<>();;
        this.available = available;
        this.vehicleType = vehicleType;
        this.currentLocation = currentLocation;
        this.licensePlateNumber = licensePlateNumber;
        this.rating = rating;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setOrders(Queue<Order> orders) {
        this.orders = new PriorityQueue<>(orders);
    }

    public boolean getIsAvailable() {
        return available;
    }

    public void setIsAvailable(boolean available) {
        this.available = available;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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
        return super.toString() +
                "\nUser vehicle type: " + vehicleType +
                "\nUser license plate number: " + licensePlateNumber;
    }
}