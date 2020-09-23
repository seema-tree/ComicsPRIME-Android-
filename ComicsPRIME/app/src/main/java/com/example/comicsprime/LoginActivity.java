package com.example.comicsprime;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class LoginActivity extends Fragment {

    private static final String TAG = "LoginActivity";

    EditText editusername, editpassword;
    Button btnLogin;

    //FIREBASE

    FirebaseDatabase database;
    DatabaseReference users;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);


        //ANIMATION FOR BACKGROUND
        LinearLayout layout = view.findViewById(R.id.loginLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //EDITS

        editusername = view.findViewById(R.id.loginusername);
        editpassword = view.findViewById(R.id.loginpassword);

        btnLogin = (Button) view.findViewById(R.id.login);

        //TO ENABLE BUTTON AFTER TEXT IS WRITTEN

        editusername.addTextChangedListener(loginTextWatch);
        editpassword.addTextChangedListener(loginTextWatch);

        //FIREBASE


        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(editusername.getText().toString(),
                        editpassword.getText().toString());

            }
        });



        return view;
    }


    //FUNCTION TO CHECK USERNAME AND PASSWORD

    private void signin(final String username, final String password) {

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    User login = dataSnapshot.child(username).getValue(User.class);
                    if(login.getPassword().equals(password)){
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                        openLoggedInActivity();
                    } else{
                        Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //FOR OPENINIG OTHER PAGE

    private void openLoggedInActivity(){
        Intent s = new Intent(getActivity(), HomePageLoggedInActivity.class);

        //PASS DATA
        String username = editusername.getText().toString().trim();
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

            String usernameInput = editusername.getText().toString().trim();
            String passwordInput = editpassword.getText().toString().trim();

            btnLogin.setEnabled(!usernameInput.isEmpty()
                    && !passwordInput.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
