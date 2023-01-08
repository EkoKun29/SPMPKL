package com.example.spmpkl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.spmpkl.databinding.AdminActivityAdminBinding;
import com.example.spmpkl.databinding.MahasiswaActivityMahasiswaBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.spmpkl.databinding.AdminActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    TextView Name;
    private AdminActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_nilai, R.id.navigation_rekap, R.id.navigation_logout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        gettingData();

    }
    private void gettingData() {
        Intent intent = getIntent();
        String m_name =intent.getStringExtra("Name");
        Name = (TextView) findViewById(R.id.text_name);
        Name.setText(m_name);
    }
    public void logout(MenuItem item){
        startActivity(new Intent(AdminActivity.this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }
}