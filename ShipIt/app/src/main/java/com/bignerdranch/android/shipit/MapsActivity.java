package com.bignerdranch.android.shipit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Add a marker in Sydney and move the camera

        LatLng origin = new LatLng(40.751508, -73.978373);
        mMap.addMarker(new MarkerOptions().position(origin).title("origin"));

        //LatLng destination = new LatLng(40.7536510007, -73.9790731694);

       // mMap.addPolyline(new PolylineOptions().add(nyc, destination).width(10).color(Color.RED));

        LatLng dest = new LatLng(40.753692, -73.978897);
        mMap.addPolyline(new PolylineOptions().add(origin, dest).width(10).color(Color.RED));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 16));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40, -73), 12.0f));

    }




//    @Override
//    public double getDistanceInfo(double lat1, double lng1, String destinationAddress) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Double dist = 0.0;
//        try {
//
//            destinationAddress = destinationAddress.replaceAll(" ","%20");
//
//            //String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + latFrom + "," + lngFrom + "&destination=" + latTo + "," + lngTo + "&mode=driving&sensor=false";
//            String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + 40 + "," + -73 + "&destination=" + 40.7536510007 + "," + -73.9790731694 + "&mode=driving&sensor=false";
//
//            HttpPost httppost = new HttpPost(url);
//
//            HttpClient client = new DefaultHttpClient();
//            HttpResponse response;
//            stringBuilder = new StringBuilder();
//
//
//            response = client.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            InputStream stream = entity.getContent();
//            int b;
//            while ((b = stream.read()) != -1) {
//
//                stringBuilder.append((char) b);
//            }
//        } catch (ClientProtocolException e) {
//        } catch (IOException e) {
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//
//            jsonObject = new JSONObject(stringBuilder.toString());
//
//            JSONArray array = jsonObject.getJSONArray("routes");
//
//            JSONObject routes = array.getJSONObject(0);
//
//            JSONArray legs = routes.getJSONArray("legs");
//
//            JSONObject steps = legs.getJSONObject(0);
//
//            JSONObject distance = steps.getJSONObject("distance");
//
//            Log.i("Distance", distance.toString());
//            dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );
//
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return dist;
//    }


}
