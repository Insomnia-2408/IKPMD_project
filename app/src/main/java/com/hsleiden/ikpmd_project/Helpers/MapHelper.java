package com.hsleiden.ikpmd_project.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapHelper {

    private Context context;

    public MapHelper(Context context) {
        this.context = context;
    }

    public LatLng getLocation(String locationText) {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        LatLng location = null;
        try {
            List<Address> locations = geocoder.getFromLocationName(locationText, 1);
            Log.d("addresses", locations.get(0).toString());
            location = new LatLng(locations.get(0).getLatitude(), locations.get(0).getLongitude());
        } catch (IOException e) {
            Log.d("Error", e.getMessage());
        }
        return location;

    }
}
