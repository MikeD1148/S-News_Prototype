package com.example.s_newsprototype2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends MenuConstant {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ViewStructure> articleList;
    Adapter adapter;
    SharedPreferences sharedPreferences;
    boolean Grumpy;
    boolean Happy;
    boolean Glad;
    boolean Mad;
    boolean Nosey;
    boolean Joy;
    boolean Bashful;
    boolean Smurf;
    boolean Horror;
    boolean Confusion;
    boolean Relief;
    boolean Fear;
    boolean Awe;
    boolean Pain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Grumpy = sharedPreferences.getBoolean("grumpyValue", false);
        Happy = sharedPreferences.getBoolean("happyValue", false);
        Glad = sharedPreferences.getBoolean("gladValue", false);
        Mad = sharedPreferences.getBoolean("madValue", false);
        Nosey = sharedPreferences.getBoolean("noseyValue", false);
        Joy = sharedPreferences.getBoolean("joyValue", false);
        Bashful = sharedPreferences.getBoolean("bashfulValue", false);
        Smurf = sharedPreferences.getBoolean("smurfValue", false);
        Horror = sharedPreferences.getBoolean("horrorValue", false);
        Confusion = sharedPreferences.getBoolean("confusionValue", false);
        Relief = sharedPreferences.getBoolean("reliefValue", false);
        Fear = sharedPreferences.getBoolean("fearValue", false);
        Awe = sharedPreferences.getBoolean("aweValue", false);
        Pain = sharedPreferences.getBoolean("painValue", false);

        initData();
        initRecyclerView();

    }

    private void readFromFile() {
        try {
            InputStream inputStream = getAssets().open("articles.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                String url = nextLine[0];
                String title = nextLine[1];
                String date = nextLine[2];
                String description = nextLine[3];
                String articleUrl = nextLine[4];

                if (Grumpy && description.equals("Grumpy")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Happy && description.equals("Happy")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Glad && description.equals("Glad")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Mad && description.equals("Mad")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Nosey && description.equals("Nosey")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Joy && description.equals("Joy")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Bashful && description.equals("Bashful")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Smurf && description.equals("Smurf")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Horror && description.equals("Horror")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Confusion && description.equals("Confusion")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Relief && description.equals("Relief")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Fear && description.equals("Fear")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Awe && description.equals("Awe")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
                if (Pain && description.equals("Pain")) {
                    articleList.add(new ViewStructure(url, title, date, description, articleUrl));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
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

        articleList = new ArrayList<>();
        readFromFile();
    }


}