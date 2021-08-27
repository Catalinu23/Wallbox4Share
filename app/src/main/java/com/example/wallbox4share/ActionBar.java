package com.example.wallbox4share;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class ActionBar extends AppCompatActivity {

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

}
