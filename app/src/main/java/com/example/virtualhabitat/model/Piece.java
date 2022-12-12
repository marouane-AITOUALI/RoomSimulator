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
        ajoutMurInitial();
    }

    /**
     * Rajoute les quatres murs initiaux de la pièce
     */
    private void ajoutMurInitial() {
        murs.add(new Mur("nord"));
        murs.add(new Mur("sud"));
        murs.add(new Mur("est"));
        murs.add(new Mur("ouest"));
    }

    /**
     * Constructeur Piece
     * @param p Piece à construire
     */
    public Piece(Piece p){
        id = p.getName();
        murs = p.getMurs();
    }


    /**
     * Retourne le mur passé en paramètre
     * @param mur Mur à retourner
     * @return Mur Le mur à retourner
     */
    public Mur getMur(Mur mur){
        for(int i =0; i < 4; i++){
            if(murs.get(i).getDirection().equals(mur.getDirection())){
                return murs.get(i);
            }
        }
        return null;
    }

    /**
     * Retourne la liste des murs de la pièce
     * @return Retourne la liste des murs de la pièce
     */
    public ArrayList<Mur> getMurs(){
        return murs;
    }

    /**
     * Retourne le mur dont la direction est passée en paramètre
     * @param dir la direction du mur
     * @return Le mur dont la direction est passée en paramètre
     */
    public Mur getMur(String dir){
        for(int i =0; i < 4; i++){
            if(murs.get(i).getDirection().equals(dir)){
                return murs.get(i);
            }
        }
        return null;
    }


    /**
     * Retourne la liste des accès d'un mur
     * @param dir la direction du mur
     * @return Retourne la liste des accès d'un mur de direction dir
     */
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

    /**
     * Nom de la pièce
     * @return Retourne le nom de la pièce
     */
    public String getName(){
        return id;
    }







}

