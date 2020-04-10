package com.hsleiden.ikpmd_project.Helpers;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hsleiden.ikpmd_project.Models.Route;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    public static void addRoute(Route route) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> routeMap = new HashMap<>();
        routeMap.put(DatabaseInfo.RouteColumn.START, route.getStart());
        routeMap.put(DatabaseInfo.RouteColumn.END, route.getEnd());
        routeMap.put(DatabaseInfo.RouteColumn.STARTLATNG, route.getStartLatLng());
        routeMap.put(DatabaseInfo.RouteColumn.ENDLATNG, route.getEndLatLng());

        db.collection("routes")
                .add(routeMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DocumentSnapshot added with id: ", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", "Error adding document", e );
                    }
                });

    }

}
