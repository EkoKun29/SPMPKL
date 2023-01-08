package com.example.spmpkl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spmpkl.databinding.DosenActivityDosenBinding;

public class DosenActivity extends AppCompatActivity {

    TextView text_name, text_npm;
    private AppBarConfiguration mAppBarConfiguration;
    private DosenActivityDosenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DosenActivityDosenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDosen.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_rekap, R.id.nav_nilai, R.id.nav_bimbingan, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dosen);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        gettingData();
    }
    private void gettingData() {
        Intent intent = getIntent();
        String m_username = intent.getStringExtra("Username");
        String m_name = intent.getStringExtra("Name");
        String m_dosbing = intent.getStringExtra("Dosbing");

        View header = binding.navView.getHeaderView(0);

        text_name = (TextView) header.findViewById(R.id.text_name);
        text_npm = (TextView)  header.findViewById(R.id.text_npm);

        text_name.setText(m_name);
        text_npm.setText(m_username);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dosen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dosen);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void logout(MenuItem item){
        startActivity(new Intent(DosenActivity.this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }
}