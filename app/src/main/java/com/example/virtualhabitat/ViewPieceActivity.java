package com.example.virtualhabitat;

import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.model.GestionnairePieces;
import com.example.virtualhabitat.model.Piece;

import java.util.ArrayList;

public class ViewPieceActivity extends AppCompatActivity {

    private ArrayList<Piece> pieces;
    private ImageButton left, right;
    private ImageView image;
    private String nomPiece;

    private int dir = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_piece);

        pieces = GestionnairePieces.getInstance().getPieces();
        left = findViewById(R.id.leftArrow);
        right = findViewById(R.id.rightArrow);

        image = findViewById(R.id.imageToShow);

        Bundle extras = getIntent().getExtras();
        nomPiece = extras.getString("nomPiece");

        setImage();
    }

    private void setImage() {
        switch(dir){
            case 1:
                image.setImageBitmap(getPiece(nomPiece).getMur("nord").getBitmap());
                break;
            case 2:
                image.setImageBitmap(getPiece(nomPiece).getMur("est").getBitmap());
                break;
            case 3:
                image.setImageBitmap(getPiece(nomPiece).getMur("sud").getBitmap());
                break;
            case 4:
                image.setImageBitmap(getPiece(nomPiece).getMur("ouest").getBitmap());
                break;
        }

    }

    public Piece getPiece(String name){
        for(Piece p: pieces){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
}