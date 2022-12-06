package com.example.virtualhabitat.model;

import android.graphics.Rect;

public class Acces {

    private Rect rect;
    private String nextPiece;


    public Acces(String nextPiece, Rect r){
        rect = r;
        this.nextPiece = nextPiece;
    }
    public Acces(Rect rectangle){
        rect = rectangle;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public void setNextPiece(String nextPiece) {
        this.nextPiece = nextPiece;
    }

    public String getNextPiece() {
        return nextPiece;
    }

    public Rect getRect() {
        return rect;
    }
}
