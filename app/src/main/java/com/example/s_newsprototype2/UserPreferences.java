package com.example.s_newsprototype2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class UserPreferences extends MenuConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        //Initialise the Switch for each Category
        makeSwitch(R.id.Inspirational, "inspirationValue");
        makeSwitch(R.id.Controversial, "controversyValue");
        makeSwitch(R.id.Provoking, "provokeValue");
        makeSwitch(R.id.Amusing, "amuseValue");
        makeSwitch(R.id.Sad, "sadValue");
        makeSwitch(R.id.Informative, "informationValue");
    }

    private void makeSwitch(int switchID, String switchCategory) {
        //Get specific Switch
        Switch currentSwitch = findViewById(switchID);
        //Load the Switches saved state from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        //Set value of Switch to value stored in SharedPreferences if no value set false
        currentSwitch.setChecked(sharedPreferences.getBoolean(switchCategory, false));

        //Changes the value of the Switch in SharedPreferences if the value of the Switch is changed by the User
        currentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean toggled) {
                SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean(switchCategory, toggled);
                editor.apply();
            }
        });
    }
}