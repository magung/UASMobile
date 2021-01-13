package com.magung.uasagung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainAppActivity extends AppCompatActivity {
    DataAdapter adapter;
    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        showRecycleView();
    }

    private void showRecycleView() {
        RecyclerView view = (RecyclerView) findViewById(R.id.rv_meal);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        view.setLayoutManager(mLayoutManager);

        movies = MainActivity.db.dataDao().getAll(); //Ambil semua data
        adapter = new DataAdapter(movies, this);
        view.setAdapter(adapter);
    }
}