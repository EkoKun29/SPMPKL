package com.example.spmpkl.ui_pelap.home_pelap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



import com.example.spmpkl.databinding.PelapFragmentHomeBinding;


public class HomeFragment extends Fragment {

    TextView text_name, text_npm;

    private com.example.spmpkl.databinding.PelapFragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = PelapFragmentHomeBinding.inflate(inflater, container, false);
        text_name = binding.textName;
        text_npm =   binding.textNpm;

        String m_username = getActivity().getIntent().getStringExtra("Username");
        String m_name = getActivity().getIntent().getStringExtra("Name");
        String m_ppkl = getActivity().getIntent().getStringExtra("Ppkl");

        text_name.setText(m_name);
        text_npm.setText(m_username);



        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}