package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        circleImageView = (CircleImageView) findViewById(R.id.settings_image);
        status_btn = (Button) findViewById(R.id.status_btn);
        name_txt = (TextView) findViewById(R.id.settings_pseudo);
        status_txt = (TextView) findViewById(R.id.settings_status);

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

    }
}
