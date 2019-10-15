package com.example.klataj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        toolbar = (Toolbar) findViewById(R.id.users_appBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tout itilizatè");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users");




        recyclerView = (RecyclerView) findViewById(R.id.users_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(userDatabase,Users.class)
                .build();

        FirebaseRecyclerAdapter<Users,UsersViewHolder> usersUsersViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, final int position, @NonNull Users model) {

                holder.userName.setText(model.getName());
                holder.userStatus.setText(model.getStatus());
                Picasso.with(UsersActivity.this).load(model.getImage()).placeholder(R.drawable.default_image_profile).into(holder.profileImange);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String visit_user_id = getRef(position).getKey();

                        Intent profilIntent = new Intent(UsersActivity.this,ProfileActivity.class);
                        profilIntent.putExtra("visit_user_id",visit_user_id);
                        startActivity(profilIntent);

                    }
                });

            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_single_layout,parent,false);
               UsersViewHolder usersViewHolder = new UsersViewHolder(view);
               return usersViewHolder;
            }
        };
        recyclerView.setAdapter(usersUsersViewHolderFirebaseRecyclerAdapter);
        usersUsersViewHolderFirebaseRecyclerAdapter.startListening();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{


        TextView userName, userStatus;
        CircleImageView profileImange;


        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.users_single_name);
            userStatus = itemView.findViewById(R.id.users_single_status);
            profileImange = itemView.findViewById(R.id.users_single_image);
        }
    }
}
