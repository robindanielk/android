package com.example.user.popularmoviesapp;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by user on 3/30/2017.
 */

public class DetailsActivity extends AppCompatActivity
{
    private PopularMovies pMovies;
    private TextView moviesTitle;
    private TextView moviesOverview;
    private ImageView moviePoster;
    private ImageView movieBackDrop;
    private TextView moviesReleaseDate;
    private TextView moviesVoteAverage;

    private static final String MOVIE_POSITION = "moviesPosition";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        if(getIntent() != null) {
            if (getIntent().hasExtra(MOVIE_POSITION)) {
                pMovies = getIntent().getParcelableExtra(MOVIE_POSITION);
            }
        }else
        {
            throw new IllegalArgumentException("Invalid position recieved to DetailsActivity");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.details_collapsing_toolbar);
        toolbarLayout.setTitle(pMovies.getOriginalTitle());

        movieBackDrop = (ImageView) findViewById(R.id.details_ct_back_drop);
        moviesTitle = (TextView) findViewById(R.id.details_tv_title);
        moviesOverview = (TextView) findViewById(R.id.details_tv_overview);
        moviePoster = (ImageView) findViewById(R.id.details_cv_movie_image);
        moviesReleaseDate = (TextView) findViewById(R.id.details_tv_release_date);
        moviesVoteAverage = (TextView) findViewById(R.id.details_tv_ratings);

        moviesTitle.setText(pMovies.getOriginalTitle());
        moviesOverview.setText(pMovies.getOverview());
        moviesReleaseDate.setText(pMovies.getReleaseDate());
        moviesVoteAverage.setText(String.valueOf(pMovies.getVoteAverage()));

        Picasso.with(this).load(pMovies.getBackDropImageUrl()).into(movieBackDrop);
        Picasso.with(this).load(pMovies.getMovieImage()).into(moviePoster);

        FloatingActionButton flbShare = (FloatingActionButton) findViewById(R.id.details_fab_share);
        flbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
