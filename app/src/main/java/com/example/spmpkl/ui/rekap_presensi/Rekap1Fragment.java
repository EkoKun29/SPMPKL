package com.example.spmpkl.ui.rekap_presensi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spmpkl.adaptermodel.UserAdapter;
import com.example.spmpkl.databinding.AdminRekap1FragmentBinding;
import com.example.spmpkl.databinding.DosenFragmentRekap2Binding;
import com.example.spmpkl.model.User;
import com.example.spmpkl.ui_dosen.rekap_dosen.Rekap2Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Rekap1Fragment extends Fragment {
    RecyclerView recyclerView;
    EditText Name;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;
    private AdminRekap1FragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = AdminRekap1FragmentBinding.inflate(inflater, container, false);
        recyclerView = binding.rv;

        progressDialog = new ProgressDialog(Rekap1Fragment.this.getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data....");
        userAdapter = new UserAdapter(getActivity().getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Rekap1Fragment.this.getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:

                                Intent intent = new Intent();
                                intent.putExtra("id",list.get(pos).getId());
                                intent.putExtra("date",list.get(pos).getDate());
                                intent.putExtra("time",list.get(pos).getTime());
                                intent.putExtra("ket",list.get(pos).getKet());
                                intent.putExtra("lok",list.get(pos).getLoc());

                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);


        gettingData();
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        getData();
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

        Name = binding.Nama;

    Name.setText(m_name);
    Name.setText(m_name2);
    Name.setText(m_name3);
 Name.setText(m_name4);
Name.setText(m_name5);
Name.setText(m_name6);
Name.setText(m_name7);
Name.setText(m_name8);
Name.setText(m_name9);
Name.setText(m_name10);


    }
    private void getData(){
        Intent intent = getActivity().getIntent();
        String m_name = intent.getStringExtra("Mahasiswa2");
        progressDialog.show();
        db.collection(m_name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                User user=new User(document.getString("Date"),document.getString("Time"),document.getString("Keterangan"),document.getString("Lokasi Terbaru"), document.getString("Izin"));
                                user.setId(document.getId());
                                list.add(user);

                            }
                            userAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void deleteData(String id){
        Intent intent = getActivity().getIntent();
        String m_name = intent.getStringExtra("Mahasiswa2");
        progressDialog.show();
        db.collection(m_name).document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getActivity().getApplicationContext(), "Data Gagal di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
