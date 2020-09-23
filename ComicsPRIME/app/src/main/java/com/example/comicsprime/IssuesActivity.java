package com.example.comicsprime;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.Adapters.issueRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IssuesActivity extends AppCompatActivity implements issueRecyclerAdapter.OnComicIssueListener{

    private static final String TAG = "IssuesActivity";

    androidx.appcompat.widget.Toolbar toolbarIssue;
    EditText searchEditTextIssue;
    ImageButton searchButtonIssue;
    RecyclerView comicListViewIssue;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comicsIssueReference;

    //GET DATA
    String username;
    String title_name;
    String volume_name;

    //RECYCLER VIEW
    final ArrayList<String> listIssues = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issues_page);

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        title_name = bundle.getString("title_name");
        volume_name = bundle.getString("volume_name");

        searchEditTextIssue = (EditText) findViewById(R.id.search_barIssue);
        searchButtonIssue = (ImageButton) findViewById(R.id.search_btnIssue);

        comicListViewIssue = (RecyclerView) findViewById(R.id.comic_recyclerViewIssue);
        comicListViewIssue.setHasFixedSize(true);
        comicListViewIssue.setLayoutManager(new LinearLayoutManager(this));

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comicsIssueReference = database.getReference().child("Comics").child(username).child(title_name).child(volume_name);


        //TOOLBAR
        toolbarIssue = findViewById(R.id.toolbarIssue);
        toolbarIssue.setTitle(title_name + " Volume " +  volume_name); //SETTING OTHER TITLE
        setSupportActionBar(toolbarIssue);


        //RECYCLER VIEW
        issueRecyclerAdapter mIssueRecyclerAdapter = new issueRecyclerAdapter(listIssues, this);
        comicListViewIssue.setAdapter(mIssueRecyclerAdapter);

        comicsIssueReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listIssues.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    listIssues.add(dataSnapshot.getKey().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onComicIssueClick(int position) {

    }
}
