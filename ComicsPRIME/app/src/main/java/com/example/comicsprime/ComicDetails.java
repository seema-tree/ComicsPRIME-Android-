package com.example.comicsprime;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComicDetails extends AppCompatActivity {

    private final String TAG = "ComicDetails";

    androidx.appcompat.widget.Toolbar toolbarComicDetails;


    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comicDetailsReference;

    //GET DATA
    String username;
    String title_name;
    String volume_name;
    String issue_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_details_page);

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        title_name = bundle.getString("title_name");
        volume_name = bundle.getString("volume_name");
        issue_name = bundle.getString("issue_name");

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comicDetailsReference = database.getReference().child("Comics").child(username).child(title_name).child(volume_name).child(issue_name);


        //TOOLBAR
        toolbarComicDetails = findViewById(R.id.toolbarComicsDetails);
        toolbarComicDetails.setTitle(title_name + " Vol " +  volume_name + " " + issue_name); //SETTING OTHER TITLE
        setSupportActionBar(toolbarComicDetails);
    }
}
