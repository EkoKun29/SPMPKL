package com.example.spmpkl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.spmpkl.databinding.PelapActivityPelapBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spmpkl.databinding.PelapActivityPelapBinding;

public class PelapActivity extends AppCompatActivity {

    TextView text_name, text_npm;


    private AppBarConfiguration mAppBarConfiguration;
    private PelapActivityPelapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PelapActivityPelapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPelap.toolbar);
       /* binding.appBarPelap.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_nilai, R.id.nav_rekap, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_pelap);
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


        text_name.setText(m_name);
        text_npm.setText(m_username);


        /*    imageView.this.getIntent.getStringExtra(m_upload);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pelap, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_pelap);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item){
        startActivity(new Intent(PelapActivity.this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }
}