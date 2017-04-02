package com.example.user.popularmoviesapp;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 3/30/2017.
 */

public class MainActivityFragment extends Fragment
{

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private TextView mEmptyView;
    private GridViewAdapter mAdapter;
    private ProgressBar mLoaderIndicator;
    private Boolean isConnected;

    private List<PopularMovies> popularMoviesList = new ArrayList<>();

    private static final String MOVIE_BASE_URI = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY = "73c0644c92763ce7863dcc4fb6592334";

    private static String DEFAULT_SORT_BY = "revenue.desc";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        mLoaderIndicator = (ProgressBar) rootView.findViewById(R.id.gv_pb_indicator);

        GridView gridView = (GridView) rootView.findViewById(R.id.gv_main);

        mEmptyView = (TextView) rootView.findViewById(R.id.tv_empty_tv);

        mAdapter = new GridViewAdapter(getActivity(),popularMoviesList );

        gridView.setEmptyView(mEmptyView);


        gridView.setAdapter(mAdapter);

        sortByOptions(DEFAULT_SORT_BY);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_popular:
                sortByOptions("popularity.desc");
                return true;
            case R.id.menu_top_rated:
                sortByOptions("vote_average.desc");
                return true;
            case R.id.menu_highest_revenue:
                sortByOptions("revenue.desc");
                return true;
            default:
                Log.e(TAG,"Error in sortByString method call");
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortByOptions(final String sortBy)
    {
        new AsyncTask<String, Void, List<PopularMovies>>() {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                mLoaderIndicator.setVisibility(View.VISIBLE);

            }

            @Override
            protected List<PopularMovies> doInBackground(String... params)
            {
                if(params.length == 0)
                {
                    return null;
                }
                Uri baseUri =Uri.parse(MOVIE_BASE_URI).buildUpon()
                        .appendQueryParameter("api_key",API_KEY)
                        .appendQueryParameter("language","en-US")
                        .appendQueryParameter("sort_by",sortBy)
                        .appendQueryParameter("page","1")
                        .build();
                return QueryUtils.extractPopularMovies(baseUri.toString());
            }

            @Override
            protected void onPostExecute(List<PopularMovies> popularMovies)
            {
            mLoaderIndicator.setVisibility(View.GONE);
                if(popularMovies != null)
                {
                    popularMoviesList.clear();
                    popularMoviesList.addAll(popularMovies);
                    mAdapter.updateMovies(popularMovies);
                }else
                {
                    mEmptyView.setText("No Movies Currently found!!");
                }
            }
        }.execute(sortBy);


    }

}
