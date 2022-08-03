package com.example.mymovielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add, List;
    EditText Title, Year, Genre, Rating;
    ArrayList<Movie> AL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add =findViewById(R.id.addBut);
        List =findViewById(R.id.listBut);
        Title =findViewById(R.id.tinp1);
        Genre =findViewById(R.id.tinp2);
        Year =findViewById(R.id.tinum);
        Rating =findViewById(R.id.tinp3);
        AL = new ArrayList<>();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String T = Title.getText().toString().trim();
                String G = Genre.getText().toString().trim();
                int Y = Integer.parseInt(Year.getText().toString());
                String R = Rating.getText().toString().trim();

                DBhelp db = new DBhelp(MainActivity.this);
                long id = db.insertMovie(T,G,Y,R);

                Title.setText("");
                Genre.setText("");
                Year.setText("");
                Rating.setText("");

            }
        });

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivityList.class);
                startActivity(i);
            }
        });

    }
}