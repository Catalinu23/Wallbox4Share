package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MyWallboxesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallboxes);
        getSupportActionBar().setTitle("My Wallboxes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openUserMenu();
    }

    private void openUserMenu() {
        Intent intent = new Intent(this, UserMenuCategoriesActivity.class);
        startActivity(intent);
    }
}