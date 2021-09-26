package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView faqButton = findViewById(R.id.buttonFAQ);
        TextView aboutUsButton = findViewById(R.id.buttonAboutUs);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFAQActivity();
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutUsActivity();
            }
        });

    }

    private void openFAQActivity(){
        Intent intent = new Intent(this, FAQActivity.class);
        startActivity(intent);
    }

    private void openAboutUsActivity(){
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
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