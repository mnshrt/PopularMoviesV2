package com.manish.android.popularmoviesv2;


import android.os.Parcel;
import android.os.Parcelable;

class Movie implements Parcelable {
   private  String moviePosterPath;
   private String movieOriginalTitle;
    private String movieDate;
    private double movieRating;
    private String moviePlotSynopsis;


Movie(){}

 /*    Movie(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }*/

 public Movie(Parcel parcel){
     this.moviePosterPath=parcel.readString();
     this.movieOriginalTitle=parcel.readString();
     this.movieDate=parcel.readString();
     this.movieRating=parcel.readDouble();
     this.moviePlotSynopsis=parcel.readString();

 }
  public Movie( String movieOriginalTitle,String moviePosterPath, String movieDate,  String moviePlotSynopsis,double movieRating) {
        this.moviePosterPath = moviePosterPath;
        this.movieOriginalTitle = movieOriginalTitle;
         this.movieDate = movieDate;
        this.moviePlotSynopsis = moviePlotSynopsis;
        this.movieRating = movieRating;

    }
    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public String getMoviePlotSynopsis() {
        return moviePlotSynopsis;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public String getMovieDate() {
        return movieDate;
    }


     @Override
     public String toString() {
         return movieDate+" "+movieOriginalTitle+" "+moviePlotSynopsis+" "+movieRating+" "+moviePosterPath;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.moviePosterPath);
        dest.writeString(this.movieOriginalTitle);
        dest.writeString(this.movieDate);
        dest.writeDouble(this.movieRating);
        dest.writeString(this.moviePlotSynopsis);



    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
