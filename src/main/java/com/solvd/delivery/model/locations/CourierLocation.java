package com.solvd.delivery.model.locations;

import com.solvd.delivery.model.users.Courier;
import com.solvd.delivery.model.users.Customer;
import com.solvd.delivery.interfaces.ITrackPositionHandler;

import java.util.List;
import java.util.ArrayList;

public class CourierLocation extends Location implements ITrackPositionHandler {
    private Courier courier;
    List<Customer> customer;

    public CourierLocation(
            double latitude, double longitude,
            String address, String city,
            String country, Courier courier, List<Customer> customer
    ) {
        super(latitude, longitude, address, city, country);
        this.courier = courier;
        this.customer = (customer != null ? customer : new ArrayList<Customer>());
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<Customer> getCustomer() {
        return new ArrayList<>(customer);
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = new ArrayList<>(customer);
    }

    @Override
    public void updateLocation(Location newLocation) {
        if (newLocation instanceof CourierLocation
                && courier.getIsAvailable()) {
            this.setLatitude(newLocation.getLatitude());
            this.setLongitude(newLocation.getLongitude());
            this.setAddress(newLocation.getAddress());
            this.setCity(newLocation.getCity());
            this.setZipCode(newLocation.getZipCode());
            System.out.println("Courier location updated to " + newLocation.getAddress());
        } else {
            System.out.println("Invalid location type for courier");
        }
    }

    @Override
    public Location getCurrentLocation() {
        return this;
    }

    @Override
    public String toString() {
        return "CourierLocation{" +
                "courier=" + courier.getFullName() +
                ", address='" + getAddress() + '\'' +
                '}';
    }
}