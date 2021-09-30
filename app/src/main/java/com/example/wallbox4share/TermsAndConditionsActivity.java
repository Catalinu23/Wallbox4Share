package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TermsAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        getSupportActionBar().setTitle("Terms and Conditions");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openSettingsActivity();
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}