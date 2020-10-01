package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.Adapters.titleRecyclerAdapter;
import com.example.comicsprime.Model.Comic;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePageLoggedInActivity extends AppCompatActivity implements titleRecyclerAdapter.OnComicListener {

    private static final String TAG = "HomePageLoggedInActivity";

    FloatingActionButton addComicButton;
    androidx.appcompat.widget.Toolbar toolbar;
    EditText searchEditText;
    ImageButton searchButton;
    RecyclerView comicListView;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comicsReference;

    //GET DATA
    String username_1;


    //RECYCLER VIEW
    final ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeloggedin_page);


        searchEditText = (EditText) findViewById(R.id.search_bar);
        searchButton = (ImageButton) findViewById(R.id.search_btn);

        comicListView = (RecyclerView) findViewById(R.id.comic_recyclerView);
        comicListView.setHasFixedSize(true);
        comicListView.setLayoutManager(new LinearLayoutManager(this));

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        username_1 = bundle.getString("username");


        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comicsReference = database.getReference().child("Comics").child(username_1);


        //TOOLBAR
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Comics"); //SETTING OTHER TITLE
        setSupportActionBar(toolbar);


        //RECYCLER VIEW
        titleRecyclerAdapter mTitleRecyclerAdapter = new titleRecyclerAdapter(list, this);
        comicListView.setAdapter(mTitleRecyclerAdapter);

        comicsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    list.add(dataSnapshot.getKey().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //EDITS

        addComicButton = (FloatingActionButton) findViewById(R.id.addComic);

        addComicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), AddComicsActivity.class);

                //PASS DATA
                s.putExtra("username_1", username_1);

                startActivity(s);
            }
        });


        //SEARCH BUTTON

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = searchEditText.getText().toString().trim();

                //firebaseComicSearch(searchText);

            }
        });

    }

    @Override
    public void onComicClick(int position) {
        Intent intent = new Intent(this, VolumesActivity.class);

        //PASS DATA
        intent.putExtra("username_1", username_1);
        intent.putExtra("title_name", list.get(position));

        startActivity(intent);


    }







//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        //private void firebaseComicSearch(String searchText) {
//
//            //Query query = comicsReference.orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");
//
//
//
//            FirebaseRecyclerOptions<Comic> options =
//                    new FirebaseRecyclerOptions.Builder<Comic>()
//                            .setQuery(comicsReference, Comic.class)
//                            .build();
//
//
//            FirebaseRecyclerAdapter<Comic, ComicsViewHolder> adapter = new FirebaseRecyclerAdapter<Comic, ComicsViewHolder>(options) {
//
//                @Override
//                public ComicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
//
//
//                    return new ComicsViewHolder(view);
//                }
//
//                @Override
//                protected void onBindViewHolder(ComicsViewHolder holder, int position, Comic model) {
//                    // Bind the image_details object to the BlogViewHolder
//                    // ...
//                    holder.setDetails(model.getTitle());
//
//
//                }
//            };
//
////                Comic.class,
////                R.layout.list_layout,
////                ComicsViewHolder.class,
////                comicsReference
//
//
//
//            comicListView.setAdapter(adapter);
//            adapter.startListening();
//        //}
//    }


}
