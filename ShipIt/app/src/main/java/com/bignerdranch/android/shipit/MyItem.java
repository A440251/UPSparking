package com.bignerdranch.android.shipit;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem  {

    private final LatLng mPosition;
    //private final String mTitle;
   // private final String mSnippet;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(40.752230, -73.979976);
   }

 /**   public MyItem (double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    public LatLng getPosition() {
        return mPosition;
    }
/**
    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
   **/

}
