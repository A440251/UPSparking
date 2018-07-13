package com.bignerdranch.android.shipit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

import static android.widget.Toast.LENGTH_LONG;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<MyItem> mClusterManager;
    private Button button_back;
    private Button button_next;
    private Button button_flag;
    private int incrementer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        button_back = (RadioButton) findViewById(R.id.radioBack);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent =  new Intent (MapsActivity.this, ListActivity.class);
                startActivity(intent);

            }

        });

        incrementer = 0;
        button_next= (RadioButton) findViewById(R.id.radioNext);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if(incrementer == 0){
                    LatLng zoom = new LatLng(40.753484, -73.978770);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 18));
                }
                if(incrementer == 1){
                    LatLng zoom = new LatLng(40.754344, -73.981207);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 18));
                }
                else if(incrementer == 2) {
                    LatLng zoom = new LatLng(40.756831, -73.981185);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 18));
                }
                incrementer++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                LatLng views = new LatLng(40.754344, -73.981207);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(views, 16));
                            }
                        },
                        5000);


            }

        });

        button_flag= (RadioButton) findViewById(R.id.radioFlag);
        button_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                LatLng zoom = new LatLng(40.753760, -73.979436);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 18));
                mMap.addMarker(new MarkerOptions().position(zoom).title("Parking Ticket Record").icon(BitmapDescriptorFactory.fromResource(R.drawable.ticket_master)));
                Toast.makeText(MapsActivity.this, "Your location has been recorded", LENGTH_LONG).show();


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

        Circle currentLoc = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.752220, -73.979976))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Circle nextLoc = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.7521408, -73.9797825))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc.setVisible(false);

        Circle nextLoc2 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.7528072, -73.97927019999997))
                .radius(15)
                .fillColor(Color.BLUE));
        nextLoc2.setVisible(false);

        Circle nextLoc3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.753484, -73.978770))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc3.setVisible(false);


        Circle nextLoc4 =  mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.75415199603125, -73.98040234749897))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc4.setVisible(false);

        Circle nextLoc5 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.754420, -73.981055))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc5.setVisible(false);

        Circle nextLoc6 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.75549347386774, -73.98359417622669))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc6.setVisible(false);

        Circle nextLoc7 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.7561183027969, -73.98314356511219))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc7.setVisible(false);

        Circle nextLoc8 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.75675297295136, -73.982698318416))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc8.setVisible(false);

        Circle nextLoc9 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.757378746314096, -73.98223161404741))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc9.setVisible(false);


        Circle nextLoc10 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.75706136569319, -73.98144274973754))
                .radius(15)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        nextLoc10.setVisible(false);


        Circle[] arr = {currentLoc,nextLoc,nextLoc2,nextLoc3,nextLoc4,nextLoc5,nextLoc6,nextLoc7,nextLoc8,nextLoc9,nextLoc10};
        moveCircle(arr,0,3000);
        moveCircle(arr, 1, 6000);
        moveCircle(arr, 2, 9000);
        moveCircle(arr,3,19000);
        moveCircle(arr,4,22000);
        moveCircle(arr,5,32000);
        moveCircle(arr,6,35000);
        moveCircle(arr,7,38000);
        moveCircle(arr,8,41000);
        moveCircle(arr,9,44000);



    }
    private void moveCircle(final Circle circle[], final int index, final int delay){
        new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            circle[index].setVisible(false);
                            circle[index + 1].setVisible(true);
                        }
                    },
                    delay);
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





}



