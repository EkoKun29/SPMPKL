package com.example.spmpkl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistergrupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registergrup);

        final Button btnBim = findViewById(R.id.btn_bim);
        final Button btnMah = findViewById(R.id.btn_mah);

        btnBim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistergrupActivity.this, RegisterpelapActivity.class);
                startActivity(intent);
            }
        });

        btnMah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistergrupActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
