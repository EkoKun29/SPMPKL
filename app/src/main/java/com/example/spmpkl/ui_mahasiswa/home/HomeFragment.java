package com.example.spmpkl.ui_mahasiswa.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.spmpkl.R;
import com.example.spmpkl.RegisterActivity;
import com.example.spmpkl.UploadFotoActivity;
import com.example.spmpkl.databinding.MahasiswaFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
DatabaseReference reference;
ImageView image;
TextView text_name, text_npm, text_dosbing, text_lokasi ;



    private MahasiswaFragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = MahasiswaFragmentHomeBinding.inflate(inflater, container, false);




        text_name = binding.textName;
        text_npm =   binding.textNpm;
        text_dosbing = binding.textDosbing;
        text_lokasi = binding.textLokasi;
        image = binding.image;

        String m_username = getActivity().getIntent().getStringExtra("Username");
        String m_name = getActivity().getIntent().getStringExtra("Name");
        String m_dosbing = getActivity().getIntent().getStringExtra("Dosbing");
        String m_lokasi = getActivity().getIntent().getStringExtra("Alamat");

        String img = this.getActivity().getIntent().getStringExtra("Url");
        if(img != null) {
            // cek kalo urinya ada tinggal load gambarnya
            Picasso.get().load(img).into(image);
        }


        text_name.setText(m_name);
        text_npm.setText(m_username);
        text_dosbing.setText(m_dosbing);
        text_lokasi.setText(m_lokasi);

        View view = binding.getRoot();



       // gettingData();
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;


    }
   /* private void gettingData() {


        View view = binding.navView.();





    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}