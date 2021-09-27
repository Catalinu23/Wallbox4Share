package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.LinkMovementMethod;
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

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        Button SignUpButton = findViewById(R.id.signUpButton);
        EditText editTextUsername = findViewById(R.id.editTextTextName);
        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        EditText editTextConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);

        TextView goToLogIn = findViewById(R.id.hasAccountTextView4);
        goToLogIn.setMovementMethod(LinkMovementMethod.getInstance());

        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });

        String url = "http://10.0.2.2:8080/users/add";

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, MapActivity.class);
                startActivity(intent);

                Editable username = editTextUsername.getText();
                Editable email = editTextEmail.getText();
                Editable password = editTextPassword.getText();
                Editable confirmPassword = editTextConfirmPassword.getText();
                User user = new User(username.toString(), email.toString(), password.toString());
                try {
                    jsonObject.put("username", user.getUsername());
                    jsonObject.put("email", user.getEmail());
                    jsonObject.put("password", user.getPassword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        (Response.Listener<JSONObject>) response -> {

                            try {
                                Toast.makeText(SignUpActivity.this, response.getString("id"), Toast.LENGTH_SHORT).show();
                                Long id = Long.parseLong(response.getString("id"));
                                SharedPreferences sharedPreferences = getSharedPreferences("id_preferences", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("id", id);
                                editor.apply();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, (Response.ErrorListener) error -> {
                    //Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                });

                queue.add(stringRequest);


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openLogInActivity();
    }

    private void openLogInActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void openMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}