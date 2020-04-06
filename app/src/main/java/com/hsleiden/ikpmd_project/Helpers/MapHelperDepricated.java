package com.hsleiden.ikpmd_project.Helpers;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapHelperDepricated extends IntentService {

    protected ResultReceiver resultReceiver;
    protected Geocoder geocoder;

    public MapHelperDepricated() {
        super("Map");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        geocoder = new Geocoder(this, Locale.getDefault());

        resultReceiver = intent.getParcelableExtra("RECEIVER");
        int fetchType = intent.getIntExtra("FETCH_TYPE_EXTRA", 0);
//        Log.d("MAPHELPER", "onHandleIntent");

    }

    public LatLng getLocation(String address) {
        LatLng location = null;
        try {
            List<Address> locations = geocoder.getFromLocationName(address, 1);
            location = new LatLng(locations.get(0).getLatitude(), locations.get(0).getLongitude());
        } catch (IOException e) {
            Log.d("Error", e.getMessage());
        }
        return location;
    }

    public LatLng plsWork(String addressInput) {
        Geocoder gc = new Geocoder(this);
        LatLng location = null;
        if(gc.isPresent()){
            try {
                List<Address> list = gc.getFromLocationName(addressInput, 1);
                Address address = list.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                location = new LatLng(lat, lng);
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }
        }
        return location;
    }

}
