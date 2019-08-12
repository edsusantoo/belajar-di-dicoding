package com.edsusantoo.bismillah.moviecatalogueconsumer.model;

public class Movie {

    private int movieId;

    private String movieName;

    private String moviePhoto;

    private String description;

    private String type;

    public Movie(int movieId, String movieName, String moviePhoto, String description, String type) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moviePhoto = moviePhoto;
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePhoto() {
        return moviePhoto;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
