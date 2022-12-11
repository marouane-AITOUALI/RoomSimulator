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
        murs.add(new Mur("nord"));
        murs.add(new Mur("sud"));
        murs.add(new Mur("est"));
        murs.add(new Mur("ouest"));
    }

    public Piece(Piece p){
        id = p.getName();
        murs = p.getMurs();
    }

    public Mur getMur(Mur mur){
        for(int i =0; i < 4; i++){
            if(murs.get(i).getDirection().equals(mur.getDirection())){
                return murs.get(i);
            }
        }
        return null;
    }

    public ArrayList<Mur> getMurs(){
        return murs;
    }

    public Mur getMur(String dir){
        for(int i =0; i < 4; i++){
            if(murs.get(i).getDirection().equals(dir)){
                return murs.get(i);
            }
        }
        return null;
    }

    public ArrayList<Acces> getAcces(int dir){
        ArrayList<Acces> acces = null;
        switch (dir){
            case 1:
                acces = getMur("nord").getAcces();
                break;
            case 2:
                acces = getMur("est").getAcces();
                break;
            case 3:
                acces = getMur("sud").getAcces();
                break;
            case 4:
                acces = getMur("ouest").getAcces();
                break;
        }
        return acces;
    }

    public String getName(){
        return id;
    }

    public void ajouterMur(Bitmap image, String dir){
        murs.add(new Mur(image, dir));
    }

    public void ajouterPhoto(String dir){
        murs.add(new Mur(dir));
    }




}

