package com.hsleiden.ikpmd_project;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hsleiden.ikpmd_project.Helpers.MapHelper;
import com.hsleiden.ikpmd_project.Helpers.PopupHelper;

public class RouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapHelper mapHelper;
    private PopupHelper popup;
    private View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.popup = new PopupHelper(getSystemService(LAYOUT_INFLATER_SERVICE));
        this.mapHelper = new MapHelper("Map");

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Move the camera
        LatLng amsterdam = new LatLng(52, 5);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(amsterdam));

        setUpPopup();

    }

    public void setUpPopup() {
        this.popupView = this.popup.showRoutePopup(getWindow().getDecorView().getRootView());
        final EditText location = (EditText) popupView.findViewById(R.id.location);

        Button showOnMap = (Button) popupView.findViewById(R.id.showOnMap);
        showOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = location.getText().toString();
                updateMap(locationText);
            }
        });
    }

    private void updateMap(String locationText) {
        LatLng coordinates = mapHelper.getLocation(locationText);
        if(coordinates != null) {
            mMap.addMarker(new MarkerOptions()).setPosition(coordinates);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
        } else {
            Log.d("Error", "Location not found");
        }
    }
}
