package com.example.mymovielist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityEdit extends AppCompatActivity {

    Button Edit, Delete, Cancel;
    EditText Title, Year, Genre, Rating;
    ArrayList<Movie> AL;
    Movie object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);

        Edit = findViewById(R.id.Edit);
        Delete = findViewById(R.id.Delete);
        Cancel = findViewById(R.id.Cancel);
        Title = findViewById(R.id.tinp1);
        Genre = findViewById(R.id.tinp2);
        Rating = findViewById(R.id.tinp3);
        Year = findViewById(R.id.tinum);
        AL = new ArrayList<>();

        Intent i = getIntent();
        String data = (String) i.getSerializableExtra("AAA");

        DBhelp db = new DBhelp(this);
        AL = db.getAllMovie();

        for (int A = 0; A < AL.size(); A++) {
            if (AL.get(A).getTitle().equalsIgnoreCase(data)  ) {
                object = AL.get(A);
                break;
            }
        }

        Title.setText(object.getTitle());
        Genre.setText(object.getGenre());
        Year.setText(String.valueOf(object.getYear()));
        Rating.setText(object.getRatings());

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBhelp dbh = new DBhelp(MainActivityEdit.this);
                object.setTitle(Title.getText().toString());
                object.setGenre(Genre.getText().toString());
                object.setYear(Integer.valueOf(Year.getText().toString()));
                object.setRatings(Rating.getText().toString());
                dbh.updateMovie(object);
                dbh.close();
                finish();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelp dbh = new DBhelp(MainActivityEdit.this);
                dbh.deleteMovie(object.getId());
                finish();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}