package com.example.spmpkl.ui_mahasiswa.bimbingan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.spmpkl.R;
import com.example.spmpkl.UploadPdf;
import com.example.spmpkl.databinding.MahasiswaFragmentBimbinganBinding;
import com.example.spmpkl.ui_mahasiswa.hasil.HasilFragment;
import com.example.spmpkl.ui_mahasiswa.upload.UploadFragment;

public class BimbinganFragment extends Fragment {

    Button bimbingan, hasil;

    private MahasiswaFragmentBimbinganBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //MahasiswaHomeViewModel homeViewModel = new ViewModelProvider(this).get(MahasiswaHomeViewModel.class);
        binding = MahasiswaFragmentBimbinganBinding.inflate(inflater, container, false);
        //View m_view = inflater.inflate(R.layout.mahasiswa_fragment_home, container, false);

        bimbingan = binding.btnBim;
        hasil = binding.btnHasil;

        bimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasil.setVisibility(View.GONE);
                bimbingan.setVisibility(View.GONE);
                replaceFragment(new UploadFragment());
                /*Intent intent = new Intent(BimbinganFragment.this.getActivity(), UploadPdf.class);
                startActivity(intent);*/
            }
        });

        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasil.setVisibility(View.GONE);
                bimbingan.setVisibility(View.GONE);
                replaceFragment(new HasilFragment());
            }
        });




        View view = binding.getRoot();



       // gettingData();
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