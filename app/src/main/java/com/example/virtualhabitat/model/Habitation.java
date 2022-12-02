package com.example.virtualhabitat.model;

import java.util.ArrayList;

public class Habitation {
    private GestionnairePieces pieces;
    private String id;


    public Habitation(String id, GestionnairePieces gestionnaire){
        pieces = gestionnaire;
        this.id = id;
    }

    public Habitation(String name){
        this.id = name;
        pieces = new GestionnairePieces();
    }

    public void ajouterPiece(Piece... p){
        for(int i = 0; i < p.length; i++){
            pieces.ajouter(p[i]);
        }
    }

    public String getName(){
        return id;
    }

    public ArrayList<Piece> getPieces(){
        return pieces.getPieces();
    }

}
