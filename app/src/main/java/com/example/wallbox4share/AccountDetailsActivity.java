package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        Button editButton = findViewById(R.id.editDetailsButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDetailsActivity();
            }
        });
    }

    private void openEditDetailsActivity() {
        Intent intent = new Intent(this, EditAccountActivity.class);
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