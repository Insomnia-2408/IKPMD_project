package com.hsleiden.ikpmd_project;

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

public class MapHelper extends IntentService {

    protected ResultReceiver resultReceiver;
    protected Geocoder geocoder;

    public MapHelper(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        geocoder = new Geocoder(this, Locale.getDefault());

        resultReceiver = intent.getParcelableExtra("RECEIVER");
        int fetchType = intent.getIntExtra("FETCH_TYPE_EXTRA", 0);
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

}
