package com.example.spmpkl.ui_pelap.rekap_pelap;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spmpkl.R;
import com.example.spmpkl.adaptermodel.UserAdapter;
import com.example.spmpkl.databinding.PelapFragmentRekapBinding;
import com.example.spmpkl.databinding.PelapFragmentRekapsBinding;
import com.example.spmpkl.ui_mahasiswa.upload.UploadFragment;

public class RekapSFragment extends Fragment {
    Button P1, P2, P3;

    private PelapFragmentRekapsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = PelapFragmentRekapsBinding.inflate(inflater, container, false);
        P1 = binding.Nama1;
        P2 = binding.Nama2;
        P3 = binding.Nama3;

        P1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                replaceFragment(new RekapFragment());
            }
        });
        P2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                replaceFragment(new Rekap2Fragment());
            }
        });
        P3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
               P3.setVisibility(View.GONE);
                replaceFragment(new Rekap3Fragment());
            }
        });

        gettingData();
        View view = binding.getRoot();
        return view;
    }

    private void gettingData() {
        Intent intent = getActivity().getIntent();
        String m_name = intent.getStringExtra("Ppkl1");
        String m_name2 = intent.getStringExtra("Ppkl2");
        String m_name3 = intent.getStringExtra("Ppkl3");

        P1 = binding.Nama1;
        P2 = binding.Nama2;
        P3 = binding.Nama3;

        P1.setText(m_name);
        P2.setText(m_name2);
        P3.setText(m_name3);
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
