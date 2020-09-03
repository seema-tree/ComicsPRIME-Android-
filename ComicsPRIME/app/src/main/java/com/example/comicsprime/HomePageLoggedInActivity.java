package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePageLoggedInActivity extends AppCompatActivity {

    private static final String TAG = "HomePageLoggedInActivity";

    FloatingActionButton addComicButton;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeloggedin_page);

        //EDITS

        addComicButton = (FloatingActionButton) findViewById(R.id.addComic);

        addComicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), AddComicsActivity.class);

                startActivity(s);
            }
        });

    }


}
