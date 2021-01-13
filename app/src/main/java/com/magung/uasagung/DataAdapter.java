package com.magung.uasagung;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

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
        final int pos = position;
        nama  = movies.get(position).getNama();
        image = "https://image.tmdb.org/t/p/w500" + movies.get(position).getImage();
        final int id = movies.get(position).getId();
        holder.tvNama.setText(nama);
        Glide.with(context).load(image).into(holder.imgImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Operasi data");
                alertDialog.setMessage(id +" - "+ nama);
                alertDialog.setPositiveButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.setNeutralButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(context);
                        String url = "https://rest-api-agung-411.000webhostapp.com/api/produk.php?action=hapus&id="+ id;

                        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        String id, nama, telp;

                                        if (response.optString("result").equals("true")){
                                            Toast.makeText(context, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();

                                            movies.remove(pos); //hapus baris customers
                                            notifyItemRemoved(pos); //refresh customer list ( ada animasinya )
                                            notifyDataSetChanged();

                                        }else{
                                            Toast.makeText(context, "Gagal hapus data", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Log.d("Events: ", error.toString());

                                Toast.makeText(context, "Please check your connection", Toast.LENGTH_SHORT).show();
                            }
                        });

                        queue.add(jsObjRequest);
                    }
                });


                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
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
