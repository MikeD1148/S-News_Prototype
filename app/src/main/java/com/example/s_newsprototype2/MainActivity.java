package com.example.s_newsprototype2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends MenuConstant {

    //Initialise
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Adapter adapter;

    //Initialise List to add Article information to
    List<ViewStructure> articleList;

    //Initialise saved switches positions
    SharedPreferences sharedPreferences;
    boolean Inspirational;
    boolean Controversial;
    boolean Provoking;
    boolean Amusing;
    boolean Sad;
    boolean Informative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve saved state of Preference Switches the default state is not toggled so a the User can choose what Articles to see when they first launch the app
        sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Inspirational = sharedPreferences.getBoolean("inspirationValue", false);
        Controversial = sharedPreferences.getBoolean("controversyValue", false);
        Provoking = sharedPreferences.getBoolean("provokeValue", false);
        Amusing = sharedPreferences.getBoolean("amuseValue", false);
        Sad = sharedPreferences.getBoolean("sadValue", false);
        Informative = sharedPreferences.getBoolean("informationValue", false);

        //Loading Article Data into Recycled View
        initData();
        //Creating the Recycled View with the populated data
        initRecyclerView();

    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(articleList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        //Create the list to store Article details
        articleList = new ArrayList<>();

        try {
            //Use the Application Context to store current news feed for the lifecycle of the App
            Context context = getApplicationContext();

            //Create file in the applications private file system
            File file = new File(context.getFilesDir(), "articles.csv");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] row;

            //Create Hashmap of Switches and their States
            Map<String, Boolean> SwitchState = new HashMap<>();
            SwitchState.put("Inspirational", Inspirational);
            SwitchState.put("Controversial", Controversial);
            SwitchState.put("Provoking", Provoking);
            SwitchState.put("Amusing", Amusing);
            SwitchState.put("Sad", Sad);
            SwitchState.put("Informative", Informative);

            //Read data from saved file
            while ((row = csvReader.readNext()) != null) {
                String url = row[0];
                String title = row[1];
                String date = row[2];
                String description = row[3];
                String articleUrl = row[4];

                //Check for matching key and check if key is enabled
                if (SwitchState.containsKey(description) && SwitchState.get(description)) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


}