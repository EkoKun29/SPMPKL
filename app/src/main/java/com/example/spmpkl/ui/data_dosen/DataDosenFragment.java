package com.example.spmpkl.ui.data_dosen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.spmpkl.databinding.AdminFragmentDatadosenBinding;


public class DataDosenFragment extends Fragment {

    private AdminFragmentDatadosenBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = AdminFragmentDatadosenBinding.inflate(inflater, container, false);


        View view = binding.getRoot();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}