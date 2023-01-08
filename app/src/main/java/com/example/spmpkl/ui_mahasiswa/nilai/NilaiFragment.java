package com.example.spmpkl.ui_mahasiswa.nilai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.spmpkl.R;
import com.example.spmpkl.databinding.MahasiswaFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.MahasiswaFragmentNilaiBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NilaiFragment extends Fragment {
    TextView nomor1, unsur1, nilai1, keterangan1,
            nomor2, unsur2, nilai2, keterangan2,
            nomor3, unsur3, nilai3, keterangan3,
            nomor4, unsur4, nilai4, keterangan4,
            nomor5, unsur5, nilai5, keterangan5,
            nomor6, unsur6, nilai6, keterangan6,
            nomor7, unsur7, nilai7, keterangan7;

    Button btn_simpan, btn_cetak;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("rekapNilai");
    private MahasiswaFragmentNilaiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = MahasiswaFragmentNilaiBinding.inflate(inflater, container, false);










        View view = binding.getRoot();



        gettingData();
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;


    }
    private void gettingData(){
        Intent intent = getActivity().getIntent();
        /*String m_name = intent.getStringExtra("Name");
        databaseReference.child("rekapNilai").child(m_name).setValue(String.class);*/
        String Unsur1 = intent.getStringExtra("unsur1");
        String Unsur2 = intent.getStringExtra("unsur2");
        String Unsur3 = intent.getStringExtra("unsur3");
        String Unsur4 = intent.getStringExtra("unsur4");
        String Unsur5 = intent.getStringExtra("unsur5");
        String Unsur6 = intent.getStringExtra("unsur6");
        String Unsur7 = intent.getStringExtra("unsur7");

        String Nilai1 = intent.getStringExtra("nilai1");
        String Nilai2 = intent.getStringExtra("nilai2");
        String Nilai3 = intent.getStringExtra("nilai3");
        String Nilai4 = intent.getStringExtra("nilai4");
        String Nilai5 = intent.getStringExtra("nilai5");
        String Nilai6 = intent.getStringExtra("nilai6");
        String Nilai7 = intent.getStringExtra("nilai7");

        String Keterangan1 = intent.getStringExtra("keterangan1");
        String Keterangan2 = intent.getStringExtra("keterangan2");
        String Keterangan3 = intent.getStringExtra("keterangan3");
        String Keterangan4 = intent.getStringExtra("keterangan4");
        String Keterangan5 = intent.getStringExtra("keterangan5");
        String Keterangan6 = intent.getStringExtra("keterangan6");
        String Keterangan7 = intent.getStringExtra("keterangan7");

        nomor1 = binding.no1;
        unsur1 = binding.u1;
        nilai1 = binding.n1;
        keterangan1 = binding.k1;

        nomor2 = binding.no2;
        unsur2 = binding.u2;
        nilai2 = binding.n2;
        keterangan2 = binding.k2;

        nomor3 = binding.no3;
        unsur3 = binding.u3;
        nilai3 = binding.n3;
        keterangan3 = binding.k3;

        nomor4 = binding.no4;
        unsur4 = binding.u4;
        nilai4 = binding.n4;
        keterangan4 = binding.k4;

        nomor5 = binding.no5;
        unsur5 = binding.u5;
        nilai5 = binding.n5;
        keterangan5 = binding.k5;

        nomor6 = binding.no6;
        unsur6 = binding.u6;
        nilai6 = binding.n6;
        keterangan6 = binding.k6;

        nomor7 = binding.no7;
        unsur7 = binding.u7;
        nilai7 = binding.n7;
        keterangan7 = binding.k7;

        unsur1.setText(Unsur1);
        unsur2.setText(Unsur2);
        unsur3.setText(Unsur3);
        unsur4.setText(Unsur4);
        unsur5.setText(Unsur5);
        unsur6.setText(Unsur6);
        unsur7.setText(Unsur7);

        nilai1.setText(Nilai1);
        nilai2.setText(Nilai2);
        nilai3.setText(Nilai3);
        nilai4.setText(Nilai4);
        nilai5.setText(Nilai5);
        nilai6.setText(Nilai6);
        nilai7.setText(Nilai7);

        keterangan1.setText(Keterangan1);
        keterangan2.setText(Keterangan2);
        keterangan3.setText(Keterangan3);
        keterangan4.setText(Keterangan4);
        keterangan5.setText(Keterangan5);
        keterangan6.setText(Keterangan6);
        keterangan7.setText(Keterangan7);





    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}