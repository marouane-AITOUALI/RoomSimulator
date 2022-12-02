package com.example.virtualhabitat.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class Piece {

    private String id;
    private ArrayList<Photo> photos;
    public Piece(String id) {
        this.id = id;
        photos = new ArrayList<>(4);
    }

    public String getName(){
        return id;
    }

    public void ajouterPhoto(Bitmap image){
        photos.add(new Photo(image));
    }


}

