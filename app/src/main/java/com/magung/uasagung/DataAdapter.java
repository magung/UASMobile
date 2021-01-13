package com.magung.uasagung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.CardViewHolder> {
    private List<Movie> movies;
    private Context context;

    public DataAdapter(List<Movie> movies,Context context){
        this.movies = movies;
        this.context = context;
    }
    public List<Movie> getMovies(){
        return movies;
    }
    @NonNull
    @Override
    public DataAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.CardViewHolder holder, int position) {
        final String nama,image;
        nama  = movies.get(position).getNama();
        image = "https://image.tmdb.org/t/p/w500" + movies.get(position).getImage();
        final int id = movies.get(position).getId();
        holder.tvNama.setText(nama);
        Glide.with(context).load(image).into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        ImageView imgImage;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tx_movie);
            imgImage = (ImageView) itemView.findViewById(R.id.im_movie);
        }
    }
}
