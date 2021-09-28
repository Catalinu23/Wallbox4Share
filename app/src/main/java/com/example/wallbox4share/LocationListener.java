package com.example.wallbox4share;

import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;


public class LocationListener implements OnSuccessListener<Location> {

    private Location location;

    @Override
    public void onSuccess(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
