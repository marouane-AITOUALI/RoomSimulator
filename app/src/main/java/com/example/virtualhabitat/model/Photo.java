package com.example.virtualhabitat.model;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Photo implements Iterable<Access>{

    private Orientation orientation;
    private ArrayList<Access> acces;
    private Bitmap bitmap;
    public Photo(Orientation ori, Bitmap image){
        orientation = ori;
        acces = new ArrayList<>();
        bitmap = image;
    }

    public Orientation getOrientation(){
        return orientation;
    }

    @NonNull
    @NotNull
    @Override
    public Iterator<Access> iterator() {
        return acces.iterator();
    }
}
