package com.example.spmpkl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://spmpkl-567fc-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText Username = findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);
        final Button btnLogin= findViewById(R.id.btn_login);
        final TextView btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("users",0);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usernametxt = Username.getText().toString();
                final String passwordtxt = Password.getText().toString();






                if(usernametxt.isEmpty() || passwordtxt.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Masukkan Username Atau Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)) {

                                final String getPassword = snapshot.child(usernametxt).child("Password").getValue(String.class);
                                final String getname = snapshot.child(usernametxt).child("Name").getValue(String.class);
                                if (getPassword.equals(passwordtxt)) {

                                    Toast.makeText(LoginActivity.this, "Login Sukses "+ getname, Toast.LENGTH_SHORT).show();

                                    if (usernametxt.length()==8){
                                        final String name = snapshot.child(usernametxt).child("Name").getValue(String.class);
                                        final String username = snapshot.child(usernametxt).child("Username").getValue(String.class);
                                        final String alamat = snapshot.child(usernametxt).child("Alamat").getValue(String.class);
                                        final String dosbing = snapshot.child(usernametxt).child("Dosbing").getValue(String.class);
                                        final String url = snapshot.child(usernametxt).child("Url").getValue(String.class);
                                        final String lat = snapshot.child(usernametxt).child("Lat").getValue(String.class);
                                        final String lng = snapshot.child(usernametxt).child("Long").getValue(String.class);

                                        final String Unsur1 = snapshot.child(usernametxt).child("unsur1").getValue(String.class);
                                        final String Unsur2 = snapshot.child(usernametxt).child("unsur2").getValue(String.class);
                                        final String Unsur3 = snapshot.child(usernametxt).child("unsur3").getValue(String.class);
                                        final String Unsur4 = snapshot.child(usernametxt).child("unsur4").getValue(String.class);
                                        final String Unsur5 = snapshot.child(usernametxt).child("unsur5").getValue(String.class);
                                        final String Unsur7 = snapshot.child(usernametxt).child("unsur7").getValue(String.class);
                                        final String Unsur6 = snapshot.child(usernametxt).child("unsur6").getValue(String.class);

                                        final String Nilai1 = snapshot.child(usernametxt).child("nilai1").getValue(String.class);
                                        final String Nilai2 = snapshot.child(usernametxt).child("nilai2").getValue(String.class);
                                        final String Nilai3 = snapshot.child(usernametxt).child("nilai3").getValue(String.class);
                                        final String Nilai4 = snapshot.child(usernametxt).child("nilai4").getValue(String.class);
                                        final String Nilai5 = snapshot.child(usernametxt).child("nilai5").getValue(String.class);
                                        final String Nilai6 = snapshot.child(usernametxt).child("nilai6").getValue(String.class);
                                        final String Nilai7 = snapshot.child(usernametxt).child("nilai7").getValue(String.class);

                                        final String Keterangan1 = snapshot.child(usernametxt).child("keterangan1").getValue(String.class);
                                        final String Keterangan2 = snapshot.child(usernametxt).child("keterangan2").getValue(String.class);
                                        final String Keterangan4 = snapshot.child(usernametxt).child("keterangan4").getValue(String.class);
                                        final String Keterangan3 = snapshot.child(usernametxt).child("keterangan3").getValue(String.class);
                                        final String Keterangan5 = snapshot.child(usernametxt).child("keterangan5").getValue(String.class);
                                        final String Keterangan6 = snapshot.child(usernametxt).child("keterangan6").getValue(String.class);
                                        final String Keterangan7 = snapshot.child(usernametxt).child("keterangan7").getValue(String.class);

                                        Intent intent = new Intent(LoginActivity.this,MahasiswaActivity.class);
                                        intent.putExtra("Name", name);
                                        intent.putExtra("Username", username);
                                        intent.putExtra("Alamat", alamat);
                                        intent.putExtra("Dosbing", dosbing);
                                        intent.putExtra("Url", url);
                                        intent.putExtra("Lat",lat);
                                        intent.putExtra("Long",lng);

                                        intent.putExtra("unsur1", Unsur1);
                                        intent.putExtra("unsur2", Unsur2);
                                        intent.putExtra("unsur3", Unsur3);
                                        intent.putExtra("unsur4", Unsur4);
                                        intent.putExtra("unsur5", Unsur5);
                                        intent.putExtra("unsur6", Unsur6);
                                        intent.putExtra("unsur7", Unsur7);

                                        intent.putExtra("nilai1", Nilai1);
                                        intent.putExtra("nilai2", Nilai2);
                                        intent.putExtra("nilai3", Nilai3);
                                        intent.putExtra("nilai4", Nilai4);
                                        intent.putExtra("nilai5", Nilai5);
                                        intent.putExtra("nilai6", Nilai6);
                                        intent.putExtra("nilai7", Nilai7);

                                        intent.putExtra("keterangan1", Keterangan1);
                                        intent.putExtra("keterangan2", Keterangan2);
                                        intent.putExtra("keterangan3", Keterangan3);
                                        intent.putExtra("keterangan4", Keterangan4);
                                        intent.putExtra("keterangan5", Keterangan5);
                                        intent.putExtra("keterangan6", Keterangan6);
                                        intent.putExtra("keterangan7", Keterangan7);
                                        startActivity(intent);
                                        finish();





                                    }else {
                                        if (usernametxt.length()==3) {
                                            final String name = snapshot.child(usernametxt).child("Name").getValue(String.class);
                                            final String mahasiswa1 = snapshot.child(usernametxt).child("Mahasiswa1").getValue(String.class);
                                            final String mahasiswa2 = snapshot.child(usernametxt).child("Mahasiswa2").getValue(String.class);
                                            final String mahasiswa3 = snapshot.child(usernametxt).child("Mahasiswa3").getValue(String.class);
                                            final String mahasiswa4 = snapshot.child(usernametxt).child("Mahasiswa4").getValue(String.class);
                                            final String mahasiswa5 = snapshot.child(usernametxt).child("Mahasiswa5").getValue(String.class);
                                            final String mahasiswa6 = snapshot.child(usernametxt).child("Mahasiswa6").getValue(String.class);
                                            final String mahasiswa7 = snapshot.child(usernametxt).child("Mahasiswa7").getValue(String.class);
                                            final String mahasiswa8 = snapshot.child(usernametxt).child("Mahasiswa8").getValue(String.class);
                                            final String mahasiswa9 = snapshot.child(usernametxt).child("Mahasiswa9").getValue(String.class);
                                            final String mahasiswa10 = snapshot.child(usernametxt).child("Mahasiswa10").getValue(String.class);

                                            Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                                            intent.putExtra("Name", name);
                                            intent.putExtra("Mahasiswa1", mahasiswa1);
                                            intent.putExtra("Mahasiswa2", mahasiswa2);
                                            intent.putExtra("Mahasiswa3", mahasiswa3);
                                            intent.putExtra("Mahasiswa4", mahasiswa4);
                                            intent.putExtra("Mahasiswa5", mahasiswa5);
                                            intent.putExtra("Mahasiswa6", mahasiswa6);
                                            intent.putExtra("Mahasiswa7", mahasiswa7);
                                            intent.putExtra("Mahasiswa8", mahasiswa8);
                                            intent.putExtra("Mahasiswa9", mahasiswa9);
                                            intent.putExtra("Mahasiswa10", mahasiswa10);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{

                                            if (usernametxt.length()==10){
                                                final String name = snapshot.child(usernametxt).child("Name").getValue(String.class);
                                                final String username = snapshot.child(usernametxt).child("Username").getValue(String.class);
                                                final String mahasiswa1 = snapshot.child(usernametxt).child("Mahasiswa1").getValue(String.class);
                                                final String mahasiswa2 = snapshot.child(usernametxt).child("Mahasiswa2").getValue(String.class);
                                                final String mahasiswa3 = snapshot.child(usernametxt).child("Mahasiswa3").getValue(String.class);
                                                final String mahasiswa4 = snapshot.child(usernametxt).child("Mahasiswa4").getValue(String.class);
                                                final String mahasiswa5 = snapshot.child(usernametxt).child("Mahasiswa5").getValue(String.class);
                                                final String mahasiswa6 = snapshot.child(usernametxt).child("Mahasiswa6").getValue(String.class);
                                                final String mahasiswa7 = snapshot.child(usernametxt).child("Mahasiswa7").getValue(String.class);
                                                final String mahasiswa8 = snapshot.child(usernametxt).child("Mahasiswa8").getValue(String.class);
                                                final String mahasiswa9 = snapshot.child(usernametxt).child("Mahasiswa9").getValue(String.class);
                                                final String mahasiswa10 = snapshot.child(usernametxt).child("Mahasiswa10").getValue(String.class);


                                                Intent intent = new Intent(LoginActivity.this,DosenActivity.class);
                                                intent.putExtra("Name", name);
                                                intent.putExtra("Username", username);
                                                intent.putExtra("Mahasiswa1", mahasiswa1);
                                                intent.putExtra("Mahasiswa2", mahasiswa2);
                                                intent.putExtra("Mahasiswa3", mahasiswa3);
                                                intent.putExtra("Mahasiswa4", mahasiswa4);
                                                intent.putExtra("Mahasiswa5", mahasiswa5);
                                                intent.putExtra("Mahasiswa6", mahasiswa6);
                                                intent.putExtra("Mahasiswa7", mahasiswa7);
                                                intent.putExtra("Mahasiswa8", mahasiswa8);
                                                intent.putExtra("Mahasiswa9", mahasiswa9);
                                                intent.putExtra("Mahasiswa10", mahasiswa10);

                                                startActivity(intent);
                                                finish();

                                            }else{
                                                final String name = snapshot.child(usernametxt).child("Name").getValue(String.class);
                                                final String username = snapshot.child(usernametxt).child("Username").getValue(String.class);
                                                final String ppkl1 = snapshot.child(usernametxt).child("Ppkl1").getValue(String.class);
                                                final String ppkl2 = snapshot.child(usernametxt).child("Ppkl2").getValue(String.class);
                                                final String ppkl3 = snapshot.child(usernametxt).child("Ppkl3").getValue(String.class);


                                                Intent intent = new Intent(LoginActivity.this,PelapActivity.class);
                                                intent.putExtra("Name", name);
                                                intent.putExtra("Username", username);
                                                intent.putExtra("Ppkl1", ppkl1);
                                                intent.putExtra("Ppkl2", ppkl2);
                                                intent.putExtra("Ppkl3", ppkl3);


                                                startActivity(intent);
                                                finish();

                                            }

                                        }


                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(LoginActivity.this, RegistergrupActivity.class));
            }
        });



    }
}