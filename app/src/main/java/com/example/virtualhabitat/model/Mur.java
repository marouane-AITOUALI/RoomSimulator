package com.example.virtualhabitat.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Mur implements Iterable<Access>{

    private ArrayList<Access> acces = new ArrayList<>();;
    private String direction;
    private Bitmap bitmap;
    private ImageView imageView;

    public Mur(String dir){
        direction = dir;
        bitmap = null;
        imageView = null;

    }
    public Mur(Bitmap image, String dir){
        bitmap = image;
        direction = dir;
    }

    /**
     * Modifie l'acces (porte)
     * @param acces Acces porte
     */
    public void setAcces(ArrayList<Access> acces) {
        this.acces = acces;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ArrayList<Access> getAcces() {
        return acces;
    }

    /**
     * Retourne la direction du mur
     * @return Direction dur mur
     */
    public String getDirection() {
        return direction;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public ImageView getImageView() {
        return imageView;
    }

    @NonNull
    @NotNull
    @Override
    public Iterator<Access> iterator() {
        return acces.iterator();
    }
}
