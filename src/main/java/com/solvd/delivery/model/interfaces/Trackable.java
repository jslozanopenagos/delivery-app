package com.solvd.delivery.model.interfaces;

import com.solvd.delivery.model.locations.Location;

public interface Trackable {
    void updateLocation(Location newLocation);

    Location getCurrentLocation();
}
