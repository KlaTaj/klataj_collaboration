package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText pseudo;
    private EditText imel;
    private EditText modpas;
    private TextView txt_register;
    private TextView txt_signup;
    private Button btn_register;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        // findViewById for editText
        pseudo = (EditText) findViewById(R.id.pseudo_reg);
        imel = (EditText) findViewById(R.id.email_reg);
        modpas = (EditText) findViewById(R.id.password_reg);

        // findViewById for textView
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        progressDialog = new ProgressDialog(this);

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        // findViewById for button
        btn_register = (Button) findViewById(R.id.button_reg);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pseudo_name = pseudo.getText().toString();
                String email_reg = imel.getText().toString();
                String password_reg = modpas.getText().toString();

                if (!TextUtils.isEmpty(password_reg) || !TextUtils.isEmpty(email_reg) || !TextUtils.isEmpty(password_reg)){


                    progressDialog.setMessage("Tanpri tann pandan nou tckeke kalifikasyon ou yo!");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    register_user(pseudo_name,email_reg,password_reg);
                }


            }
        });


    }

    private void register_user(String pseudo_name, String email_reg, String password_reg) {
        mAuth.createUserWithEmailAndPassword(email_reg, password_reg)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.hide();

                            Toast.makeText(RegisterActivity.this, "Ou paka konekte. Tanpri tcheke fòm lan epi eseye ankò.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

}
