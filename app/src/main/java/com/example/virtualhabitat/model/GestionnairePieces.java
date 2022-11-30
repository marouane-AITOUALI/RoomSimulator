package com.example.virtualhabitat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestionnairePieces implements Iterable<Piece>{

    private ArrayList<Piece> pieces;

    public GestionnairePieces(){
        pieces = new ArrayList<>();
    }

    public void ajouter(Piece piece){
        pieces.add(piece);
    }


    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    @Override
    public Iterator<Piece> iterator() {
        return pieces.iterator();
    }
}
