package com.example.spmpkl.ui.home_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.spmpkl.databinding.AdminFragmentHomeBinding;


public class HomeFragment extends Fragment {
    TextView Name;
    private AdminFragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = AdminFragmentHomeBinding.inflate(inflater, container, false);

        String m_name = getActivity().getIntent().getStringExtra("Name");
        Name = binding.textName;
        Name.setText(m_name);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}