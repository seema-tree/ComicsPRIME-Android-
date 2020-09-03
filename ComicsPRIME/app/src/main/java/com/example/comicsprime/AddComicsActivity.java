package com.example.comicsprime;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddComicsActivity extends AppCompatActivity {

    private static final String TAG = "AddComicsActivity";

    Button btnAdd;
    TextView editTitle, editVolume, editIssue;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference comics;

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

        //FIREBASE

        database = FirebaseDatabase.getInstance();
        comics = database.getReference("Comics");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
