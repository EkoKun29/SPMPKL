package com.example.spmpkl.ui_mahasiswa.maps;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.spmpkl.R;
import com.example.spmpkl.databinding.MahasiswaMapsCobaBinding;
import com.example.spmpkl.ui_mahasiswa.presensi.PresensiFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsCoba extends Fragment implements OnMapReadyCallback {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://spmpkl-567fc-default-rtdb.firebaseio.com/");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int t1Hour, t2Minute;
    EditText Name, Date, Time, Keterang, New_L;
    Button simpan;
    RadioButton masuk, izin;
    RadioGroup radio;


    private GoogleMap mMap;
    private MapView mapView;
    private GeofencingClient geofencingClient;
    FusedLocationProviderClient client;
    float radius = 200 ;
    static final String lngIntent = "lng";
    static final String latIntent = "lat";
    Double lat = 0.0;
    Double lng = 0.0;
    private String id = " ";
    private LatLng latLng;



    private MahasiswaMapsCobaBinding binding;
    private ProgressDialog progressDialog;



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;

        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                String m_lat = getActivity().getIntent().getStringExtra("Lat");
                String m_lng = getActivity().getIntent().getStringExtra("Long");

                latLng = new LatLng(Double.parseDouble(m_lat), Double.parseDouble(m_lng));
                googleMap.addMarker(new MarkerOptions().position(latLng).title("PKL Location"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(19.0f));
                CircleOptions circleOptions = new CircleOptions();
//                circleOptions.center(longlat);
                circleOptions.center(latLng);
                circleOptions.radius(radius);
//                circleOptions.radius(radius);
                circleOptions.strokeColor(Color.argb(255, 255,0,0));
                circleOptions.fillColor(Color.argb(64, 255,0,0));
                circleOptions.strokeWidth(4);
                mMap.addCircle(circleOptions);

                Location location = task.getResult();
                LatLng longlat = new LatLng(location.getLatitude(),location.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(longlat).title("My Location"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(longlat));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(19.0f));
                lat = longlat.latitude;
                lng = longlat.longitude;



                double currentLatitude = Double.parseDouble(m_lat);
                double currentLongitude = Double.parseDouble(m_lng);

                double endLatitude = lat;
                double endLongitude = lng;
                float[] results = new float[200];
                Location.distanceBetween(currentLatitude, currentLongitude, endLatitude, endLongitude,results);


                BigDecimal bd = new BigDecimal(results[0]);
                BigDecimal rounded = bd.setScale(2, RoundingMode.HALF_UP);
                double values = rounded.doubleValue();
                if (values > 200) {
                    Toast.makeText(getActivity().getApplicationContext(), "Anda berada diluar Lokasi", Toast.LENGTH_SHORT).show();
                    masuk.setVisibility(View.GONE);
                    values = (values * 0.001f);// convert meters to Kilometers
                    bd = new BigDecimal(values);
                    rounded = bd.setScale(2, RoundingMode.HALF_UP);
                    values = rounded.doubleValue();
                }else{
                    masuk.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity().getApplicationContext(), "Anda berada di radius Lokasi", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }




    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MahasiswaMapsCobaBinding.inflate(inflater, container, false);

        Date = binding.Tanggal;
        Time = binding.Waktu;
        New_L = binding.NewL;
        Keterang = binding.Ket;
        mapView = binding.map;

        lat = this.getActivity().getIntent().getDoubleExtra(MapsCoba.latIntent, 0.0);
        lng = this.getActivity().getIntent().getDoubleExtra(MapsCoba.lngIntent, 0.0);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        geofencingClient = LocationServices.getGeofencingClient(getActivity());

        simpan = binding.btnSimpan;

        masuk = binding.btrM;
        izin = binding.btrI;
        radio = binding.radio;

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        progressDialog = new ProgressDialog(MapsCoba.this.getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan....");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MapsCoba.this.getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;

                        Date.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MapsCoba.this.getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t2Minute = minute;
                                String time = t1Hour + ":" + t2Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    java.util.Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    Time.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour, t2Minute);
                timePickerDialog.show();
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId== R.id.btr_m ) {
                    Keterang.setVisibility(View.GONE);
                    New_L.setVisibility(View.VISIBLE);

                }else if(checkedId==R.id.btr_i ) {

                    Keterang.setVisibility(View.VISIBLE);
                }

            }

        });
        simpan.setOnClickListener(v -> {
            if ( Name.getText().length() > 0 && Date.getText().length() > 0 && Time.getText().length() > 0 && Keterang.getText().length() > 0 && New_L.getText().length()>0 ) {
                saveData1(Name.getText().toString(), Date.getText().toString(), Time.getText().toString(), Keterang.getText().toString(), New_L.getText().toString());
            }else{
                if (Name.getText().length() > 0 && Date.getText().length() > 0 && Time.getText().length() > 0 && masuk.getText().length() > 0 && New_L.getText().length() > 0 ) {
                    saveData2(Name.getText().toString(), Date.getText().toString(), Time.getText().toString(),   masuk.getText().toString(), New_L.getText().toString());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Location Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = getActivity().getIntent();
        if(intent != null){
            id = intent.getStringExtra("id");
            Date.setText(intent.getStringExtra("date"));
            Time.setText(intent.getStringExtra("time"));
            Keterang.setText(intent.getStringExtra("ket"));
            New_L.setText(intent.getStringExtra("loc"));
        }


        getCurrentLocation();
        gettingData();
        View view = binding.getRoot();
        return view;

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){
        LocationManager locationManager = (LocationManager) getActivity().
                getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {


                    Location location = task.getResult();

                    if ( location != null) {
                        Geocoder geo = new Geocoder(requireContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            Address address = addresses.get(0);
                            New_L.setText(address.getAddressLine(0));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(1000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback =new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult){
                                Location location1 = locationResult.getLastLocation();
                                New_L.setText(String.valueOf(location1.getLatitude() + "/" +
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


    private void gettingData() {
        Intent intent = getActivity().getIntent();
        String m_name = intent.getStringExtra("Name");
        Name = binding.Nama;
        Name.setText(m_name);


    }

    private void saveData1 (String Name, String Date, String Time, String Izin, String NewL){
        Map<String, Object> user = new HashMap<>();

        user.put("Name", Name);
        user.put("Date", Date);
        user.put("Time", Time);
        user.put("Izin", Izin);
        user.put("Lokasi Terbaru", NewL);
        progressDialog.show();
        db.collection(Name)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getActivity().getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        replaceFragment(new PresensiFragment());
                        progressDialog.dismiss();

                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });

    }
    private void saveData2 ( String Name, String Date, String Time, String Masuk, String NewL){
        Map<String, Object> user = new HashMap<>();

        user.put("Name", Name);
        user.put("Date", Date);
        user.put("Time", Time);
        user.put("Keterangan", Masuk);
        user.put("Lokasi Terbaru", NewL);
        progressDialog.show();
        db.collection(Name)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getActivity().getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        replaceFragment(new PresensiFragment());
                        progressDialog.dismiss();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });


    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, fragment).commit();
        ft.addToBackStack(null);

    }
}
