package com.bignerdranch.android.shipit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

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
    String str;
    private Button button_radius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        button_radius = (RadioButton) findViewById(R.id.radioBack);
        button_radius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent =  new Intent (MapsActivity.this, ListActivity.class);
                startActivity(intent);

            }

        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng nyc = new LatLng(40.752220, -73.979976);
        LatLng views = new LatLng(40.754344, -73.981207);

        // mMap.addMarker(new MarkerOptions().position(nyc).title("Route A to B "));
        // LatLng destination = new LatLng(40.7536510007, -73.9790731694);
        mMap.addMarker(new MarkerOptions().position(nyc).title("Package 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));
        // mMap.addPolyline(new PolylineOptions().add(nyc, destination).width(10).color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(views, 16));
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

        addSpots();


    }


    private void addItems() {

        // Set some lat/lng coordinates to start with.
        //   double lat = 40.752230;
        // double lng = -73.979976;
        //LatLng package_1= new LatLng(40.753692, -73.978897);
        //  mMap.addMarker(new MarkerOptions().position(package_1).title("Package 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_2 = new LatLng(40.753484, -73.978770);
        mMap.addMarker(new MarkerOptions().position(package_2).title("Package 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_3 = new LatLng(40.754344, -73.981207);
        mMap.addMarker(new MarkerOptions().position(package_3).title("Package 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));

        LatLng package_4 = new LatLng(40.756831, -73.981185);
        mMap.addMarker(new MarkerOptions().position(package_4).title("Package 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.package_2)));


    }

    private void addSpots() {
        LatLng spot_1 = new LatLng(40.7536510007, -73.9790731694);
        mMap.addMarker(new MarkerOptions().position(spot_1).title("meter no: 1433288").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_2 = new LatLng(40.7535520359, -73.9790867782);
        mMap.addMarker(new MarkerOptions().position(spot_2).title("meter_no:1433287").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_3 = new LatLng(40.7537125871, -73.9794677963);
        mMap.addMarker(new MarkerOptions().position(spot_3).title("meter_no:1433290").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));
/** Second Spot*/
        LatLng spot_4 = new LatLng(40.7542823837, -73.9808423412);
        mMap.addMarker(new MarkerOptions().position(spot_4).title("meter no: 1433288").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_5 = new LatLng(40.7544099616, -73.9808676886);
        mMap.addMarker(new MarkerOptions().position(spot_5).title("meter_no:1433050").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_6 = new LatLng(40.7544847459, -73.9813176076);
        mMap.addMarker(new MarkerOptions().position(spot_6).title("meter_no:1433052").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));
/**   Third Spot      */
        LatLng spot_7 = new LatLng(40.7546812012, -73.9815139631);
        mMap.addMarker(new MarkerOptions().position(spot_7).title("meter_no:1433049").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_8 = new LatLng(40.7571032699, -73.981697671);
        mMap.addMarker(new MarkerOptions().position(spot_8).title("meter_no:1433027").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_9 = new LatLng(40.7568464016, -73.9810778427);
        mMap.addMarker(new MarkerOptions().position(spot_9).title("meter_no:1433050").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_10 = new LatLng(40.7569676287, -73.981117885);
        mMap.addMarker(new MarkerOptions().position(spot_10).title("meter_no:1433021").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));
/** First Spot */

        LatLng spot_11 = new LatLng(40.7522277202, -73.9800947458);
        mMap.addMarker(new MarkerOptions().position(spot_11).title("\n" +
                "meter_no:1443962").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));

        LatLng spot_12 = new LatLng(40.7525223346, -73.9795878724
        );
        mMap.addMarker(new MarkerOptions().position(spot_12).title("meter_no:1433287").icon(BitmapDescriptorFactory.fromResource(R.drawable.available)));


    }
/**

        public void onRadioButtonClicked() {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_checkedId == R.id.radioBack) {
                        Intent intent = new Intent(this, ListActivity.class);
                    }
                    else if (_checkedId == R.id.radioFlag) {
                        Intent intent = new Intent(this, ListActivity.class);
                        startActivity(intent);
                    }
                    else if (_checkedId == R.id.radioNext) {
                        Intent intent = new Intent(this, ListActivity.class);
                    }
                }
**/
                /**
                    boolean checked = ((RadioButton) view).isChecked();

                    switch(view.getId()) {
                        case R.id.radioBack:
                            if (checked) {
                                Intent intent = new Intent(this, ListActivity.class);
                                startActivity(intent);

                            }
                            break;
                        case R.id.radioFlag:
                            if (checked) {
                                Intent intent = new Intent(this, ListActivity.class);
                                startActivity(intent);

                            }
                            break;
                        case R.id.radioNext:
                            if (checked) {

                                Intent intent = new Intent(this, ListActivity.class);
                                startActivity(intent);

                            }
                                break;
                    }


        **/


    }



