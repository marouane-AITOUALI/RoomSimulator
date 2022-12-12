package com.example.virtualhabitat.model;

import android.graphics.Rect;

public class Acces {

    private Rect rect;
    private String nextPiece;

    /**
     * Contructeur Acces
     * @param nextPiece Nom de la pièce où l'accès mène
     * @param r Rect définissant les dimensions de l'accès
     */
    public Acces(String nextPiece, Rect r){
        rect = r;
        this.nextPiece = nextPiece;
    }

    /**
     * Retourne le nom de la pièce où l'accès mène
     * @return Retourne le nom de la pièce où l'accès mène
     */
    public String getNextPiece() {
        return nextPiece;
    }

    /**
     * Retourne le rectangle définissant les dimensions de l'accès
     * @return Retourne le rectangle définissant les dimensions de l'accès
     */
    public Rect getRect() {
        return rect;
    }
}
