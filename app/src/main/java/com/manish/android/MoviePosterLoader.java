package com.manish.android.popularmoviesv2;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by emkayx on 06-03-2017.
 */

public class MoviePosterLoader extends AsyncTaskLoader<List<Movie>> {


    private String mUrl;
     MoviePosterLoader(Context context,String mUrl) {
        super(context);
        this.mUrl=mUrl;
    }

    @Override
    public List<Movie> loadInBackground() {


      List<Movie> movies = QueryUtils.fetchMovieData(mUrl);

      return movies;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
