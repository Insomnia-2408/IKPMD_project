package com.hsleiden.ikpmd_project;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hsleiden.ikpmd_project.Helpers.MapHelper;
import com.hsleiden.ikpmd_project.Helpers.PopupHelper;
import com.hsleiden.ikpmd_project.Models.Route;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapHelper mapHelper;
    private PopupHelper popup;
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.popup = new PopupHelper((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE), this);

        this.mapHelper = new MapHelper();
        this.mapHelper.initializeMaps();

        this.route = new Route();

    }

    private void createToolbar() {

        Button tryAgain = new Button(this);
        tryAgain.setText("Probeer opnieuw");
        tryAgain.setBackgroundColor(View.INVISIBLE);
        tryAgain.setGravity(Gravity.LEFT);
        tryAgain.setTextColor(Color.WHITE);

        tryAgain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setUpPopup();
                    recreate();
                }

            }
        });

        Button succes = new Button(this);
        succes.setText("Ok");
        succes.setBackgroundColor(View.INVISIBLE);
        succes.setGravity(Gravity.RIGHT);
        succes.setTextColor(Color.WHITE);

        succes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              Log.d("Succes", "true");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toolbar toolbar = new Toolbar(this);
            toolbar.setBackgroundColor(Color.BLACK);
            toolbar.addView(tryAgain, 0);
            toolbar.addView(succes, 1);
            addContentView(toolbar, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }

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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(amsterdam, 7.5f));

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
                String currentLocationText = currentLocation.getText().toString();
                String destinationText = destination.getText().toString();
                try {
                    processData(currentLocationText, destinationText);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
    }

    private void processData(String currentLocationText, String destinationText) throws InterruptedException {

        createToolbar();

        this.route.setStart(currentLocationText);
        this.route.setEnd(destinationText);

        addMarkers(currentLocationText, destinationText);

    }

    private void addMarkers(String currentLocation, String destination) {

        AsyncTask<String, Void, List<LatLng>> result = null;

        result = this.mapHelper.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, currentLocation, destination);

        LatLng locCurrent = null;
        LatLng locDest = null;
        try {
            List<LatLng> results = result.get();
            locCurrent = results.get(0);
            locDest = results.get(1);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(locCurrent != null && locDest != null) {

            this.route.setStartLatLng(locCurrent);
            this.route.setEndLatLng(locDest);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(locCurrent);
            mMap.addMarker(markerOptions);
            markerOptions.position(locDest);
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locDest, 10));

        } else {
            Toast toast = new Toast(this);
            toast.setText("Er ging iets mis, probeer later opnieuw");
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            Log.d("Error", "Location not found");
        }

    }

}
