package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
    private static final int FACTS = 59;

    androidx.appcompat.widget.Toolbar toolbarVolume;
    EditText searchEditTextVolume;
    ImageButton searchButtonVolume;
    RecyclerView comicListViewVolume;
    TextView factsTextViewVolume;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comicsVolumeReference;
    DatabaseReference factsReference;

    //GET DATA
    String username;
    String title_name;

    //RECYCLER VIEW
    ArrayList<String> listVolumes = new ArrayList<>();



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

        factsTextViewVolume = (TextView) findViewById(R.id.factsVolume);

        comicListViewVolume = (RecyclerView) findViewById(R.id.comic_recyclerViewVolume);
        comicListViewVolume.setHasFixedSize(true);
        comicListViewVolume.setLayoutManager(new LinearLayoutManager(this));

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comicsVolumeReference = database.getReference().child("Comics").child(username).child(title_name);
        factsReference = database.getReference().child("Facts");


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

        //SEARCH BUTTON

        final volumeRecyclerAdapter mVolumeRecyclerAdapter2 = new volumeRecyclerAdapter(listVolumes, this);


        searchButtonVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String searchText = searchEditTextVolume.getText().toString().trim().toUpperCase();
                listVolumes.clear();

                comicListViewVolume.setAdapter(mVolumeRecyclerAdapter2);

                comicsVolumeReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listVolumes.clear();

                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                            String keys = dataSnapshot.getKey().toString().toUpperCase();
                            if(keys.contains(searchText)){
                                listVolumes.add(dataSnapshot.getKey().toString());
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        //FACTS

        final String posi = Integer.toString((int) Math.floor(Math.random() * FACTS));
        factsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(dataSnapshot.getKey().toString().equals(posi)){

                        String facts_text = "Did you know? \n" + dataSnapshot.getValue().toString();

                        factsTextViewVolume.setText(facts_text);
                    }
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
