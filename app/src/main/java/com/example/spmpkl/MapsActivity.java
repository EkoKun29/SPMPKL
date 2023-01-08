package com.example.spmpkl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

   private EditText  locat;
    FusedLocationProviderClient client;
    float radius = 1000 ;
    private Button btnsimpan;
    private GoogleMap mMap;

    String img = "";
    Double lat = 0.0;
    Double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        img = this.getIntent().getStringExtra(RegisterActivity.imgIntent);
        lat = this.getIntent().getDoubleExtra(RegisterActivity.latIntent, 0.0);
        lng = this.getIntent().getDoubleExtra(RegisterActivity.lngIntent, 0.0);

        locat = findViewById(R.id.LongLat);
    btnsimpan = findViewById(R.id.btnSimpan);


            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = LocationServices.getFusedLocationProviderClient(this);




        getCurrentLocation();
    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location = task.getResult();

                    if (location != null){
                        locat.setText(String.valueOf(location.getLatitude()+ "/" +
                                location.getLongitude()));
                    }else{
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(1000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback =new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult){
                                Location location1 = locationResult.getLastLocation();
                                locat.setText(String.valueOf(location1.getLatitude() + "/" +
                                        location1.getLongitude()));

                            }
                        };
                        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                    }
                }
            });
        }else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                mMap = googleMap;
                Location location = task.getResult();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions options =new MarkerOptions().position(latLng).title("My Location");
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,19));
                mMap.addMarker(options);

                lat = latLng.latitude;
                lng = latLng.longitude;
                btnsimpan.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        Intent intent = new Intent(MapsActivity.this, RegisterActivity.class);
                        intent.putExtra(RegisterActivity.imgIntent, img);
                        intent.putExtra(RegisterActivity.latIntent, lat);
                        intent.putExtra(RegisterActivity.lngIntent, lng);
                        startActivity(intent);
                    }
                });
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(latLng);
                circleOptions.radius(radius);
                circleOptions.strokeColor(Color.argb(255, 255, 0, 0));
                circleOptions.fillColor(Color.argb(64, 255, 0, 0));
                circleOptions.strokeWidth(4);
                mMap.addCircle(circleOptions);

            }
        });
    }
}