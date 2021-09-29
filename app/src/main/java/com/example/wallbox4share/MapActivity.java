package com.example.wallbox4share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private Map<Marker, Wallbox> markersMap = new HashMap<Marker, Wallbox>();
    User auxUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userProfilePictureButton:
                startActivity(new Intent(this, UserMenuCategoriesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void zoomToUserLocation() {
        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }

        displayWallboxes();
        zoomToUserLocation();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapActivity.this, R.style.BottomSheetDialog);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                TextView wallboxNameTextView = bottomSheetDialog.findViewById(R.id.wallboxNameTextView);
                TextView ownerTextView = bottomSheetDialog.findViewById(R.id.ownerTextView);
                TextView phoneTextView = bottomSheetDialog.findViewById(R.id.phoneTextView);
                wallboxNameTextView.setText(marker.getTitle());
                phoneTextView.setText(markersMap.get(marker).getPhone_number());
                ownerTextView.setText((markersMap.get(marker).getDescription()));
                //Toast.makeText(MapActivity.this, markersMap.get(marker).getPhone_number(), Toast.LENGTH_SHORT).show();

                bottomSheetDialog.show();
                return false;
            }
        });
    }


    private void displayWallboxes() {
        //MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(34.024212, -118.496475)).title("Elon Musk's Wallbox");
        //mMap.addMarker(markerOptions);
        //mMap.addMarker(new MarkerOptions().position(new LatLng(123d, 40d)).title("User123's Wallbox"));
        String url = "http://10.0.2.2:8080/wallboxes/";
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wallbox>>() {}.getType();

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                (Response.Listener<JSONArray>) response -> {
                    //Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                    List<Wallbox> wallboxes = gson.fromJson(response.toString(), type);
                    for(Wallbox wallbox: wallboxes) {
                        try {
                            Long id = wallbox.getOwner_id();
                            String userUrl = "http://10.0.2.2:8080/user/" + id.toString();
                            RequestQueue userQueue = Volley.newRequestQueue(this);
                            Gson userGson = new Gson();
                            JsonObjectRequest userStringRequest = new JsonObjectRequest(Request.Method.GET, userUrl, null,
                                    (Response.Listener<JSONObject>) userResponse -> {
                                        User user = gson.fromJson(userResponse.toString(), User.class);
                                        LatLng latLng = new LatLng(wallbox.getLatitude(), wallbox.getLongitude());
                                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(user.getUsername() + "'s Wallbox").icon(BitmapDescriptorFactory.defaultMarker((float) 190.5));
                                        Marker marker = mMap.addMarker(markerOptions);
                                        markersMap.put(marker, wallbox);
                                    }, (Response.ErrorListener) error -> {
                                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                            });

                            userQueue.add(userStringRequest);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, (Response.ErrorListener) error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);
    }



    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            }
        } else {
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public User getUserById(Long id) {
        String url = "http://10.0.2.2:8080/user/" + id.toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                (Response.Listener<JSONObject>) response -> {
                    User user = gson.fromJson(response.toString(), User.class);
                    auxUser = user;
                }, (Response.ErrorListener) error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);
        return auxUser;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}