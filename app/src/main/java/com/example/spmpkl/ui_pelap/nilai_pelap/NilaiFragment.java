package com.example.spmpkl.ui_pelap.nilai_pelap;



import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.spmpkl.Common;
import com.example.spmpkl.DosenActivity;
import com.example.spmpkl.MahasiswaActivity;
import com.example.spmpkl.R;
import com.example.spmpkl.dataNilai;
import com.example.spmpkl.databinding.PelapFragmentNilaiBinding;
import com.google.ar.core.Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.model.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

public class NilaiFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users/18670056");


    private PelapFragmentNilaiBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pelap_fragment_nilai, container, false);

        EditText unsur1 = root.findViewById(R.id.u1);
        EditText unsur2 = root.findViewById(R.id.u2);
        EditText unsur3 = root.findViewById(R.id.u3);
        EditText unsur4 = root.findViewById(R.id.u4);
        EditText unsur5 = root.findViewById(R.id.u5);
        EditText unsur6 = root.findViewById(R.id.u6);
        EditText unsur7 = root.findViewById(R.id.u7);

        EditText keterangan1 = root.findViewById(R.id.k1);
        EditText keterangan2 = root.findViewById(R.id.k2);
        EditText keterangan3 = root.findViewById(R.id.k3);
        EditText keterangan4 = root.findViewById(R.id.k4);
        EditText keterangan5 = root.findViewById(R.id.k5);
        EditText keterangan6 = root.findViewById(R.id.k6);
        EditText keterangan7 = root.findViewById(R.id.k7);

        EditText nilai1 = root.findViewById(R.id.n1);
        EditText nilai2 = root.findViewById(R.id.n2);
        EditText nilai3 = root.findViewById(R.id.n3);
        EditText nilai4 = root.findViewById(R.id.n4);
        EditText nilai5 = root.findViewById(R.id.n5);
        EditText nilai6 = root.findViewById(R.id.n6);
        EditText nilai7 = root.findViewById(R.id.n7);

        Button btn_simpan = root.findViewById(R.id.simpan);

        nilai1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai1.getText().toString().length() !=0) {
                    int nilai_1 = Integer.parseInt(nilai1.getText().toString().trim());
                    if (nilai_1 <= 50) {
                        keterangan1.setText("Kurang Sekali");
                    }else if(nilai_1 >= 51 && nilai_1 <= 59){
                        keterangan1.setText("Kurang");
                    }else if (nilai_1 >=60 && nilai_1 <=69){
                        keterangan1.setText("Cukup");
                    }else if (nilai_1 >=70 && nilai_1 <=79) {
                        keterangan1.setText("Baik");
                    }else if (nilai_1 >=80 && nilai_1 <=100) {
                        keterangan1.setText("Baik Sekali");
                    }else{
                        keterangan1.setText("");
                    }
                }
            }
        });

        nilai2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai2.getText().toString().length() !=0) {
                    int nilai_2 = Integer.parseInt(nilai2.getText().toString().trim());
                    if (nilai_2 <= 50) {
                        keterangan2.setText("Kurang Sekali");
                    }else if(nilai_2 >= 51 && nilai_2 <= 59){
                        keterangan2.setText("Kurang");
                    }else if (nilai_2 >=60 && nilai_2 <=69){
                        keterangan2.setText("Cukup");
                    }else if (nilai_2 >=70 && nilai_2 <=79) {
                        keterangan2.setText("Baik");
                    }else if (nilai_2 >=80 && nilai_2 <=100) {
                        keterangan2.setText("Baik Sekali");
                    }else{
                        keterangan2.setText("");
                    }
                }
            }
        });

        nilai3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai3.getText().toString().length() !=0) {
                    int nilai_3 = Integer.parseInt(nilai3.getText().toString().trim());
                    if (nilai_3 <= 50) {
                        keterangan3.setText("Kurang Sekali");
                    }else if(nilai_3 >= 51 && nilai_3 <= 59){
                        keterangan3.setText("Kurang");
                    }else if (nilai_3 >=60 && nilai_3 <=69){
                        keterangan3.setText("Cukup");
                    }else if (nilai_3 >=70 && nilai_3 <=79) {
                        keterangan3.setText("Baik");
                    }else if (nilai_3 >=80 && nilai_3 <=100) {
                        keterangan3.setText("Baik Sekali");
                    }else{
                        keterangan3.setText("");
                    }
                }
            }
        });

        nilai4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai4.getText().toString().length() !=0) {
                    int nilai_4 = Integer.parseInt(nilai4.getText().toString().trim());
                    if (nilai_4 <= 50) {
                        keterangan4.setText("Kurang Sekali");
                    }else if(nilai_4 >= 51 && nilai_4 <= 59){
                        keterangan4.setText("Kurang");
                    }else if (nilai_4 >=60 && nilai_4 <=69){
                        keterangan4.setText("Cukup");
                    }else if (nilai_4 >=70 && nilai_4 <=79) {
                        keterangan4.setText("Baik");
                    }else if (nilai_4 >=80 && nilai_4 <=100) {
                        keterangan4.setText("Baik Sekali");
                    }else{
                        keterangan4.setText("");
                    }
                }
            }
        });

        nilai5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai5.getText().toString().length() !=0) {
                    int nilai_5 = Integer.parseInt(nilai5.getText().toString().trim());
                    if (nilai_5 <= 50) {
                        keterangan5.setText("Kurang Sekali");
                    }else if(nilai_5 >= 51 && nilai_5 <= 59){
                        keterangan5.setText("Kurang");
                    }else if (nilai_5 >=60 && nilai_5 <=69){
                        keterangan5.setText("Cukup");
                    }else if (nilai_5 >=70 && nilai_5 <=79) {
                        keterangan5.setText("Baik");
                    }else if (nilai_5 >=80 && nilai_5 <=100) {
                        keterangan5.setText("Baik Sekali");
                    }else{
                        keterangan5.setText("");
                    }
                }
            }
        });

        nilai6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai6.getText().toString().length() !=0) {
                    int nilai_6 = Integer.parseInt(nilai6.getText().toString().trim());
                    if (nilai_6 <= 50) {
                        keterangan6.setText("Kurang Sekali");
                    }else if(nilai_6 >= 51 && nilai_6 <= 59){
                        keterangan6.setText("Kurang");
                    }else if (nilai_6 >=60 && nilai_6 <=69){
                        keterangan6.setText("Cukup");
                    }else if (nilai_6 >=70 && nilai_6 <=79) {
                        keterangan6.setText("Baik");
                    }else if (nilai_6 >=80 && nilai_6 <=100) {
                        keterangan6.setText("Baik Sekali");
                    }else{
                        keterangan6.setText("");
                    }
                }
            }
        });

        nilai7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable S) {
                if(nilai7.getText().toString().length() !=0) {
                    int nilai_7 = Integer.parseInt(nilai7.getText().toString().trim());
                    if (nilai_7 <= 50) {
                        keterangan7.setText("Kurang Sekali");
                    }else if(nilai_7 >= 51 && nilai_7 <=59){
                        keterangan7.setText("Kurang");
                    }else if (nilai_7 >=60 && nilai_7 <=69){
                        keterangan7.setText("Cukup");
                    }else if (nilai_7 >=70 && nilai_7 <=79) {
                        keterangan7.setText("Baik");
                    }else if (nilai_7 >=80 && nilai_7 <=100) {
                        keterangan7.setText("Baik Sekali");
                    }else{
                        keterangan7.setText("");
                    }
                }
            }
        });



        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Unsur1 = unsur1.getText().toString();
                String Unsur2 = unsur2.getText().toString();
                String Unsur3 = unsur3.getText().toString();
                String Unsur4 = unsur4.getText().toString();
                String Unsur5 = unsur5.getText().toString();
                String Unsur6 = unsur6.getText().toString();
                String Unsur7 = unsur7.getText().toString();

                final String Nilai1 = nilai1.getText().toString();
                String Nilai2 = nilai2.getText().toString();
                String Nilai3 = nilai3.getText().toString();
                String Nilai4 = nilai4.getText().toString();
                String Nilai5 = nilai5.getText().toString();
                String Nilai6 = nilai6.getText().toString();
                String Nilai7 = nilai7.getText().toString();

                String Keterangan1 = keterangan1.getText().toString();
                String Keterangan2 = keterangan2.getText().toString();
                String Keterangan3 = keterangan3.getText().toString();
                String Keterangan4 = keterangan4.getText().toString();
                String Keterangan5 = keterangan5.getText().toString();
                String Keterangan6 = keterangan6.getText().toString();
                String Keterangan7 = keterangan7.getText().toString();

                if (Unsur1.isEmpty() || Unsur2.isEmpty() || Unsur3.isEmpty() || Unsur4.isEmpty() || Unsur5.isEmpty() || Unsur6.isEmpty() || Unsur7.isEmpty()
                        || Nilai1.isEmpty() || Nilai2.isEmpty() || Nilai3.isEmpty() || Nilai4.isEmpty() || Nilai5.isEmpty() || Nilai6.isEmpty() || Nilai7.isEmpty() ||
                        Keterangan1.isEmpty() || Keterangan2.isEmpty() || Keterangan3.isEmpty() || Keterangan4.isEmpty() || Keterangan5.isEmpty() || Keterangan6.isEmpty() || Keterangan7.isEmpty()) {
                    Toast.makeText(NilaiFragment.this.getActivity(), "Data Tidak Boleh Kosong!!", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            databaseReference.child("unsur1").setValue(Unsur1);
                            databaseReference.child("unsur2").setValue(Unsur2);
                            databaseReference.child("unsur3").setValue(Unsur3);
                            databaseReference.child("unsur4").setValue(Unsur4);
                            databaseReference.child("unsur5").setValue(Unsur5);
                            databaseReference.child("unsur6").setValue(Unsur6);
                            databaseReference.child("unsur7").setValue(Unsur7);

                            databaseReference.child("nilai1").setValue(Nilai1);
                            databaseReference.child("nilai2").setValue(Nilai2);
                            databaseReference.child("nilai3").setValue(Nilai3);
                            databaseReference.child("nilai4").setValue(Nilai4);
                            databaseReference.child("nilai5").setValue(Nilai5);
                            databaseReference.child("nilai6").setValue(Nilai6);
                            databaseReference.child("nilai7").setValue(Nilai7);

                            databaseReference.child("keterangan1").setValue(Keterangan1);
                            databaseReference.child("keterangan2").setValue(Keterangan2);
                            databaseReference.child("keterangan3").setValue(Keterangan3);
                            databaseReference.child("keterangan4").setValue(Keterangan4);
                            databaseReference.child("keterangan5").setValue(Keterangan5);
                            databaseReference.child("keterangan6").setValue(Keterangan6);
                            databaseReference.child("keterangan7").setValue(Keterangan7);


                            Toast.makeText(requireContext(), "Data Berhasil DiKirim", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }

        });







        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }


}