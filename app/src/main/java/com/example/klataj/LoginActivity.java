package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText imel;
    private EditText modpas;
    private Button button_log;
    private TextView txt_register;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        imel = (EditText) findViewById(R.id.email_log);
        modpas = (EditText) findViewById(R.id.password_log);

        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            }
        });

        button_log = (Button) findViewById(R.id.button_log);

        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_log = imel.getText().toString();
                String password_log = modpas.getText().toString();

                if (!TextUtils.isEmpty(email_log) || !TextUtils.isEmpty(password_log)){

                     progressDialog.setMessage("Tanpri tann pandan nou tcheke kalifikasyon ou yo");
                     progressDialog.setCanceledOnTouchOutside(false);
                     progressDialog.show();

                     log_user(email_log,password_log);
                }
            }
        });
    }

    private void log_user(String email_log, String password_log) {

        mAuth.signInWithEmailAndPassword(email_log,password_log).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    progressDialog.dismiss();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }else {

                    progressDialog.hide();

                    // If sign in fails, display a message to the user.

                    Toast.makeText(LoginActivity.this, "Ou paka konekte. Tanpri tcheke fòm lan epi eseye ankò",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
