package com.example.spmpkl.ui_dosen.nilai_dosen;

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
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentNilaiBinding;
import com.example.spmpkl.ui_dosen.bimbingan_dosen.KirimFragment;

public class NilaiFragment extends Fragment {
    Button Download;
    TextView teksNilai, tN;

    private DosenFragmentNilaiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DosenFragmentNilaiBinding.inflate(inflater, container, false);
        Download = binding.down;
        teksNilai = binding.textNilai1;
        tN = binding.textNilai;

        Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download.setVisibility(View.GONE);
                teksNilai.setVisibility(View.GONE);
                tN.setVisibility(View.GONE);
                replaceFragment(new ListNilaiFragment());
            }
        });



        View view = binding.getRoot();

        /*final TextView textView = binding.textHome;*/
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