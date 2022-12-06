package com.example.virtualhabitat.model;

import android.content.Context;

import java.util.ArrayList;

public class saveModel {

    private ArrayList<Piece> pieces;
    private ArrayList<Piece> copiePieces;

    private String json="";

    public String save(Context context, String nomFichier){

        copiePieces = new ArrayList<>();
        pieces = GestionnairePieces.getInstance().getPieces();

        for(Piece piece : pieces){

        }
        return null;
    }

}
