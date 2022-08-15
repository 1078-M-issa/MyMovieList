package com.example.mymovielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add, List;
    EditText Title, Year, Genre, Rating;
    ArrayList<Movie> AL;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.sp1);
        Add =findViewById(R.id.addBut);
        List =findViewById(R.id.listBut);
        Title =findViewById(R.id.tinp1);
        Genre =findViewById(R.id.tinp2);
        Year =findViewById(R.id.tinum);
        AL = new ArrayList<>();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ratings_array, android.R.layout.simple_spinner_item);





        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bound = 0;


                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String V;
                        switch (i) {
                            case 0:
                                V = "g";
                                break;
                            case 1:
                                V = "m18";
                                break;
                            case 2:
                                V = "nc16";
                                break;
                            case 3:
                                V = "pg";
                                break;
                            case 4:
                                V = "pg13";
                                break;
                            case 5:
                                V = "r21";
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                String R = spin.getSelectedItem().toString();

                if (TextUtils.isEmpty(Title.getText())) {
                    Title.setError("Title name is empty");

                    } else if (TextUtils.isEmpty(Genre.getText())) {
                        Genre.setError("Genre name is empty");

                    } else if (TextUtils.isEmpty(Year.getText())) {
                        Year.setError("Year is empty");

                    } else if (Integer.parseInt(Year.getText().toString()) > 2024 || Integer.parseInt(Year.getText().toString()) < 1950 ) {
                        Year.setError("Year set is outside year boundary");
                        bound = 1;

                    } else if (!TextUtils.isEmpty(Title.getText()) && !TextUtils.isEmpty(Genre.getText()) && (!TextUtils.isEmpty(Year.getText()) && bound != 1 )) {

                        String T = Title.getText().toString().trim();
                        String G = Genre.getText().toString().trim();
                        int Y = Integer.parseInt(Year.getText().toString());

                        DBhelp db = new DBhelp(MainActivity.this);
                        long id = db.insertMovie(T,G,Y,R);



                    Title.setText("");
                    Genre.setText("");
                    Year.setText("");

                }

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