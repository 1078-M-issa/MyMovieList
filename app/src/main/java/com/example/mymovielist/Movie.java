package com.example.mymovielist;

public class Movie {
    private int Id;
    private String Title;
    private String Genre;
    private int Year;
    private String Ratings;

    public Movie(int id, String title, String genre, int year, String ratings) {
        Id = id;
        Title = title;
        Genre = genre;
        Year = year;
        Ratings = ratings;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }
}
