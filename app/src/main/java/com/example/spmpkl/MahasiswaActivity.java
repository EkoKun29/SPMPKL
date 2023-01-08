package com.example.spmpkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.spmpkl.databinding.MahasiswaNavHeaderMahasiswaBinding;
import com.example.spmpkl.ui_mahasiswa.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spmpkl.databinding.MahasiswaActivityMahasiswaBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class MahasiswaActivity extends AppCompatActivity {


    TextView text_name, text_npm;
    ImageView imageView;



    private AppBarConfiguration mAppBarConfiguration;
    private MahasiswaActivityMahasiswaBinding binding;


    private NavigationView nav_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MahasiswaActivityMahasiswaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        setSupportActionBar(binding.appBarMahasiswa.toolbar);
     /*   binding.appBarMahasiswa.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_presensi, R.id.nav_rekap, R.id.nav_bimbingan, R.id.nav_nilai, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_mahasiswa);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        gettingData();

    }

    private void gettingData() {
        Intent intent = getIntent();
        String m_username = intent.getStringExtra("Username");
        String m_name = intent.getStringExtra("Name");




        View header = binding.navView.getHeaderView(0);

        text_name = (TextView) header.findViewById(R.id.text_name);
        text_npm = (TextView)  header.findViewById(R.id.text_npm);

        imageView = header.findViewById(R.id.imageView);


        String img = this.getIntent().getStringExtra("Url");
        if(img != null) {
            // cek kalo urinya ada tinggal load gambarnya
            Picasso.get().load(img).into(imageView);
        }

        text_name.setText(m_name);
        text_npm.setText(m_username);




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mahasiswa, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_mahasiswa);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void logout(MenuItem item){
        startActivity(new Intent(MahasiswaActivity.this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }



}