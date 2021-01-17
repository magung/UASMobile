package com.magung.uasagung;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class DataDAO {
    @Query("SELECT * FROM Movie")
    public abstract List<Movie> getAll();

    @Query("DELETE FROM Movie")
    public abstract void deleteAll();

    @Insert
    public abstract void insertAll(Movie movie);

    @Update
    public abstract void update(Movie movie);

    @Delete
    public abstract void delete(Movie movie);
}
