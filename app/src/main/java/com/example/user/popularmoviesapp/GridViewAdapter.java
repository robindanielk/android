package com.example.user.popularmoviesapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by user on 3/30/2017.
 */

public class GridViewAdapter extends ArrayAdapter<PopularMovies>
{
    private static final String TAG = GridViewAdapter.class.getSimpleName();

    private List<PopularMovies> list;

    public GridViewAdapter(Activity context,List<PopularMovies> list)

    {
        super(context,0);
        this.list = list;
    }

    public void updateMovies(List<PopularMovies> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    /*Adapter for inflating the grid item view*/
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        MyViewHolder viewHolder = null;
        if(rootView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.grid_item,parent,false);
            viewHolder = new MyViewHolder(rootView);
            rootView.setTag(viewHolder);
        }else
        {
           viewHolder = (MyViewHolder) rootView.getTag();
        }
        PopularMovies currentMovie = getItem(position);

        Picasso.with(getContext()).load(currentMovie.getMovieImage())
                .into(viewHolder.mPosterImage);
        viewHolder.mOriginalTitle.setText(currentMovie.getOriginalTitle());

        return rootView;
    }

    private class MyViewHolder
    {
         public TextView mOriginalTitle;
         public ImageView mPosterImage;
        public MyViewHolder(View v)
        {
            mOriginalTitle = (TextView) v.findViewById(R.id.gv_tv_original_title);
            mPosterImage = (ImageView) v.findViewById(R.id.gv_image);
        }
    }
}
