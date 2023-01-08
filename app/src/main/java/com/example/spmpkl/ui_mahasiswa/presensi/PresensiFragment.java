package com.example.spmpkl.ui_mahasiswa.presensi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.spmpkl.R;
import com.example.spmpkl.databinding.MahasiswaFragmentPresensiBinding;
import com.example.spmpkl.ui_mahasiswa.maps.MapsCoba;
import com.example.spmpkl.ui_mahasiswa.maps.MapsFragment;
import com.example.spmpkl.ui_mahasiswa.rekap.RekapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class PresensiFragment extends Fragment {
Button btn_masuk, btn_rekap;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://spmpkl-567fc-default-rtdb.firebaseio.com/");

    private MahasiswaFragmentPresensiBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* MahasiswaPresensiViewModel presensiViewModel =
                new ViewModelProvider(this).get(MahasiswaPresensiViewModel.class);*/
        binding = MahasiswaFragmentPresensiBinding.inflate(inflater, container, false);
        if (ContextCompat.checkSelfPermission(PresensiFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
        }
        btn_masuk = binding.btnMasuk;
        btn_rekap = binding.btnRekap;




        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(PresensiFragment.this.getActivity())
                                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                                btn_rekap.setVisibility(View.GONE);
                                                btn_masuk.setVisibility(View.GONE);
                                                replaceFragment(new MapsCoba());
                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                                if(response.isPermanentlyDenied()){
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(PresensiFragment.this.getActivity());
                                                    builder.setTitle("Permission Denied")
                                                            .setMessage("Permission to acces device location is permanenly denied. you need to go to setting to allow the permission.")
                                                            .setNegativeButton("Cancel" , null)
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new  Intent();
                                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));

                                                                }

                                                                private String getPackageName() {
                                                                    return null;
                                                                }


                                                            }).show();
                                                }else{
                                                    Toast.makeText(PresensiFragment.this.getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                                            }
                                        }).check();
            }
        });

        btn_rekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_masuk.setVisibility(View.GONE);
                btn_rekap.setVisibility(View.GONE);
                replaceFragment(new RekapFragment());

            }
        });


        View view = binding.getRoot();
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