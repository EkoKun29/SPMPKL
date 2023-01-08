package com.example.spmpkl.ui_mahasiswa.nilai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.spmpkl.R;
import com.example.spmpkl.databinding.MahasiswaFragmentBimbinganBinding;
import com.example.spmpkl.databinding.MahasiswaFragmentNilaisBinding;
import com.example.spmpkl.ui_mahasiswa.hasil.HasilFragment;
import com.example.spmpkl.ui_mahasiswa.upload.UploadFragment;

public class NilaiSFragment extends Fragment {
    Button Lihat, Kirim;

    private MahasiswaFragmentNilaisBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //MahasiswaHomeViewModel homeViewModel = new ViewModelProvider(this).get(MahasiswaHomeViewModel.class);
        binding = MahasiswaFragmentNilaisBinding.inflate(inflater, container, false);
        //View m_view = inflater.inflate(R.layout.mahasiswa_fragment_home, container, false);

        Lihat = binding.btnNilai;
        Kirim = binding.btnKirim;

       Lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kirim.setVisibility(View.GONE);
                Lihat.setVisibility(View.GONE);
                replaceFragment(new NilaiFragment());
                /*Intent intent = new Intent(BimbinganFragment.this.getActivity(), UploadPdf.class);
                startActivity(intent);*/
            }
        });

        Kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kirim.setVisibility(View.GONE);
                Lihat.setVisibility(View.GONE);
                replaceFragment(new KirimNilaiFragment());
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
