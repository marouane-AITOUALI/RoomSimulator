package com.example.virtualhabitat.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class Piece {

    private String id;
    private ArrayList<Mur> murs;
    public Piece(String id) {
        this.id = id;
        murs = new ArrayList<>(4);
    }

    public String getName(){
        return id;
    }

    public void ajouterPhoto(Bitmap image, String dir){
        murs.add(new Mur(image, dir));
    }


}

