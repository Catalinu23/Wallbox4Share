package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Button saveDetailsButton = findViewById(R.id.saveDetailsButton);
        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
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

}