package com.example.virtualhabitat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestionnairePieces {

    private ArrayList<Piece> pieces;
    private static final GestionnairePieces instance = new GestionnairePieces();

    /**
     * Constructeur de GestionnairePieces
     */
    public GestionnairePieces(){
        pieces = new ArrayList<>();
    }

    /**
     * Retourne l'instance de GestionnairePieces
     * @return Retourne l'instance de GestionnairePieces
     */
    public static GestionnairePieces getInstance() {
        return instance;
    }

    /**
     * Rajoute la pièce p à la liste des pièces
     * @param p Pièce à rajouter
     */
    public void ajouter(Piece p ){
        pieces.add(p);
    }

    /**
     * Retourne la liste de toutes les pièces
     * @return Retourne la liste de toutes les pièces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

}
