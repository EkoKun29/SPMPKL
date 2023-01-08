package com.example.spmpkl.ui_dosen.rekap_dosen;

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
import com.example.spmpkl.databinding.DosenFragmentRekapsBinding;
import com.example.spmpkl.databinding.PelapFragmentRekapsBinding;

public class RekapSFragment extends Fragment {
    Button P1, P2, P3, P4, P5, P6, P7, P8, P9, P10;

    private DosenFragmentRekapsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = DosenFragmentRekapsBinding.inflate(inflater, container, false);
        P1 = binding.Nama1;
        P2 = binding.Nama2;
        P3 = binding.Nama3;
        P4 = binding.Nama4;
        P5 = binding.Nama5;
        P6 = binding.Nama6;
        P7 = binding.Nama7;
        P8 = binding.Nama8;
        P9 = binding.Nama9;
        P10 = binding.Nama10;

        P1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new RekapFragment());
            }
        });
        P2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap2Fragment());
            }
        });
        P3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap3Fragment());
            }
        });
        P4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap4Fragment());
            }
        });

        P5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });
        P6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });
        P7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });
        P8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });
        P9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });
        P10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.setVisibility(View.GONE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
                replaceFragment(new Rekap5Fragment());
            }
        });


        gettingData();
        View view = binding.getRoot();
        return view;
    }

    private void gettingData() {
        Intent intent = getActivity().getIntent();
        String m_name = intent.getStringExtra("Mahasiswa1");
        String m_name2 = intent.getStringExtra("Mahasiswa2");
        String m_name3 = intent.getStringExtra("Mahasiswa3");
        String m_name4 = intent.getStringExtra("Mahasiswa4");
        String m_name5 = intent.getStringExtra("Mahasiswa5");
        String m_name6 = intent.getStringExtra("Mahasiswa6");
        String m_name7 = intent.getStringExtra("Mahasiswa7");
        String m_name8 = intent.getStringExtra("Mahasiswa8");
        String m_name9 = intent.getStringExtra("Mahasiswa9");
        String m_name10 = intent.getStringExtra("Mahasiswa10");

        P1 = binding.Nama1;
        P2 = binding.Nama2;
        P3 = binding.Nama3;
        P4 = binding.Nama4;
        P5 = binding.Nama5;
        P6 = binding.Nama6;
        P7 = binding.Nama7;
        P8 = binding.Nama8;
        P9 = binding.Nama9;
        P10 = binding.Nama10;

        P1.setText(m_name);
        P2.setText(m_name2);
        P3.setText(m_name3);
        P4.setText(m_name4);
        P5.setText(m_name5);
        P6.setText(m_name6);
        P7.setText(m_name7);
        P8.setText(m_name8);
        P9.setText(m_name9);
        P10.setText(m_name10);
        if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6!= null && m_name7!= null && m_name8!= null && m_name9!= null && m_name10!= null){
            P1.setVisibility(View.VISIBLE);
            P2.setVisibility(View.VISIBLE);
            P3.setVisibility(View.VISIBLE);
            P4.setVisibility(View.VISIBLE);
            P5.setVisibility(View.VISIBLE);
            P6.setVisibility(View.VISIBLE);
            P7.setVisibility(View.VISIBLE);
            P8.setVisibility(View.VISIBLE);
            P9.setVisibility(View.VISIBLE);
            P10.setVisibility(View.VISIBLE);
        }else{
            if(m_name!= null && m_name2== null && m_name3== null && m_name4== null && m_name5== null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.GONE);
                P3.setVisibility(View.GONE);
                P4.setVisibility(View.GONE);
                P5.setVisibility(View.GONE);
                P6.setVisibility(View.GONE);
                P7.setVisibility(View.GONE);
                P8.setVisibility(View.GONE);
                P9.setVisibility(View.GONE);
                P10.setVisibility(View.GONE);
            }else{
                if(m_name!= null && m_name2!= null && m_name3== null && m_name4== null && m_name5== null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                    P1.setVisibility(View.VISIBLE);
                    P2.setVisibility(View.VISIBLE);
                    P3.setVisibility(View.GONE);
                    P4.setVisibility(View.GONE);
                    P5.setVisibility(View.GONE);
                    P6.setVisibility(View.GONE);
                    P7.setVisibility(View.GONE);
                    P8.setVisibility(View.GONE);
                    P9.setVisibility(View.GONE);
                    P10.setVisibility(View.GONE);
                }else{
                    if(m_name!= null && m_name2!= null && m_name3!= null && m_name4== null && m_name5== null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                        P1.setVisibility(View.VISIBLE);
                        P2.setVisibility(View.VISIBLE);
                        P3.setVisibility(View.VISIBLE);
                        P4.setVisibility(View.GONE);
                        P5.setVisibility(View.GONE);
                        P6.setVisibility(View.GONE);
                        P7.setVisibility(View.GONE);
                        P8.setVisibility(View.GONE);
                        P9.setVisibility(View.GONE);
                        P10.setVisibility(View.GONE);
                    }else{
                        if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5== null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                            P1.setVisibility(View.VISIBLE);
                            P2.setVisibility(View.VISIBLE);
                            P3.setVisibility(View.VISIBLE);
                            P4.setVisibility(View.VISIBLE);
                            P5.setVisibility(View.GONE);
                            P6.setVisibility(View.GONE);
                            P7.setVisibility(View.GONE);
                            P8.setVisibility(View.GONE);
                            P9.setVisibility(View.GONE);
                            P10.setVisibility(View.GONE);
                        }else{
                            if(m_name== null && m_name2== null && m_name3== null && m_name4== null && m_name5== null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null) {
                                P1.setVisibility(View.GONE);
                                P2.setVisibility(View.GONE);
                                P3.setVisibility(View.GONE);
                                P4.setVisibility(View.GONE);
                                P5.setVisibility(View.GONE);
                                P6.setVisibility(View.GONE);
                                P7.setVisibility(View.GONE);
                                P8.setVisibility(View.GONE);
                                P9.setVisibility(View.GONE);
                                P10.setVisibility(View.GONE);
                            }else{
                                if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6== null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                                    P1.setVisibility(View.VISIBLE);
                                    P2.setVisibility(View.VISIBLE);
                                    P3.setVisibility(View.VISIBLE);
                                    P4.setVisibility(View.VISIBLE);
                                    P5.setVisibility(View.VISIBLE);
                                    P6.setVisibility(View.GONE);
                                    P7.setVisibility(View.GONE);
                                    P8.setVisibility(View.GONE);
                                    P9.setVisibility(View.GONE);
                                    P10.setVisibility(View.GONE);
                                }else{
                                    if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6!= null && m_name7== null && m_name8== null && m_name9== null && m_name10== null){
                                        P1.setVisibility(View.VISIBLE);
                                        P2.setVisibility(View.VISIBLE);
                                        P3.setVisibility(View.VISIBLE);
                                        P4.setVisibility(View.VISIBLE);
                                        P5.setVisibility(View.VISIBLE);
                                        P6.setVisibility(View.VISIBLE);
                                        P7.setVisibility(View.GONE);
                                        P8.setVisibility(View.GONE);
                                        P9.setVisibility(View.GONE);
                                        P10.setVisibility(View.GONE);
                                    }else{
                                        if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6!= null && m_name7!= null && m_name8== null && m_name9== null && m_name10== null){
                                            P1.setVisibility(View.VISIBLE);
                                            P2.setVisibility(View.VISIBLE);
                                            P3.setVisibility(View.VISIBLE);
                                            P4.setVisibility(View.VISIBLE);
                                            P5.setVisibility(View.VISIBLE);
                                            P6.setVisibility(View.VISIBLE);
                                            P7.setVisibility(View.VISIBLE);
                                            P8.setVisibility(View.GONE);
                                            P9.setVisibility(View.GONE);
                                            P10.setVisibility(View.GONE);
                                        }else{
                                            if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6!= null && m_name7!= null && m_name8!= null && m_name9== null && m_name10== null){
                                                P1.setVisibility(View.VISIBLE);
                                                P2.setVisibility(View.VISIBLE);
                                                P3.setVisibility(View.VISIBLE);
                                                P4.setVisibility(View.VISIBLE);
                                                P5.setVisibility(View.VISIBLE);
                                                P6.setVisibility(View.VISIBLE);
                                                P7.setVisibility(View.VISIBLE);
                                                P8.setVisibility(View.VISIBLE);
                                                P9.setVisibility(View.GONE);
                                                P10.setVisibility(View.GONE);
                                            }else {
                                                if(m_name!= null && m_name2!= null && m_name3!= null && m_name4!= null && m_name5!= null && m_name6!= null && m_name7!= null && m_name8!= null && m_name9!= null && m_name10== null){
                                                    P1.setVisibility(View.VISIBLE);
                                                    P2.setVisibility(View.VISIBLE);
                                                    P3.setVisibility(View.VISIBLE);
                                                    P4.setVisibility(View.VISIBLE);
                                                    P5.setVisibility(View.VISIBLE);
                                                    P6.setVisibility(View.VISIBLE);
                                                    P7.setVisibility(View.VISIBLE);
                                                    P8.setVisibility(View.VISIBLE);
                                                    P9.setVisibility(View.VISIBLE);
                                                    P10.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }
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
