package com.example.comicsprime;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class UpcomingComicsActivity extends AppCompatActivity {

    private static final String TAG = "UpcomingComicsActivity";

    androidx.appcompat.widget.Toolbar toolbarUpcoming;

    //NAVIGATION BAR
    private DrawerLayout drawerUpcoming;
    NavigationView navigationViewUpcoming;

    //GET DATA
    String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_comics_page);

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        //TOOLBAR
        toolbarUpcoming = findViewById(R.id.toolbarUpcoming);
        toolbarUpcoming.setTitle("Upcoming Comics"); //SETTING OTHER TITLE
        setSupportActionBar(toolbarUpcoming);

        //NAVIGATION BAR
        drawerUpcoming = findViewById(R.id.drawer_layoutUpcoming);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerUpcoming, toolbarUpcoming, R.string.navigation_drawer_open, R.string.navigation_drawer_close); //1
        drawerUpcoming.addDrawerListener(toggle); //2
        toggle.syncState(); //NAVIGTION BAR DONE, NOW NAVIGATION SETTINGS (//1 and //2 for NAVIGATION TOGGLE BUTTON ON TOOLBAR)
        navigationViewUpcoming = findViewById(R.id.nav_viewUpcoming);
        navigationViewUpcoming.setCheckedItem(R.id.nav_upcoming);
        navigationViewUpcoming.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_mycomics:
                        Intent intent1 = new Intent(getApplicationContext(), HomePageLoggedInActivity.class);

                        //PASS DATA
                        intent1.putExtra("username", username);

                        startActivity(intent1);
                        break;
                    case R.id.nav_upcoming:
                        Intent intent2 = new Intent(getApplicationContext(), UpcomingComicsActivity.class);

                        //PASS DATA
                        intent2.putExtra("username", username);

                        startActivity(intent2);

                }

                drawerUpcoming.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    //SET WHAT BACK BUTTON DOES
    @Override
    public void onBackPressed() {
        if(drawerUpcoming.isDrawerOpen(GravityCompat.START)){
            drawerUpcoming.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
