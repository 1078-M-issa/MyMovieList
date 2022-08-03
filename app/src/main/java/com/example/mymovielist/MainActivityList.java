package com.example.mymovielist;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityList extends AppCompatActivity {

    Button Show;
    ListView lv;
    ArrayList<Movie> alMovies;
    CustomAdapter caAdapter;
    Movie selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Show = findViewById(R.id.slist);
        lv = findViewById(R.id.Lv);
        alMovies = new ArrayList<>();


        DBhelp db = new DBhelp(MainActivityList.this);
        alMovies =  db.getAllMovie();

        caAdapter = new CustomAdapter(this,R.layout.row,alMovies);
        lv.setAdapter(caAdapter);

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                for (int i = 0; i<alMovies.size(); i++) {

                    if (alMovies.get(i).getRatings().equalsIgnoreCase("pg13")) {
                        selected = alMovies.get(i);
                        alMovies.clear();
                        alMovies.add(selected);
                    }

                }
                lv.setAdapter(caAdapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(MainActivityList.this,
                        MainActivityEdit.class);
                Movie object = alMovies.get(position);
                i.putExtra("AAA", object.getTitle());
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        DBhelp db = new DBhelp(MainActivityList.this);
        alMovies =  db.getAllMovie();
        caAdapter = new CustomAdapter(this,R.layout.row,alMovies);
        lv.setAdapter(caAdapter);
    }
}