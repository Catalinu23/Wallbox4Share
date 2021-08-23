package com.example.wallbox4share;

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

        TextView accountDetailsButton = findViewById(R.id.textViewCategory1);
        TextView myCarButton = findViewById(R.id.textViewCategory2);
        TextView myWalloxesButton = findViewById(R.id.textViewCategory3);
        TextView favouritesButton = findViewById(R.id.textViewCategory4);

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
    }

    private void openAccountDetailsActivity() {
        Intent intent = new Intent(this, AccountDetailsActivity.class);
        startActivity(intent);
    }

    private void openMyCarActivity() {
        Intent intent = new Intent(this, MyCarActivity.class);
        startActivity(intent);
    }
}