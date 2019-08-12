package com.edsusantoo.bismillah.moviecatalogueconsumer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edsusantoo.bismillah.moviecatalogueconsumer.adapter.FavoriteMoviesAdapter;
import com.edsusantoo.bismillah.moviecatalogueconsumer.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogueconsumer.model.Movie;
import com.edsusantoo.bismillah.moviecatalogueconsumer.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_favorite)
    RecyclerView rvFavoritesMovie;
    private ArrayList<Movie> dataFavoriteMovie = new ArrayList<>();
    private FavoriteMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecycleView();

        getFavoriteMovie();
    }

    private ArrayList<Favorites> getFavorite() {
        ArrayList<Favorites> listFavorites = new ArrayList<>();
        Cursor cursor = getContentResolver().query(Constants.URI_FAVORITES, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                listFavorites.add(new Favorites(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex("movie_id")))));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listFavorites;
    }

    private ArrayList<Movie> getMovie() {
        ArrayList<Movie> listMovie = new ArrayList<>();
        Cursor cursor = getContentResolver().query(Constants.URI_MOVIES, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                listMovie.add(new Movie(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),
                        cursor.getString(cursor.getColumnIndex("movie_name")),
                        cursor.getString(cursor.getColumnIndex("movie_photo")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("type"))
                ));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listMovie;
    }

    private void setupRecycleView() {
        adapter = new FavoriteMoviesAdapter(this);
        rvFavoritesMovie.setLayoutManager(new LinearLayoutManager(this));
        rvFavoritesMovie.setAdapter(adapter);
        rvFavoritesMovie.setHasFixedSize(true);
    }

    private void getFavoriteMovie() {
        if (getFavorite() != null && getFavorite().size() != 0) {
            if (getMovie() != null && getFavorite().size() != 0) {
                for (Favorites dataFavorite : getFavorite()) {
                    for (Movie dataMovie : getMovie()) {
                        if (dataMovie.getMovieId() == dataFavorite.getMovieId()) {
                            if(dataMovie.getType().equals(Constants.TYPE_MOVIE)){
                                dataFavoriteMovie.add(dataMovie);
                            }
                        }
                    }
                }
            }
        }

        if (dataFavoriteMovie != null && dataFavoriteMovie.size() != 0) {
            adapter.addMovie(dataFavoriteMovie);
            adapter.refresh();
        }
    }

}
