package com.magung.uasagung;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nama")
    private String nama;
    @ColumnInfo(name = "image")
    private String image;

    @Ignore
    public Movie(String nama, String image){
        this.nama = nama;
        this.image = image;
    }
    public Movie(int id, String nama, String image){
        this.id = id;
        this.nama = nama;
        this.image = image;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public String getImage() {
        return image;
    }
}
