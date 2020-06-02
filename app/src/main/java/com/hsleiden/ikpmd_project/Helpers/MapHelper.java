package com.hsleiden.ikpmd_project.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.ktopencage.OpenCageGeoCoder;
import com.ktopencage.ResponseException;
import com.ktopencage.model.OpenCageRequest;
import com.ktopencage.model.OpenCageResponse;
import com.ktopencage.model.OpenCageResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapHelper extends AsyncTask<String, Void, List<LatLng>> {

    private static OpenCageGeoCoder openCageGeocoder;

    public MapHelper() {
    }

    @Override
    protected List<LatLng> doInBackground(String... strings) {

        List<LatLng> latLongs = new ArrayList<LatLng>();

        for(String adress : strings) {
            OpenCageRequest request = new OpenCageRequest(adress);
            request.setRestrictToCountryCode("NL");

            OpenCageResponse response = null;

            try {
                response = openCageGeocoder.handleRequest(request);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ResponseException e) {
                e.printStackTrace();
            }

            OpenCageResult result = Objects.requireNonNull(response).getResults().get(0);

            LatLng latLng = new LatLng(result.getGeometry().getLat(), result.getGeometry().getLng());
            Log.d("Result", String.valueOf(latLng));
            latLongs.add(latLng);
        }
        return latLongs;
    }

    public void initializeMaps() {
        openCageGeocoder = new OpenCageGeoCoder("8435298977184b45bd1157a149b04c86");
    }

}
