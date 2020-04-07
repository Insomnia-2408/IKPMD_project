package com.hsleiden.ikpmd_project.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.GeolocationApiRequest;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapHelper {

    private static GeoApiContext geoApiContext;

    public MapHelper() {
    }

    public static LatLng getLocation(String locationText) throws InterruptedException, ApiException, IOException {

        GeocodingApiRequest request = GeocodingApi.geocode(geoApiContext, locationText);

        Log.d("response", Arrays.toString(request.await()));

//        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//
//        LatLng location = null;
//        try {
//            List<Address> locations = geocoder.getFromLocationName(locationText, 1);
//            Log.d("addresses", locations.get(0).toString());
//            location = new LatLng(locations.get(0).getLatitude(), locations.get(0).getLongitude());
//        } catch (IOException e) {
//            Log.d("Error", e.getMessage());
//        }
//        return location;

        return null;

    }

    public static void initializeMaps() {
//        Log.d("KEY", System.getProperty("GOOGLE_MAPS_API_KEY"));
        geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyBlCCExAkd_rpY582UmrCOdhfyyHXGY4yo").build();
    }

}
