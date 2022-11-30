package com.example.virtualhabitat.model;

import android.graphics.Bitmap;

import java.util.HashMap;

public class Piece {

    private String id;
    private HashMap<Orientation, Photo> photos;
    public Piece(String id) {
        this.id = id;
        photos = new HashMap<>(4);
    }

    public void ajouterPhoto(Bitmap image, Orientation orientation){
        photos.put(orientation, new Photo(orientation, image));
    }

    public String getId() {
        return id;
    }
}

