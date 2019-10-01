package com.example.klataj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UsersActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        toolbar = (Toolbar) findViewById(R.id.users_appBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tout itilizatè");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        recyclerView = (RecyclerView) findViewById(R.id.users_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
