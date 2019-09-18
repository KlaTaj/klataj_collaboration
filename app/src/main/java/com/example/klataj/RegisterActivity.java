package com.example.klataj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText pseudo, imel,modpas;
    TextView txt_register, txt_signup;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // findViewById for editText
        pseudo = (EditText) findViewById(R.id.etPseudo);
        imel = (EditText) findViewById(R.id.email_register);
        modpas = (EditText) findViewById(R.id.pwrd_register);

        // findViewById for textView
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        // findViewById for button
        btn_register = (Button) findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }
}
