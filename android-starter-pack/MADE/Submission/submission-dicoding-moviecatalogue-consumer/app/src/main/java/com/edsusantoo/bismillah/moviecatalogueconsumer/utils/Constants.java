package com.edsusantoo.bismillah.moviecatalogueconsumer.utils;

import android.net.Uri;

public class Constants {

    private static final String AUTHORITY = "com.edsusantoo.bismillah.moviecatalogue";
    private final static String TABLE_NAME_FAVORITE="favorites";
    private final static String TABLE_NAME_MOVIE="movie";
    public static final String TYPE_MOVIE = "movies";

    //content://com.edsusantoo.bismillah.moviecatalogue/favorite
    public static final Uri URI_FAVORITES = Uri.parse(
            "content://" + AUTHORITY + "/" + TABLE_NAME_FAVORITE);

    //content://com.edsusantoo.bismillah.moviecatalogue/movie
    public static final Uri URI_MOVIES = Uri.parse(
            "content://" + AUTHORITY + "/" + TABLE_NAME_MOVIE);
}
