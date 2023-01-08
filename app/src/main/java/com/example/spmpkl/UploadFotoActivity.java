package com.example.spmpkl;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadFotoActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ImageView image;
    private EditText url;
    private TextView upload;
    private TextView coba;
    private Button upload_top, upload_button;
    private ProgressBar progesbar;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    private StorageReference storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://spmpkl-567fc.appspot.com");
    private static final int PICK_IMAGE_REQUEST = 2;
    private Uri imageUri;

    String img = "";
    Double lat = 0.0;
    Double lng = 0.0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.uploadfoto);

        getSupportActionBar().setTitle("Upload Profile Picture");

        upload = findViewById(R.id.upload);
        upload_top = findViewById(R.id.upload_foto);
        upload_button = findViewById(R.id.upload_foto_button);
        image = findViewById(R.id.image_profile);
        url = findViewById(R.id.url);
        progesbar = findViewById(R.id.probar);

        progressDialog = new ProgressDialog(UploadFotoActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengupload....");

       /* Uri uri = .getPhotoUrl();*/


        img = this.getIntent().getStringExtra(RegisterActivity.imgIntent);
        lat = this.getIntent().getDoubleExtra(RegisterActivity.latIntent, 0.0);
        lng = this.getIntent().getDoubleExtra(RegisterActivity.lngIntent, 0.0);


        upload_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/.*");
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);

            }
        });

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null) {
                    uploadtoFirebase(imageUri);


                } else {
                    Toast.makeText(UploadFotoActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            image.setImageURI(imageUri);
            /*addToStorage(imageUri);*/

        }
    }

    private void uploadtoFirebase(Uri uri){

        StorageReference fileRef = storage.child(System.currentTimeMillis()+ "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Log.d("Tag",uri.toString());

//                        Picasso.get().load(uri).into(image);
                        Intent intent = new Intent(UploadFotoActivity.this, RegisterActivity.class);
                        intent.putExtra(RegisterActivity.imgIntent, uri.toString());
                        intent.putExtra(RegisterActivity.lngIntent, lng);
                        intent.putExtra(RegisterActivity.latIntent, lat);
                        startActivity(intent);
                        finish();

                      /*  Model model = new Model(uri.toString());
                        String modelId = databaseReference.push().getKey();
                        databaseReference.child(modelId).setValue(model);*/
                    }
                });

                progesbar.setVisibility(View.GONE);
                Toast.makeText(UploadFotoActivity.this, "Uploaded Succesfully", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(UploadFotoActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();*/



            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadFotoActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}