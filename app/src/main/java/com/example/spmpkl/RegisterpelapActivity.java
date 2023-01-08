package com.example.spmpkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterpelapActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://spmpkl-567fc-default-rtdb.firebaseio.com/");
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpelap);

        final EditText Name = findViewById(R.id.name);
        final EditText Ppkl1 = findViewById(R.id.ppkl1);
        final EditText Ppkl2 = findViewById(R.id.ppkl2);
        final EditText Ppkl3 = findViewById(R.id.ppkl3);
        final EditText Username = findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);
        final EditText PasswordConf = findViewById(R.id.password_conf);
        final Button btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("users", 0);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String NameTxt = Name.getText().toString();
                final String PpklTxt1 = Ppkl1.getText().toString();
                final String PpklTxt2 = Ppkl2.getText().toString();
                final String PpklTxt3 = Ppkl3.getText().toString();
                final String UsernameTxt = Username.getText().toString();
                final String PasswordTxt = Password.getText().toString();
                final String PasswordConfTxt = PasswordConf.getText().toString();

                if (NameTxt.isEmpty() || PpklTxt1.isEmpty() || PpklTxt2.isEmpty() || PpklTxt3.isEmpty() || UsernameTxt.isEmpty() || PasswordTxt.isEmpty() || PasswordConfTxt.isEmpty())  {
                    Toast.makeText(RegisterpelapActivity.this, "Silahkan Isi Semua Data", Toast.LENGTH_SHORT).show();

                } else if (!PasswordTxt.equals(PasswordConfTxt)) {
                    Toast.makeText(RegisterpelapActivity.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(UsernameTxt)) {

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("Name", NameTxt);
                                editor.putString("Ppkl1", PpklTxt1);
                                editor.putString("Ppkl2", PpklTxt2);
                                editor.putString("Ppkl3", PpklTxt3);
                                editor.putString("Username", UsernameTxt);
                                editor.putString("Password", PasswordTxt);
                                editor.putString("PasswordConf", PasswordConfTxt);

                                editor.apply();
                                Toast.makeText(RegisterpelapActivity.this, "Username Sudah Di Registrasi", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("users").child(UsernameTxt).child("Name").setValue(NameTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Ppkl1").setValue(PpklTxt1);
                                databaseReference.child("users").child(UsernameTxt).child("Ppkl2").setValue(PpklTxt2);
                                databaseReference.child("users").child(UsernameTxt).child("Ppkl3").setValue(PpklTxt3);
                                databaseReference.child("users").child(UsernameTxt).child("Username").setValue(UsernameTxt);
                                databaseReference.child("users").child(UsernameTxt).child("Password").setValue(PasswordTxt);


                                Toast.makeText(RegisterpelapActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterpelapActivity.this, LoginActivity.class);
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
    }

}
