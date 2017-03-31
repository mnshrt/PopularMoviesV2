package com.manish.android.popularmoviesv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView synopsisTextView,dateTextView,originalTitleTextView,ratingsTextView;
    private ImageView moviePosterImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

      //List<Movie> clickedMovies = getIntent().getParcelableArrayListExtra("clickedMovies");
        Movie clickedMovie = getIntent().getParcelableExtra("clickedMovie");
        //int position =getIntent().getIntExtra("position",0);
        //Toast.makeText(getApplicationContext(),clickedMovies.get(position).toString(), Toast.LENGTH_LONG).show();

       // Log.i("detailactivity",clickedMovies.get(position).toString());
        //Log.i("checkout",clickedMovie.toString());


        synopsisTextView= (TextView) findViewById(R.id.movie_synopsis_text_view);
        dateTextView= (TextView) findViewById(R.id.movie_release_date_text_view);
        originalTitleTextView= (TextView) findViewById(R.id.movie_original_title_text_view);
        ratingsTextView= (TextView) findViewById(R.id.movie_ratings_text_view);

        moviePosterImageView= (ImageView) findViewById(R.id.movie_poster_image_view);



        originalTitleTextView.setText(clickedMovie.getMovieOriginalTitle());
        dateTextView.setText(clickedMovie.getMovieDate());
        ratingsTextView.setText(Double.toString(clickedMovie.getMovieRating()));
        synopsisTextView.setText(clickedMovie.getMoviePlotSynopsis());

        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+clickedMovie.getMoviePosterPath()).into(moviePosterImageView);

    }
}
