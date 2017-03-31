package com.manish.android.popularmoviesv2;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private static final String TMDB_REQUEST_URL="http://api.themoviedb.org/3/movie";
    //popular?api_key=e58cd6903218bfa3ff6cfe1c12977fc9";

    private PostersAdapter postersAdapter;

    private ArrayList<Movie> movieList;
    private static final int MOVIEPOSTER_LOADER_INT=1;

    private TextView emptyTextView;
    private ProgressBar movie_progressbar;

    private GridView postersGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        postersGridView = (GridView) findViewById(R.id.poster_grid_view);


        postersAdapter= new PostersAdapter(this,new ArrayList<Movie>());

        emptyTextView = (TextView) findViewById(R.id.empty_view);
        postersGridView.setEmptyView(emptyTextView);

        postersGridView.setAdapter(postersAdapter);


        /*PosterLoadTask posterLoadTask = new PosterLoadTask();
        posterLoadTask.execute(TMDB_REQUEST_URL)*/


        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;*/


        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        movie_progressbar = (ProgressBar) findViewById(R.id.movie_progressbar);
        if(isConnected) {
        LoaderManager loaderManager = getLoaderManager();

        //initialise the loader passing in the parameters ,pass in the id defined ,for bundle pass in null and for loader
        //callbacks send the activity context


        loaderManager.initLoader(MOVIEPOSTER_LOADER_INT, null, this);
        }
        else{
            movie_progressbar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_internet_connection);
        }


        //item click response to move to detailActivity

        postersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsActivityIntent= new Intent(MovieActivity.this,MovieDetailActivity.class);

                if(!movieList.isEmpty()){
                Movie clickedMovie = movieList.get(position);


               detailsActivityIntent.putExtra("clickedMovie",clickedMovie);
               // detailsActivityIntent.putExtra("position",position);

                startActivity(detailsActivityIntent);}
                else{
    Log.i("MovieActivity","movieList not updated");
}
            }
        });
    }


    //Options menu creation


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {

       movie_progressbar.setVisibility(View.VISIBLE);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sort_by = sharedPrefs.getString(
                "listPref",
                getString(R.string.settings_order_by_default_value));

       // Log.i("findme",sort_by);


        Uri baseUri = Uri.parse(TMDB_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath(sort_by);

        // please add the api key here

        uriBuilder.appendQueryParameter("api_key","");

        return new MoviePosterLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        movie_progressbar.setVisibility(View.GONE);
        emptyTextView.setText(getString(R.string.no_movies_found));

        movieList=(ArrayList<Movie>) data;
        postersAdapter.clear();

            if (data !=null || !data.isEmpty()) {

                postersAdapter.addAll(data);

            }




}


    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        postersAdapter.clear();

    }

   /*private class PosterLoadTask extends AsyncTask<String,Void,List<Movie>> {


        @Override
        protected List<Movie> doInBackground(String... params)
        {
            if(params.length==0 || params[0]==null){
                return null;}

            return QueryUtils.fetchMovieData(params[0]);
        }


        @Override
        protected void onPostExecute(List<Movie> data) {


            postersAdapter.clear();
           *//* for(Movie m :data){
            Log.v("MainActivity",m.getPosterPath());}*//*
            if (data !=null || !data.isEmpty()) {
                postersAdapter.addAll(data);

            }

        }
    }*/
}
