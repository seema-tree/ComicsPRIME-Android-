package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.Adapters.volumeRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VolumesActivity extends AppCompatActivity implements volumeRecyclerAdapter.OnComicVolumeListener {

    private static final String TAG = "VolumesActivity";

    androidx.appcompat.widget.Toolbar toolbarVolume;
    EditText searchEditTextVolume;
    ImageButton searchButtonVolume;
    RecyclerView comicListViewVolume;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comicsVolumeReference;

    //GET DATA
    String username;
    String title_name;

    //RECYCLER VIEW
    final ArrayList<String> listVolumes = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volumes_page);

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username_1");
        title_name = bundle.getString("title_name");


        searchEditTextVolume = (EditText) findViewById(R.id.search_barVolume);
        searchButtonVolume = (ImageButton) findViewById(R.id.search_btnVolume);

        comicListViewVolume = (RecyclerView) findViewById(R.id.comic_recyclerViewVolume);
        comicListViewVolume.setHasFixedSize(true);
        comicListViewVolume.setLayoutManager(new LinearLayoutManager(this));

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comicsVolumeReference = database.getReference().child("Comics").child(username).child(title_name);


        //TOOLBAR
        toolbarVolume = findViewById(R.id.toolbarVolume);
        toolbarVolume.setTitle(title_name); //SETTING OTHER TITLE
        setSupportActionBar(toolbarVolume);


        //RECYCLER VIEW
        volumeRecyclerAdapter mVolumeRecyclerAdapter = new volumeRecyclerAdapter(listVolumes, this);
        comicListViewVolume.setAdapter(mVolumeRecyclerAdapter);

        comicsVolumeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listVolumes.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    listVolumes.add(dataSnapshot.getKey().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onComicVolumeClick(int position) {

        Intent intent = new Intent(this, IssuesActivity.class);

        //PASS DATA
        intent.putExtra("username", username);
        intent.putExtra("title_name", title_name);
        intent.putExtra("volume_name", listVolumes.get(position));

        startActivity(intent);

    }
}