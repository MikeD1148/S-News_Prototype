package com.example.s_newsprototype2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ViewStructure> articleList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                articleList.add(new ViewStructure(url, title, date, description, articleUrl));
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