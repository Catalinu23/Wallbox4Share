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
        versionsList.add(new Versions("How much do I have to pay for a full charge?", "The owner of the wallbox will decide the price."));
        versionsList.add(new Versions("Where can I contact you?", "You can contact us on Facebook, Instagram or via email."));
        versionsList.add(new Versions("How can I access a wallbox?", "Go to map and there you will find waypoints that indicate all the wallboxes. Just tap on them."));
        versionsList.add(new Versions("Do you have a section for feedback?", "It will be available soon. Until then, don't hesitate to write us on emaill."));
        versionsList.add(new Versions("How many chargings are available in the free version?", "You can charge your car 5 times for free."));
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