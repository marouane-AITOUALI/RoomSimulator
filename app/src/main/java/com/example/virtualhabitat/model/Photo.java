package com.example.virtualhabitat.model;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Photo implements Iterable<Access>{


    private ArrayList<Access> acces;
    private Bitmap bitmap;
    public Photo(Bitmap image){
        acces = new ArrayList<>();
        bitmap = image;
    }





    @NonNull
    @NotNull
    @Override
    public Iterator<Access> iterator() {
        return acces.iterator();
    }
}
