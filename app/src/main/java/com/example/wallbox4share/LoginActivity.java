package com.example.wallbox4share;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar aBar = getSupportActionBar();
        getSupportActionBar().hide();


        Button button = findViewById(R.id.goToTutorialButton);
        Button signInButton = findViewById(R.id.SignInButton);

        TextView goToSignUp = findViewById(R.id.noAccountTextView2);
        goToSignUp.setMovementMethod(LinkMovementMethod.getInstance());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTutorialActivity();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity();
            }
        });

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }

    private void openMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    private void openTutorialActivity() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}