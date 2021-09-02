package com.example.wallbox4share;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar aBar = getSupportActionBar();
        getSupportActionBar().hide();


        String url = "http://10.0.2.2:8080/user/1";

        EditText editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                (Response.Listener<JSONObject>) response -> {
                    User user = gson.fromJson(response.toString(), User.class);
                    editTextEmailAddress.setText(user.getUsername());
                    editTextPassword.setText(user.getPassword());
                    //Toast.makeText(this, "entered succesfully!", Toast.LENGTH_SHORT).show();
                }, (Response.ErrorListener) error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);

        Button button = findViewById(R.id.goToTutorialButton);
        Button signInButton = findViewById(R.id.SignInButton);
        TextView goToSignUp = findViewById(R.id.noAccountTextView2);

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

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void openTutorialActivity() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}