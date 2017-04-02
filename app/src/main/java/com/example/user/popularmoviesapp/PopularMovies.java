package com.example.user.popularmoviesapp;

import android.media.Image;

/**
 * Created by user on 3/30/2017.
 */

public class PopularMovies
{

    private String mOriginalTitle;
    private String mMovieImage;
    private String mOverview;
    private double mVoteAverage;
    private String mReleaseDate;

    public PopularMovies(String mOriginalTitle,String mMovieImage,String mOverview,
                         double mVoteAverage,String mReleaseDate)
    {
        this.mOriginalTitle = mOriginalTitle;
        this.mMovieImage = mMovieImage;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
    }

    /*Method to  get the Original Title  * @return = String  */

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    /*Method to  get the MovieImage * @return = Image  */

    public String getMovieImage() {
        return mMovieImage;
    }

    /*Method to get the Overview * @return = String */

    public String getOverview() {
        return mOverview;
    }

    /*Method to  get the VoteAverage * @return = double  */

    public double getVoteAverage() {
        return mVoteAverage;
    }

    /*Method to  get the Release Date   * @return = String  */

    public String getReleaseDate() {
        return mReleaseDate;
    }
}
