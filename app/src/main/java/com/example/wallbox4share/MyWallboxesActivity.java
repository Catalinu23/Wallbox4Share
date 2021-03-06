package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MyWallboxesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[], s2[];
    Location userLocation;
    JSONObject saveObject;
    Location location;
    Long id;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallboxes);
        getSupportActionBar().setTitle("My Wallboxes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycleView);
        s1 = getResources().getStringArray(R.array.my_wallboxes);
        s2 = getResources().getStringArray(R.array.description);

        MyWallboxesAdapter myWallboxesAdapter = new MyWallboxesAdapter(this, s1, s2);
        recyclerView.setAdapter(myWallboxesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addWallboxButton = findViewById(R.id.addWallboxButton);


        SharedPreferences sharedPreferences = getSharedPreferences("id_preferences", Activity.MODE_PRIVATE);
        id = sharedPreferences.getLong("id", -1L);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);



        addWallboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();

            }
        });

    }

    private void showAlertDialog() {
        Dialog dialog = new Dialog(MyWallboxesActivity.this);
        dialog.setContentView(R.layout.add_wallbox_dialog);
        EditText editWallboxDescription = dialog.findViewById(R.id.editWallboxDescription);
        EditText editPhoneNumber = dialog.findViewById(R.id.editPhone);
        Button addButton = dialog.findViewById(R.id.addWallboxDialogButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:8080/wallboxes/add";

                RequestQueue queue = Volley.newRequestQueue(MyWallboxesActivity.this);
                Gson gson = new Gson();

                Wallbox wallbox = new Wallbox(id, location.getLatitude(), location.getLongitude(),
                        editPhoneNumber.getText().toString(), editWallboxDescription.getText().toString());
                JSONObject wallboxObject = new JSONObject();
                try {
                    wallboxObject.put("owner_id", wallbox.getOwner_id());
                    wallboxObject.put("latitude", wallbox.getLatitude());
                    wallboxObject.put("longitude", wallbox.getLongitude());
                    wallboxObject.put("phone_number", wallbox.getPhone_number());
                    wallboxObject.put("description", wallbox.getDescription());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                saveObject = new JSONObject();
                try {
                    //TODO: get current user id
                    saveObject.put("userId", wallbox.getOwner_id());
                    saveObject.put("wallbox", wallboxObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, saveObject,
                        (Response.Listener<JSONObject>) response -> {

                            //Toast.makeText(this, "entered succesfully!", Toast.LENGTH_SHORT).show();
                        }, (Response.ErrorListener) error -> {
                    Toast.makeText(MyWallboxesActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                });

                queue.add(stringRequest);
                dialog.dismiss();
            }
        });
        dialog.show();
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