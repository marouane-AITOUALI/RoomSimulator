package com.example.virtualhabitat;

import android.graphics.PixelFormat;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.model.GestionnairePieces;
import com.example.virtualhabitat.model.Piece;

import java.util.ArrayList;

public class DoorActivity extends AppCompatActivity {


    private ArrayList<Piece> pieces;
    private String nomPiece="";
    private ImageView imageView;
    private SurfaceView surfaceview;
    private int dir;
    //private CanvasVie



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        pieces = GestionnairePieces.getInstance().getPieces();

        Bundle extras = getIntent().getExtras();
        dir = extras.getInt("direction");
        nomPiece = extras.getString("nomPiece");
        Log.i("direction", String.valueOf(dir));

        imageView = findViewById(R.id.imageAcces);
        surfaceview = findViewById(R.id.surfaceView);
        surfaceview.setZOrderOnTop(true);
        surfaceview.getHolder().setFormat(PixelFormat.TRANSPARENT);

        switch(dir){
            case 1:
                imageView.setImageBitmap(getPiece(nomPiece ).getMur("nord").getBitmap());
                break;
            case 2:
                imageView.setImageBitmap(getPiece(nomPiece ).getMur("est").getBitmap());
                break;
            case 3:
                imageView.setImageBitmap(getPiece(nomPiece ).getMur("sud").getBitmap());
                break;
            case 4:
                imageView.setImageBitmap(getPiece(nomPiece ).getMur("ouest").getBitmap());
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