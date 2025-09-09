package com.solvd.delivery.model.locations;

import com.solvd.delivery.model.users.Customer;
import com.solvd.delivery.interfaces.ITrackPositionManager;

public class CustomerLocation extends Location implements ITrackPositionManager {
    Customer customer;

    public CustomerLocation(
            double latitude, double longitude,
            String address, String city,
            String country, Customer customer
    ) {
        super(latitude, longitude, address, city, country);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void updateLocation(Location newLocation) {
        if (newLocation instanceof CustomerLocation
                && customer.getIsHasActiveOrder()) {
            this.setLatitude(newLocation.getLatitude());
            this.setLongitude(newLocation.getLongitude());
            this.setAddress(newLocation.getAddress());
            this.setCity(newLocation.getCity());
            this.setZipCode(newLocation.getZipCode());

            System.out.println("User location updated to " + newLocation.getAddress());
        } else {
            System.out.println("Invalid location type for customer");
        }
    }

    @Override
    public Location getCurrentLocation() {
        return this;
    }

    @Override
    public String toString() {
        return "CourierLocation{" +
                "courier=" + customer.getFullName() +
                ", address='" + getAddress() + '\'' +
                '}';
    }
}