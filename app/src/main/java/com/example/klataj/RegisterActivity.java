package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout pseudo;
    private TextInputLayout imel;
    private TextInputLayout modpas;
    TextView txt_register, txt_signup;
    Button btn_register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // findViewById for editText
        pseudo = (TextInputLayout) findViewById(R.id.etPseudo);
        imel = (TextInputLayout) findViewById(R.id.email_register);
        modpas = (TextInputLayout) findViewById(R.id.pwrd_register);

        // findViewById for textView
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        // findViewById for button
        btn_register = (Button) findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String pseudo_name = pseudo.getEditText().getText().toString();
               String imel_register = imel.getEditText().getText().toString();
               String modpas_register = modpas.getEditText().getText().toString();


               register_user(pseudo_name, imel_register,modpas_register);

            }
        });
    }

    private void register_user(String pseudo_name, String imel_register, String modpas_register) {

        mAuth.createUserWithEmailAndPassword(imel_register,  modpas_register)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            fileList();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Ou gen yon erè.",
                                    Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }
}
