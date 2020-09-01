package com.example.comicsprime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.comicsprime.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends Fragment {

    private static final String TAG = "SignUpActivity";

    EditText editemail, editusername, editpassword, editrollno, editphone;
    Button btnsignup;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference users;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);


        //EDITS

        editemail = view.findViewById(R.id.email);
        editusername = view.findViewById(R.id.username);
        editpassword = view.findViewById(R.id.password);
        editphone = view.findViewById(R.id.phone);

        btnsignup = (Button) view.findViewById(R.id.signup);


        //FIREBASE

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(editemail.getText().toString(),
                        editusername.getText().toString(),
                        editpassword.getText().toString(),
                        editphone.getText().toString());

//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA
//BUTTON NHI CHAL RHA

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists()) {
                            Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_LONG).show();
                        } else{
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(getActivity(), "Registered Successfully !", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //NULL
                    }
                });


            }
        });


        return view;
    }
}
