package com.example.wallbox4share;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserMenuCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu_categories);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView accountDetailsButton = findViewById(R.id.buttonCategory1);
        TextView myCarButton = findViewById(R.id.buttonCategory2);
        TextView myWallBoxesButton = findViewById(R.id.buttonCategory3);
        TextView favouritesButton = findViewById(R.id.buttonCategory4);

        myCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyCarActivity();
            }
        });

        accountDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountDetailsActivity();
            }
        });

        myWallBoxesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyWallboxesActivity();
            }
        });
    }

    private void openAccountDetailsActivity() {
        Intent intent = new Intent(this, AccountDetailsActivity.class);
        startActivity(intent);
    }

    private void openMyCarActivity() {
        Intent intent = new Intent(this, MyCarActivity.class);
        startActivity(intent);
    }

    private void openMyWallboxesActivity() {
        Intent intent = new Intent(this, MyWallboxesActivity.class);
        startActivity(intent);
    }
}