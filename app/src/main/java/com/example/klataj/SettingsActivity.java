package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.File;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private DatabaseReference userDatabase;
    private FirebaseUser currentUser;

    //android Layout
    private CircleImageView circleImageView;
    private TextView name_txt;
    private TextView status_txt;

    private Button status_btn;
    private Button image_btn;


    private static final int GALLERY_PICK = 1;

    //storage Firebase
    private StorageReference imageStorage;

    // progress bar
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        circleImageView = (CircleImageView) findViewById(R.id.settings_image);
        status_btn = (Button) findViewById(R.id.status_btn);
        name_txt = (TextView) findViewById(R.id.settings_pseudo);
        status_txt = (TextView) findViewById(R.id.settings_status);
        image_btn = (Button) findViewById(R.id.image_btn);

        imageStorage = FirebaseStorage.getInstance().getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        String current_uid = currentUser.getUid();


        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();
                String thumb_image = dataSnapshot.child("thumb_image").getValue().toString();

                name_txt.setText(name);
                status_txt.setText(status);


                Picasso.with(SettingsActivity.this).load(image).into(circleImageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status_value = status_txt.getText().toString();

                Intent intent = new Intent(SettingsActivity.this, StatusActivity.class);
                intent.putExtra("status_value",status_value);
                startActivity(intent);
                finish();

            }
        });

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  Intent gallery = new Intent();
                  gallery.setType("image/*");
                  gallery.setAction(Intent.ACTION_GET_CONTENT);
                  startActivityForResult(Intent.createChooser(gallery,"SELECT IMAGE"), GALLERY_PICK);

              /*  CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(SettingsActivity.this);
                        */

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK){

            // start cropping activity for pre-acquired image saved on the device
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);

           // Toast.makeText(SettingsActivity.this,imageUri, Toast.LENGTH_LONG).show();

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                progressDialog = new ProgressDialog(SettingsActivity.this);
                progressDialog.setTitle("Telechajeman imaj...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Tanpri tann pandan w ap telechaje ak trete imaj la.");
                progressDialog.show();

                final Uri resultUri = result.getUri();
                String current_user_id = currentUser.getUid();

                StorageReference filepath = imageStorage.child("profile_images").child(current_user_id + ".png");
                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){
                            String download_url = task.getResult().getStorage().getDownloadUrl().toString();
                            userDatabase.child("image").setValue(download_url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(SettingsActivity.this,"Telechajeman avèk siksè", Toast.LENGTH_LONG).show();
                                }
                            });

                        }else{

                            Toast.makeText(SettingsActivity.this,"erè nan telechajeman la", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
