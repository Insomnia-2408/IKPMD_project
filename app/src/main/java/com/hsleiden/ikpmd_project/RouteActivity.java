package com.hsleiden.ikpmd_project;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.errors.ApiException;
import com.hsleiden.ikpmd_project.Helpers.MapHelper;
import com.hsleiden.ikpmd_project.Helpers.MapHelperDepricated;
import com.hsleiden.ikpmd_project.Helpers.PopupHelper;

import java.io.IOException;
import java.util.Map;

public class RouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapHelper mapHelper;
    private PopupHelper popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.popup = new PopupHelper(getSystemService(LAYOUT_INFLATER_SERVICE), this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Move the camera
        LatLng amsterdam = new LatLng(52, 5);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(amsterdam));

        setUpPopup();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setUpPopup() {
        final Dialog dialog = this.popup.showPrompt(R.layout.popup_route);
        final EditText currentLocation = (EditText) dialog.findViewById(R.id.currentLocation);
        final EditText destination = (EditText) dialog.findViewById(R.id.destination);

        Button showOnMap = (Button) dialog.findViewById(R.id.showOnMap);
        showOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = currentLocation.getText().toString();
                String destinationText = destination.getText().toString();
                try {
                    updateMap(locationText);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
    }

    private void updateMap(String locationText) throws InterruptedException, ApiException, IOException {
        LatLng coordinates = MapHelper.getLocation(locationText);
        if(coordinates != null) {
            mMap.addMarker(new MarkerOptions()).setPosition(coordinates);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
        } else {
            Log.d("Error", "Location not found");
        }
    }

}
