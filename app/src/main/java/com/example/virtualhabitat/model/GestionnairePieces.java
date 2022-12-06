package com.example.virtualhabitat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestionnairePieces {

    private ArrayList<Piece> pieces;
    private static final GestionnairePieces instance = new GestionnairePieces();

    public GestionnairePieces(){
        pieces = new ArrayList<>();
    }

    public static GestionnairePieces getInstance() {
        return instance;
    }

    public void ajouter(Piece p ){
        pieces.add(p);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
}
