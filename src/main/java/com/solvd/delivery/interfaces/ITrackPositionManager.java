package com.solvd.delivery.interfaces;

import com.solvd.delivery.model.locations.Location;

public interface ITrackPositionManager {
    void updateLocation(Location newLocation);

    Location getCurrentLocation();
}
