package com.example.mymovielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> MovieList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Movie> objects) {
        super (context,resource,objects);

        parent_context =context;
        layout_id = resource;
        MovieList = objects;
    }
    @Override
    public View getView (int position, View convertView,
                         ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id,parent,false);

        TextView Y = rowView.findViewById(R.id.Year);
        TextView T = rowView.findViewById(R.id.Title);
        TextView G = rowView.findViewById(R.id.Genre);
        ImageView ivMovie = rowView.findViewById(R.id.imageView);

        Movie details = MovieList.get(position);

        Y.setText(String.valueOf(details.getYear()));
        T.setText(details.getTitle());
        G.setText(details.getGenre());

        if (details.getRatings().trim().equalsIgnoreCase("g") ) {
            ivMovie.setImageResource(R.drawable.rating_g);
        } else if (details.getRatings().trim().equalsIgnoreCase("m18") ) {
            ivMovie.setImageResource(R.drawable.rating_m18);
        } else if (details.getRatings().trim().equalsIgnoreCase("nc16") ) {
            ivMovie.setImageResource(R.drawable.rating_nc16);
        } else if (details.getRatings().trim().equalsIgnoreCase("pg") ) {
            ivMovie.setImageResource(R.drawable.rating_pg);
        } else if (details.getRatings().trim().equalsIgnoreCase("pg13") ) {
            ivMovie.setImageResource(R.drawable.rating_pg13);
        } else if (details.getRatings().trim().equalsIgnoreCase("r21") ) {
            ivMovie.setImageResource(R.drawable.rating_r21);
        }





        return rowView;
    }
}
