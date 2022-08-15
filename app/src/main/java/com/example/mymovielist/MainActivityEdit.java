package com.example.mymovielist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityEdit extends AppCompatActivity {

    Button Edit, Delete, Cancel;
    EditText Title, Year, Genre, Rating;
    ArrayList<Movie> AL;
    Movie object;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);

        Edit = findViewById(R.id.Edit);
        Delete = findViewById(R.id.Delete);
        Cancel = findViewById(R.id.Cancel);
        Title = findViewById(R.id.tinp1);
        Genre = findViewById(R.id.tinp2);
        Year = findViewById(R.id.tinum);
        AL = new ArrayList<>();
        spin = findViewById(R.id.Sp2);

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
       // Rating.setText(object.getRatings());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ratings_array, android.R.layout.simple_spinner_item);

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


        spin.setSelection(adapter.getPosition(object.getRatings()));

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bound = 0;

                if (TextUtils.isEmpty(Title.getText())) {
                    Title.setError("Title name is empty");

                } else if (TextUtils.isEmpty(Genre.getText())) {
                    Genre.setError("Genre name is empty");

                } else if (TextUtils.isEmpty(Year.getText())) {
                    Year.setError("Year is empty");

                } else if (Integer.parseInt(Year.getText().toString()) > 2024 || Integer.parseInt(Year.getText().toString()) < 1950) {
                    Year.setError("Year set is outside year boundary");
                    bound = 1;

                } else if (!TextUtils.isEmpty(Title.getText()) && !TextUtils.isEmpty(Genre.getText()) && (!TextUtils.isEmpty(Year.getText()) && bound != 1)) {



                    AlertDialog.Builder ABuilder = new AlertDialog.Builder(MainActivityEdit.this);

                    ABuilder.setTitle("Apply Changes?");
                    ABuilder.setMessage("Are you sure you want to apply these cahnges?");
                    ABuilder.setCancelable(false);

                    ABuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AlertDialog mydialog = ABuilder.create();
                            mydialog.show();

                            DBhelp dbh = new DBhelp(MainActivityEdit.this);
                            object.setTitle(Title.getText().toString());
                            object.setGenre(Genre.getText().toString());
                            object.setYear(Integer.valueOf(Year.getText().toString()));
                            object.setRatings(spin.getSelectedItem().toString().trim());
                            dbh.updateMovie(object);
                            dbh.close();
                            finish();
                        }
                    });
                    ABuilder.setNegativeButton("CANCEL", null);

                    AlertDialog mydialog = ABuilder.create();
                    mydialog.show();

                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ABuilder = new AlertDialog.Builder(MainActivityEdit.this);

                ABuilder.setTitle("DELETE Movie");
                ABuilder.setMessage("Are you sure you want to delete the movie \n" + object.getTitle().toString());
                ABuilder.setCancelable(false);

                ABuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBhelp dbh = new DBhelp(MainActivityEdit.this);
                        dbh.deleteMovie(object.getId());
                        finish();
                    }
                });
                ABuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                AlertDialog mydialog = ABuilder.create();
                mydialog.show();


            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ABuilder = new AlertDialog.Builder(MainActivityEdit.this);

                ABuilder.setTitle("Cancel Changes?");
                ABuilder.setMessage("Are you sure you want to discard changes?");
                ABuilder.setCancelable(false);

                ABuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                ABuilder.setNegativeButton("CANCEL", null);

                AlertDialog mydialog = ABuilder.create();
                mydialog.show();
            }
        });

    }
}