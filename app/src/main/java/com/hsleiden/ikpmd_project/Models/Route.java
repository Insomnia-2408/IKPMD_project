package com.hsleiden.ikpmd_project.Models;

import com.google.android.gms.maps.model.LatLng;

public class Route {

    private String start;
    private LatLng startLatLng;
    private String end;
    private LatLng endLatLng;

    public Route() {

    }

    public Route(String start, LatLng startLatLng, String end, LatLng endLatLng) {
        this.start = start;
        this.startLatLng = startLatLng;
        this.end = end;
        this.endLatLng = endLatLng;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public LatLng getStartLatLng() {
        return startLatLng;
    }

    public void setStartLatLng(LatLng startLatLng) {
        this.startLatLng = startLatLng;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public LatLng getEndLatLng() {
        return endLatLng;
    }

    public void setEndLatLng(LatLng endLatLng) {
        this.endLatLng = endLatLng;
    }
}
