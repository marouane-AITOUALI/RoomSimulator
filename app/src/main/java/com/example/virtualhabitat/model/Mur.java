package com.example.virtualhabitat.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;

public class Mur {

    private ArrayList<Acces> acces = new ArrayList<>();;
    private String direction;
    private Bitmap bitmap;
    private ImageView imageView;


    /**
     * Constructeur Mur
     * @param dir Direction du mur
     */
    public Mur(String dir){
        direction = dir;
        bitmap = null;
        imageView = null;

    }

    /**
     * Change l'image bitmap du mur
     * @param bitmap La nouvelle image bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Change l'imageView du mur
     * @param imageView La nouvelle imageView du mur
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Retourne la liste des accès du mur
     * @return Retourne la liste des accès du mur
     */
    public ArrayList<Acces> getAcces() {
        return acces;
    }

    /**
     * Retourne la direction du mur
     * @return Direction dur mur
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Retourne l'image bitmap du mur
     * @return Image bitmap du mur
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Retourne l'imageView du mur
     * @return Retourne l'imageView du mur
     */
    public ImageView getImageView() {
        return imageView;
    }


}
