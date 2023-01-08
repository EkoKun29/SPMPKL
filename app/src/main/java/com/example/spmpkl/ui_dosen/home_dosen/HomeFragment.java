package com.example.spmpkl.ui_dosen.home_dosen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.spmpkl.databinding.DosenFragmentHomeBinding;
import com.example.spmpkl.databinding.DosenFragmentHomeBinding;

public class HomeFragment extends Fragment {
    TextView text_name, text_npm;

    private DosenFragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DosenFragmentHomeBinding.inflate(inflater, container, false);

        text_name = binding.textName;
        text_npm =  binding.textNpm;


        String m_username = getActivity().getIntent().getStringExtra("Username");
        String m_name = getActivity().getIntent().getStringExtra("Name");
        String m_dosen = getActivity().getIntent().getStringExtra("Dosbing");



        text_name.setText(m_name);
        text_npm.setText(m_username);

        View view = binding.getRoot();

        final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}