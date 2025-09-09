package com.solvd.delivery.interfaces;

import com.solvd.delivery.model.locations.Location;

public interface ITrackPositionHandler {
    void updateLocation(Location newLocation);

    Location getCurrentLocation();
}
