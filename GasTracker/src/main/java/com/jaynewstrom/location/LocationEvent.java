package com.jaynewstrom.location;

/**
 * Created by jaynewstrom on 10/5/13.
 */
public final class LocationEvent {
    public final double longitude;
    public final double latitude;

    LocationEvent(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
