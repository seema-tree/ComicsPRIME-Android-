package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comicsprime.Model.Comic;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddComicsActivity extends AppCompatActivity {

    private static final String TAG = "AddComicsActivity";

    Button btnAdd;
    TextView editTitle, editVolume, editIssue;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comics;

//    //FIREBASE USER
//    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comic_page);

        btnAdd = (Button) findViewById(R.id.addnewcomic);

        editTitle = (TextView) findViewById(R.id.edittextTitle);
        editVolume = (TextView) findViewById(R.id.edittextVolume);
        editIssue = (TextView) findViewById(R.id.edittextIssue);

        //TO ENABLE BUTTON AFTER TEXT IS WRITTEN

        editTitle.addTextChangedListener(loginTextWatch);
        editVolume.addTextChangedListener(loginTextWatch);
        editIssue.addTextChangedListener(loginTextWatch);

//
//        //FIREBASE USER
//
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//                if (firebaseUser != null) {
//                    userId = firebaseUser.getUid();
//                }
//            }
//        };

        
        //GET DATA AND PASS TO INTENT
        Bundle bundle = getIntent().getExtras();
        final String username_2 = bundle.getString("username_1");

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comics = database.getReference().child("Comics").child(username_2);


        //BUTTON


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Comic newComic = new Comic(editTitle.getText().toString().trim(),
                                                 editVolume.getText().toString().trim(),
                                                 editIssue.getText().toString().trim());
                String comic_name = editTitle.getText().toString().trim() + " Volume " + editVolume.getText().toString().trim() + " Issue " + editIssue.getText().toString().trim();

                comics.child(comic_name).setValue(newComic).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Added Successfully !", Toast.LENGTH_LONG).show();
                    }
                });

                openHomePageActivity(username_2);

            }
        });

    }


    //FOR OPENINIG OTHER PAGE

    private void openHomePageActivity(String username_2){
        Intent s = new Intent(getApplicationContext(), HomePageLoggedInActivity.class);

        //PASS DATA
        String username = username_2;
        s.putExtra("username", username);

        startActivity(s);
    }


    //TO ENABLE BUTTON AFTER TEXT IS WRITTEN

    private TextWatcher loginTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String titleInput = editTitle.getText().toString().trim();
            String volumeInput = editVolume.getText().toString().trim();
            String issueInput = editIssue.getText().toString().trim();

            btnAdd.setEnabled(!titleInput.isEmpty()
                    && !volumeInput.isEmpty()
                    && !issueInput.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
