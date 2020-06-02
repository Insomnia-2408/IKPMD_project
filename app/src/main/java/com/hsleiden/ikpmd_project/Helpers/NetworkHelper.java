package com.hsleiden.ikpmd_project.Helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkHelper {

    private Context context;
    private static NetworkHelper instance;

    public NetworkHelper(Context context){
        this.context = context;
    }

    public static synchronized NetworkHelper getHelper (Context ctx){
        if (instance == null){
            instance = new NetworkHelper(ctx);
        }
        return instance;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
