package com.example.wallbox4share;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.sql.DriverManager.println;

public class MyCarActivity extends AppCompatActivity {

    ArrayList<String> carsArray = new ArrayList<String>();
    String chosenCar="Tesla Model S";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        getSupportActionBar().setTitle("My Car");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carsArray.add("Tesla Model S");
        carsArray.add("Tesla Model 3");
        carsArray.add("Tesla Model X");
        carsArray.add("Tesla Model Y");

        ArrayList teslaModelS = new ArrayList();
        teslaModelS.add("Tesla Charger");
        teslaModelS.add("Tesla Super-Charger");
        ListView compatibleWallboxesListView = findViewById(R.id.compatibleWallboxesListView);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.custom_list_view_layout, teslaModelS);
        compatibleWallboxesListView.setAdapter(arrayAdapter);

        Button changeCarButton = findViewById(R.id.changeCarButton);
        TextView carModelText = findViewById(R.id.carModelText);

        Context mContext = this;

        changeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(mContext);
                carModelText.setText(chosenCar);
            }
        });
    }

    public void openDialog(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.change_car_dialog_layout, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(dialogView);

        Spinner spinner = dialogView.findViewById(R.id.spinner);
        Button okButton = dialogView.findViewById(R.id.okButton);

        spinner.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, carsArray));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                chosenCar = spinner.getSelectedItem().toString();

            }
        });
        alertDialog.show();
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