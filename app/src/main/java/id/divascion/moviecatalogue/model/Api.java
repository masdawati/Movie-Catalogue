package id.divascion.moviecatalogue.model;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

import id.divascion.moviecatalogue.BuildConfig;

public class Api {
    private static final String API_KEY = BuildConfig.MOVIEDB_API_KEY;
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String ID = "api_key";

    public static URL getAiring() {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("3")
                .appendPath("tv")
                .appendPath("airing_today")
                .appendQueryParameter(ID, API_KEY)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "1")
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL getPopular() {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("3")
                .appendPath("tv")
                .appendPath("popular")
                .appendQueryParameter(ID, API_KEY)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "1")
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL getTopRated() {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("3")
                .appendPath("tv")
                .appendPath("top_rated")
                .appendQueryParameter(ID, API_KEY)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "1")
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL getCredits(String id) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("3")
                .appendPath("tv")
                .appendPath(id)
                .appendPath("credits")
                .appendQueryParameter(ID, API_KEY)
                .appendQueryParameter("language", "en-US")
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}


