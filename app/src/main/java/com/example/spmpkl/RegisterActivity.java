package com.example.spmpkl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.type.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://spmpkl-567fc-default-rtdb.firebaseio.com/");
    private StorageReference storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://spmpkl-567fc.appspot.com");

    static final String imgIntent = "image";
    static final String latIntent = "lat";
    static final String lngIntent = "lng";

    String img = "";
    Double lat = 0.0;
    Double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        img = this.getIntent().getStringExtra(imgIntent);
        lat = this.getIntent().getDoubleExtra(latIntent, 0.0);
        lng = this.getIntent().getDoubleExtra(lngIntent, 0.0);

        final EditText Name = findViewById(R.id.name);
        final EditText Alamat = findViewById(R.id.apkl);
        final EditText Dosbing = findViewById(R.id.dp);
        final EditText Username = findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);
        final EditText PasswordConf = findViewById(R.id.password_conf);
        final Button btnRegister = findViewById(R.id.btn_register);
        final Button btnLok = findViewById(R.id.btnlok);
        final TextView btnLogin = findViewById(R.id.btn_login);
        final EditText Url = findViewById(R.id.url);
        final ImageView image = findViewById(R.id.imageView);

        btnLok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(RegisterActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent = new Intent(RegisterActivity.this, MapsActivity.class);
                                intent.putExtra(imgIntent, img);
                                intent.putExtra(latIntent, lat);
                                intent.putExtra(lngIntent, lng);
                                startActivity(intent);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setTitle("Permission Denied")
                                            .setMessage("Permission to acces device location is permanenly denied. you need to go to setting to allow the permission.")
                                            .setNegativeButton("Cancel", null)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));

                                                }

                                                private String getPackageName() {
                                                    return null;
                                                }


                                            }).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                            }
                        }).check();

            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, UploadFotoActivity.class);
                intent.putExtra(imgIntent, img);
                intent.putExtra(latIntent, lat);
                intent.putExtra(lngIntent, lng);
                startActivity(intent);

            }
        });

        //String longlat = this.getIntent().getStringExtra("locat");
        if (lat != 0.0 && lng != 0.0) {
            Geocoder geo = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geo.getFromLocation(lat, lng, 1);
                Address address = addresses.get(0);
                Alamat.setText(address.getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Tak suruh kirim uri fotonya dari UploadFotoActivity lewat intent
        // tu buat diambil dan diload disini (RegisterActivity)
        if (img != null) {
            // cek kalo urinya ada tinggal load gambarnya
            Picasso.get().load(img).into(image);
            Url.setText(img);
        }

        /*reload();*/
        /*gettingData();*/

        sharedPreferences = getSharedPreferences("users", 0);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UrlTxt = Url.getText().toString();
                final String NameTxt = Name.getText().toString();
                final String AlamatTxt = Alamat.getText().toString();
                final String DosbingTxt = Dosbing.getText().toString();
                final String UsernameTxt = Username.getText().toString();
                final String PasswordTxt = Password.getText().toString();
                final String PasswordConfTxt = PasswordConf.getText().toString();


                if (NameTxt.isEmpty() || AlamatTxt.isEmpty() || DosbingTxt.isEmpty() || UsernameTxt.isEmpty() || PasswordTxt.isEmpty() || PasswordConfTxt.isEmpty() || UrlTxt.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Silahkan Isi Semua Data", Toast.LENGTH_SHORT).show();

                } else if (!PasswordTxt.equals(PasswordConfTxt)) {
                    Toast.makeText(RegisterActivity.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(UsernameTxt)) {

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("Name", NameTxt);
                                editor.putString("Alamat", AlamatTxt);
                                editor.putString("Dosbing", DosbingTxt);
                                editor.putString("Username", UsernameTxt);
                                editor.putString("Password", PasswordTxt);
                                editor.putString("PasswordConf", PasswordConfTxt);

                                editor.putString("Lat", lat.toString());
                                editor.putString("Long", lng.toString());


                                editor.apply();
                                Toast.makeText(RegisterActivity.this, "Username Sudah Di Registrasi", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("users").child(UsernameTxt).child("Url").setValue(UrlTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Name").setValue(NameTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Alamat").setValue(AlamatTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Dosbing").setValue(DosbingTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Username").setValue(UsernameTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Password").setValue(PasswordTxt);

                                databaseReference.child("users").child(UsernameTxt).child("Lat").setValue(lat.toString());
                                databaseReference.child("users").child(UsernameTxt).child("Long").setValue(lng.toString());


                                Toast.makeText(RegisterActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }







    private void reload(){

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}