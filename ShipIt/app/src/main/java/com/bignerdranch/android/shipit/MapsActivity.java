package com.bignerdranch.android.shipit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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




        LatLng nyc = new LatLng(40.752220, -73.979976);
        LatLng views = new LatLng(40.754344,-73.981207);

       // mMap.addMarker(new MarkerOptions().position(nyc).title("Route A to B "));
       // LatLng destination = new LatLng(40.7536510007, -73.9790731694);
        mMap.addMarker(new MarkerOptions().position(nyc).title("Route A to B ").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));
       // mMap.addPolyline(new PolylineOptions().add(nyc, destination).width(10).color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(views,16));
        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        addItems();

        LatLng leg1 = new LatLng(40.7521408, -73.9797825);
        mMap.addPolyline(new PolylineOptions().add(nyc, leg1).width(10).color(Color.RED));

        LatLng leg2 = new LatLng(40.7534871, -73.9787662);
        mMap.addPolyline(new PolylineOptions().add(leg1, leg2).width(10).color(Color.RED));


        LatLng leg3 = new LatLng(40.75357109999999, -73.9789856);
        mMap.addPolyline(new PolylineOptions().add(leg2, leg3).width(10).color(Color.RED));


        LatLng leg4 = new LatLng(40.7544636, -73.9811194);
        mMap.addPolyline(new PolylineOptions().add(leg3, leg4).width(10).color(Color.RED));


        LatLng leg5 = new LatLng(40.7555096, -73.98361179999999);
        mMap.addPolyline(new PolylineOptions().add(leg4, leg5).width(10).color(Color.RED));


        LatLng leg6 = new LatLng(40.7573938, -73.98225029999999);
        mMap.addPolyline(new PolylineOptions().add(leg5, leg6).width(10).color(Color.RED));


        LatLng leg7 = new LatLng(40.7569175, -73.98112180000001);
        mMap.addPolyline(new PolylineOptions().add(leg6, leg7).width(10).color(Color.RED));


    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
     //   double lat = 40.752230;
       // double lng = -73.979976;
        //LatLng package_1= new LatLng(40.753692, -73.978897);
      //  mMap.addMarker(new MarkerOptions().position(package_1).title("Package 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_2= new LatLng(40.753484, -73.978770);
        mMap.addMarker(new MarkerOptions().position(package_2).title("Package 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_3= new LatLng(40.754344, -73.981207);
        mMap.addMarker(new MarkerOptions().position(package_3).title("Package 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_4= new LatLng(40.756831, -73.981185);
        mMap.addMarker(new MarkerOptions().position(package_4).title("Package 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));




        /**
        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 1; i < 4; i++) {
            double offset = i / 800d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng,"hello","word");
            mClusterManager.addItem(offsetItem);
        }

         **/
    }
/**
   public void setUpClusterer() { {
        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, mMap);

        // Point the map's listeners at the listeners implemented b
        // y the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }
       /**
    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 51.5145160;
        double lng = -0.1270060;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }



/**
    @Override
    public double getDistanceInfo(double lat1, double lng1, String destinationAddress) {
        StringBuilder stringBuilder = new StringBuilder();
        Double dist = 0.0;
        try {

            destinationAddress = destinationAddress.replaceAll(" ","%20");

            //String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + latFrom + "," + lngFrom + "&destination=" + latTo + "," + lngTo + "&mode=driving&sensor=false";
            String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + 40 + "," + -73 + "&destination=" + 40.7536510007 + "," + -73.9790731694 + "&mode=driving&sensor=false";

            HttpPost httppost = new HttpPost(url);

            HttpClient client = new DefaultHttpClient();
            HttpResponse response;
            stringBuilder = new StringBuilder();


            response = client.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {

                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject = new JSONObject(stringBuilder.toString());

            JSONArray array = jsonObject.getJSONArray("routes");

            JSONObject routes = array.getJSONObject(0);

            JSONArray legs = routes.getJSONArray("legs");

            JSONObject steps = legs.getJSONObject(0);
d

            Log.i("Distance", distance.toString());
            dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dist;
    }

 **/
}
