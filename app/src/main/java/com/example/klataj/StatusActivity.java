package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText status;
    private Button save_btn;

    //firebase
    private DatabaseReference statusDatabase;
    private FirebaseUser currentUser;

    // Progress
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        //Firebase
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        String current_uid = currentUser.getUid();

        statusDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);


        toolbar = (Toolbar) findViewById(R.id.status_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kont Stati");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String status_value = getIntent().getStringExtra("status_value");


        status = (EditText) findViewById(R.id.status_imput);
        save_btn = (Button) findViewById(R.id.status_save_btn);

        status.setText(status_value);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progress
                progressDialog = new ProgressDialog(StatusActivity.this);
                progressDialog.setTitle("Sove Chanjman Yo");
                progressDialog.setMessage("Tanpri, tann pandan nou anrejistre chanjman yo");
                progressDialog.show();

                String your_status = status.getText().toString();

                statusDatabase.child("status").setValue(your_status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            progressDialog.dismiss();

                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Te gen yon erè nan evolisyon nan chanjman", Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }
        });
    }
}
