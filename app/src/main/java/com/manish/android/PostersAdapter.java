package com.manish.android.popularmoviesv2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emkayx on 06-03-2017.
 */

public class PostersAdapter extends ArrayAdapter<Movie> {
Context context;
    List<Movie> movies;


    public PostersAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
        this.context=context;
        movies=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridItemView = convertView;
        if(gridItemView==null){
            gridItemView= LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_item,parent,false);
        }
        //find the movie in the given position in the list of movies


        Movie currentMovie =movies.get(position);
        Log.i("PosterAdapter",currentMovie.getMoviePosterPath());

        //get the image view
       // TextView textView = (TextView) gridItemView.findViewById(R.id.poster);
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.poster);

        Picasso.with(gridItemView.getContext()).load("http://image.tmdb.org/t/p/w500/"+currentMovie.getMoviePosterPath()).into(imageView);
       // textView.setText("http://image.tmdb.org/t/p/w185/"+currentMovie.getPosterPath());
       // View view = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_item, null);
        //gridItemView.setMinimumHeight(MovieActivity.width/2);
        return gridItemView;

    }
}
