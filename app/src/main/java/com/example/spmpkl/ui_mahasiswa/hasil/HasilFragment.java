package com.example.spmpkl.ui_mahasiswa.hasil;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.spmpkl.R;
import com.example.spmpkl.databinding.DosenFragmentDownloadBinding;
import com.example.spmpkl.databinding.MahasiswaFragmentHasilBinding;

import com.example.spmpkl.databinding.MahasiswaFragmentRekapBinding;
import com.example.spmpkl.ui_mahasiswa.presensi.PresensiFragment;
import com.example.spmpkl.ui_mahasiswa.upload.putPDF;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HasilFragment extends Fragment {

    ListView lv;
    /* Button down;*/
    DatabaseReference databaseReference;
    private MahasiswaFragmentHasilBinding binding;
    List<putPDF> uploadedPDF;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = MahasiswaFragmentHasilBinding.inflate(inflater, container, false);
        lv = binding.listPdf;
        /*down = binding.btnDown;*/
        uploadedPDF = new ArrayList<>();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                putPDF putPDF = uploadedPDF.get(i);
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });

        retrievePDF();
        View view = binding.getRoot();
        return view;
    }

    private void retrievePDF(){
        databaseReference = FirebaseDatabase.getInstance().getReference("DosenPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {

                    putPDF putPDF = ds.getValue(com.example.spmpkl.ui_mahasiswa.upload.putPDF.class);
                    uploadedPDF.add(putPDF);
                }
                String[] uploadsname = new String[uploadedPDF.size()];
                for(int i=0; i<uploadsname.length; i++){
                    uploadsname[i] = uploadedPDF.get(i).getName();

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, uploadsname){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView textView = (TextView) view
                                .findViewById(android.R.id.text1);

                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(20);
                        return view;
                    }
                };
                lv.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}