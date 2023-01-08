package com.example.spmpkl.ui_mahasiswa.upload;

import static android.app.Activity.RESULT_OK;
import static com.google.common.io.Files.getFileExtension;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.spmpkl.MapsActivity;

import com.example.spmpkl.UploadFotoActivity;
import com.example.spmpkl.UploadPdf;
import com.example.spmpkl.databinding.MahasiswaFragmentUploadBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;


public class UploadFragment extends Fragment {

    EditText file;
    Button Simpan, Select;
    DatePickerDialog.OnDateSetListener setListener;
    Uri pdfUri;
    private ProgressDialog progressDialog;
    private String pdfName;
    FirebaseStorage storage;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MahasiswaPDF");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityResultLauncher<String> mData;


    private MahasiswaFragmentUploadBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = MahasiswaFragmentUploadBinding.inflate(inflater, container, false);
        storage = FirebaseStorage.getInstance();
       /* database = FirebaseDatabase.getInstance();*/
       /* mData = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {

                    @Override
                    public void onActivityResult(Uri result) {

                        pdfUri = Uri.parse(result.getLastPathSegment());
                        file.setText("A File is Selected :" + pdfUri);

                    }
                });*/
        file = binding.file;
        Simpan = binding.simpan;
        Select = binding.select;


        Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                    /*mData.launch("application/pdf");*/

                } else {
                    ActivityCompat.requestPermissions(UploadFragment.this.getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });


        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfUri != null) {
                    uploadtoFirebase(pdfUri);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please Select Pdf", Toast.LENGTH_SHORT).show();

                }
            }

        });

/*
        progressDialog = new ProgressDialog(UploadFragment.this.getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan....");*/


        View view = binding.getRoot();
        return view;
    }

    private void uploadtoFirebase(Uri uri) {
        progressDialog= new ProgressDialog(requireContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file....");
        progressDialog.setProgress(0);
        progressDialog.show();
        String fileName=System.currentTimeMillis()+".pdf";
        StorageReference storageReference = storage.getReference();
        storageReference.child("PdfFile").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();

                        putPDF putPDF= new putPDF(file.getText().toString(), uri.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                        Toast.makeText(UploadFragment.this.getActivity(), "File Upload", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        /*int currentProgress=(int)(100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        progressDialog.setProgress(currentProgress);*/
                        double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("File Uploaded..."+(int) progress+"%");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadFragment.this.getActivity(), "File Not Successfully Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        UploadFragment.super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }else
            Toast.makeText(getActivity().getApplicationContext(), "Please Provide Permission", Toast.LENGTH_SHORT).show();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            file.setText("A file is selected :" + data.getData().getLastPathSegment());
        }else{
            Toast.makeText(UploadFragment.this.getActivity(), "Please Select a File", Toast.LENGTH_SHORT).show();
        }
    }
    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}


