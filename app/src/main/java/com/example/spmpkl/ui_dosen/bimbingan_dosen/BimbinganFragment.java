package com.example.spmpkl.ui_dosen.bimbingan_dosen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.spmpkl.R;
import com.example.spmpkl.databinding.DosenFragmentBimbinganBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.MahasiswaFragmentBimbinganBinding;
import com.example.spmpkl.ui_mahasiswa.hasil.HasilFragment;
import com.example.spmpkl.ui_mahasiswa.upload.UploadFragment;

public class BimbinganFragment extends Fragment {
    Button bimbingan, hasil;

    private DosenFragmentBimbinganBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(BimbinganViewModel.class);

        binding = DosenFragmentBimbinganBinding.inflate(inflater, container, false);

        bimbingan = binding.btnBim;
        hasil = binding.btnHasil;

        bimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasil.setVisibility(View.GONE);
                bimbingan.setVisibility(View.GONE);
                replaceFragment(new DownloadFragment());
                /*Intent intent = new Intent(BimbinganFragment.this.getActivity(), UploadPdf.class);
                startActivity(intent);*/
            }
        });

        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasil.setVisibility(View.GONE);
                bimbingan.setVisibility(View.GONE);
                replaceFragment(new KirimFragment());
            }
        });

        View view = binding.getRoot();

        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;
    }
    private void replaceFragment(Fragment fragment){


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, fragment).commit();
        ft.addToBackStack(null);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}