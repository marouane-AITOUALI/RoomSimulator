package com.example.virtualhabitat.model;

import java.util.ArrayList;

public class Habitation {
    private GestionnairePieces pieces;
    private Piece pieceActuelle;

    public Habitation(GestionnairePieces gestionnaire, Piece piece){
        pieces = gestionnaire;
        pieceActuelle = piece;
    }

    public void ajouterPiece(Piece... p){
        for(int i = 0; i < p.length; i++){
            pieces.ajouter(p[i]);
        }
    }

    public ArrayList<Piece> getPieces(){
        return pieces.getPieces();
    }

}
