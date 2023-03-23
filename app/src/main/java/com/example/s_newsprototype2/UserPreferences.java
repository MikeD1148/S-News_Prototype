package com.example.s_newsprototype2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class UserPreferences extends MenuConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        initSwitch(R.id.Grumpy, "grumpyValue");
        initSwitch(R.id.Happy, "happyValue");
        initSwitch(R.id.Glad, "gladValue");
        initSwitch(R.id.Mad, "madValue");
        initSwitch(R.id.Nosey, "noseyValue");
        initSwitch(R.id.Joy, "joyValue");
        initSwitch(R.id.Bashful, "bashfulValue");
        initSwitch(R.id.Smurf, "smurfValue");
        initSwitch(R.id.Horror, "horrorValue");
        initSwitch(R.id.Confusion, "confusionValue");
        initSwitch(R.id.Relief, "reliefValue");
        initSwitch(R.id.Fear, "fearValue");
        initSwitch(R.id.Awe, "aweValue");
        initSwitch(R.id.Pain, "painValue");

    }

    private void initSwitch(int switchID, String switchEmotion) {
        Switch switchLoop = findViewById(switchID);
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switchLoop.setChecked(sharedPreferences.getBoolean(switchEmotion, false));

        switchLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean(switchEmotion, isChecked);
                editor.apply();
            }
        });
    }
}