package com.murali.rxjavaproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Murali on 08/06/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private static final String TAG = "MovieAdapter";
    private ArrayList<Movie> movieArrayList;
    private Context context;
    MovieAdapter(Context c, ArrayList<Movie> list){
        context = c;
        movieArrayList = list;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.single_card,parent,false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Glide.with(context)
                .load(movieArrayList.get(position).getPosterPath())
                .into(holder.poster);
        holder.movieName.setText(movieArrayList.get(position).getTitle());
        holder.releaseDate.setText(movieArrayList.get(position).getReleaseDate());
        holder.certificate.setText(movieArrayList.get(position).getCensorRating());
        holder.rating.setText(movieArrayList.get(position).getRating().toString());
        holder.genre.setText(movieArrayList.get(position).getGenre());

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + movieArrayList.size());
        return movieArrayList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView movieName,releaseDate,rating,certificate,genre;


        MovieHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.eventImageView);
            movieName = (TextView) itemView.findViewById(R.id.movieNameTextView);
            releaseDate = (TextView) itemView.findViewById(R.id.releaseDateTextView);
            rating = (TextView) itemView.findViewById(R.id.ratingTextView);
            certificate = (TextView) itemView.findViewById(R.id.certificateTextView);
            genre = (TextView) itemView.findViewById(R.id.genreTextView);

        }
    }
    void clear(){
        movieArrayList.clear();
        notifyDataSetChanged();
    }
    void restore(ArrayList<Movie> list){
        movieArrayList = list;
        notifyDataSetChanged();
    }
}
