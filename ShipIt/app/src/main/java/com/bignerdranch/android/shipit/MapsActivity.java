package com.bignerdranch.android.shipit;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<MyItem> mClusterManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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


        LatLng nyc = new LatLng(40.752230, -73.979976);
        mMap.addMarker(new MarkerOptions().position(nyc).title("Route A to B "));
        LatLng destination = new LatLng(40.7536510007, -73.9790731694);
        mMap.addPolyline(new PolylineOptions().add(nyc, destination).width(10).color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nyc, 16));
        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        addItems();
        System.out.println(getJson());
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        //   double lat = 40.752230;
        // double lng = -73.979976;
        LatLng package_1 = new LatLng(40.753692, -73.978897);
        mMap.addMarker(new MarkerOptions().position(package_1).title("Package 1"));

        LatLng package_2 = new LatLng(40.754344, -73.981207);
        mMap.addMarker(new MarkerOptions().position(package_2).title("Package 2"));

        LatLng package_3 = new LatLng(40.754344, -73.981207);
        mMap.addMarker(new MarkerOptions().position(package_3).title("Package 3"));

        LatLng package_4 = new LatLng(40.756831, -73.981185);
        mMap.addMarker(new MarkerOptions().position(package_4).title("Package 4"));


    }

    private String getJson(){
        JSONObject json = {
}
