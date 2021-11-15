package com.example.lab4;

import com.google.android.gms.maps.model.LatLng;

public class City {
    public String name;
    public double lat;
    public double lng;
    public LatLng latLng;

    public City(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.latLng = new LatLng(lat, lng);
    }

    public String toString() {
        return name;
    }
}
