package com.edsusantoo.bismillah.moviecatalogueconsumer.model;

public class Favorites {

    private int favoritesId;

    private int userId;

    private int movieId;

    public Favorites(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Favorites(int favoritesId, int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
        this.favoritesId = favoritesId;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
