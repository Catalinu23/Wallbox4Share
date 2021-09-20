package com.example.wallbox4share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);
        getSupportActionBar().setTitle("FAQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewFAQ);

        iniData();
        setRecyclerView();

    }

    private void setRecyclerView() {
        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void iniData() {
        versionsList = new ArrayList<>();
        versionsList.add(new Versions("Cat trebuie sa platesc pentru o incarcare plina?", "Pretul unei incarcari complete sau partiale poate fii stabilit cu detinatorul in sine."));
        versionsList.add(new Versions("Unde va putem contacta?", "Ne puteti contacta prin facebook , email si Instagram."));
        versionsList.add(new Versions("Cum sa accesez un wallbox?", "In ecranul principal , exista cerculete mici ce marcheaza locatiile unui wallbox , apasand pe ele , le puteti accesa."));
        versionsList.add(new Versions("How?", "Description"));
        versionsList.add(new Versions("How?", "Description"));
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