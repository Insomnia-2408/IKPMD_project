package com.hsleiden.ikpmd_project.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
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
    private static JOpenCageGeocoder jOpenCageGeocoder;

    public MapHelper() {
    }

    public static LatLng getLocation(String locationText) throws InterruptedException, ApiException, IOException {

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(locationText);
        request.setRestrictToCountryCode("NL");

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng result = response.getFirstPosition();

        Log.d("Result", result.toString());

//        GeocodingApiRequest request = GeocodingApi.geocode(geoApiContext, locationText);
//
//        Log.d("response", Arrays.toString(request.await()));

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
        jOpenCageGeocoder = new JOpenCageGeocoder("8435298977184b45bd1157a149b04c86");
    }

}
