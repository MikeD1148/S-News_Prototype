package com.example.s_newsprototype2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

//Menu Super Class
public class MenuConstant extends AppCompatActivity {

    boolean darkMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Action Bar Colour
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey, getTheme())));

        //Retrieve last used theme from SharedPreferences
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        darkMode = sharedPreferences.getBoolean("dark", false);

        if (darkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                //Make connection to server and retrieve up to date news articles
                NetworkConnection newConnection = new NetworkConnection("localhost", 9999, this);
                newConnection.start();
            case R.id.home:
                //End Current Activity and Open Main Activity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                //Show the user the action has been registered
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.preference1:
                //Toggle theme from Dark to Light and save to SharedPreferences
                if (darkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("dark", false);
                }
                //Toggle theme from Light to Dark and save to SharedPreferences
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("dark", true);
                }
                editor.apply();
                //Show the user the action has been registered
                Toast.makeText(this, "Theme Changed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.preference2:
                //Open User Preferences Activity
                Intent intent2 = new Intent(this, UserPreferences.class);
                startActivity(intent2);
                //Show the user the action has been registered
                Toast.makeText(this, "Article Preferences", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
