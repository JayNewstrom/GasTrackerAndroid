package com.jaynewstrom.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.jaynewstrom.log.Log;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/5/13.
 */
class GpsLocationFinder implements LocationListener, LocationFinder {

    private final LocationManager locationManager;
    private final Bus bus;
    private final Log log;

    @Inject GpsLocationFinder(Context context, Bus bus, Log log) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.bus = bus;
        this.log = log;
    }

    @Override
    public void start() {
        Location previousLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.postLocation(previousLocation);

        this.startListeningForUpdates();
    }

    @Override
    public void stop() {
        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.postLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        this.log.v("Provider changed: " + provider + " - Status: " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        this.startListeningForUpdates();
    }

    @Override
    public void onProviderDisabled(String provider) {
        this.stop();
    }

    private void postLocation(Location location) {
        if (location != null) {
            this.bus.post(new LocationEvent(location.getLongitude(), location.getLatitude()));
        } else {
            this.log.d("Location was null");
        }
    }

    private void startListeningForUpdates() {
        if (this.isGpsEnabled()) {
            this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0f, this);
        } else {
            this.bus.post(new GpsDisabledEvent());
        }
    }

    private boolean isGpsEnabled() {
        return this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
