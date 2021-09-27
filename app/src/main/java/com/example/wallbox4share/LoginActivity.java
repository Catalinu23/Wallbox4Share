package com.example.wallbox4share;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar aBar = getSupportActionBar();
        getSupportActionBar().hide();

        EditText editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        Button button = findViewById(R.id.goToTutorialButton);
        Button signInButton = findViewById(R.id.SignInButton);
        TextView goToSignUp = findViewById(R.id.noAccountTextView2);

        SharedPreferences sharedPreferences = getSharedPreferences("id_preferences", Activity.MODE_PRIVATE);
        Long id = sharedPreferences.getLong("id", -1L);

        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
        if(id != -1) {

            String url = "http://10.0.2.2:8080/user/" + id.toString();

            RequestQueue queue = Volley.newRequestQueue(this);
            Gson gson = new Gson();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    (Response.Listener<JSONObject>) response -> {
                        User user = gson.fromJson(response.toString(), User.class);
                        editTextEmailAddress.setText(user.getUsername());
                        editTextPassword.setText(user.getPassword());
                        signInButton.performClick();

                        //editTextEmailAddress.setText(user.getUsername());
                        //editTextPassword.setText(user.getPassword());
                        //Toast.makeText(this, "entered succesfully!", Toast.LENGTH_SHORT).show();
                    }, (Response.ErrorListener) error -> {
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
            });

            queue.add(stringRequest);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmailAddress.getText().toString();
                String password = editTextPassword.getText().toString();


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

    private Boolean checkLogin(String email, String password) {
        String url = "http://10.0.2.2:8080/users/";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {}.getType();

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                (Response.Listener<JSONArray>) response -> {
                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    List<User> users = gson.fromJson(response.toString(), type);

                    for(User user: users) {
                        if(user.getEmail().equals(email) && user.getPassword().equals(password)) {

                        }
                    }

                }, (Response.ErrorListener) error -> {
            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
        });
        queue.add(stringRequest);
        return true;
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