package com.example.virtualhabitat;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.virtualhabitat.model.*;

import java.util.ArrayList;

public class DoorActivity extends AppCompatActivity {


    private ArrayList<Piece> pieces;
    private String nomPiece="";
    private ImageView imageView;
    private RecyclerView recyclerView;
    private String nextRoom;
    private SurfaceView surfaceview;
    private Rect rect;
    private int lastITemSelected=-1;
    private myCanvas canvas;
    private int dir;
    private MainAdapter adapter;
    //private CanvasVie



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        pieces = GestionnairePieces.getInstance().getPieces();

        Button addDoor = findViewById(R.id.ajoutPorte);

        // Getting Room Name and the direction using intent extras
        Bundle extras = getIntent().getExtras();
        dir = extras.getInt("direction");
        nomPiece = extras.getString("nomPiece");


        recyclerView = findViewById(R.id.pieces);

        imageView = findViewById(R.id.imageAcces);
        surfaceview = findViewById(R.id.surfaceView);
        surfaceview.setZOrderOnTop(true);
        surfaceview.getHolder().setFormat(PixelFormat.TRANSPARENT);

        // Setting the image of the room depending on the direction of the Room
        switch(dir){
            case 1:
                imageView.setImageBitmap(getPiece(nomPiece).getMur("nord").getBitmap());
                break;
            case 2:
                imageView.setImageBitmap(getPiece(nomPiece).getMur("est").getBitmap());
                break;
            case 3:
                imageView.setImageBitmap(getPiece(nomPiece).getMur("sud").getBitmap());
                break;
            case 4:
                imageView.setImageBitmap(getPiece(nomPiece).getMur("ouest").getBitmap());
                break;
        }

        // Ecouteur en cas de dessin d'un carré
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getPointerCount() == 2){
                    // Getting touching positions
                    int x1 = (int) motionEvent.getX(0);
                    int y1 = (int) motionEvent.getY(0);
                    int x2 = (int) motionEvent.getX(1);
                    int y2 = (int) motionEvent.getY(1);

                    // Rectangle & sort
                    rect = new Rect(x1, y1, x2, y2);
                    rect.sort();

                    // myCanvas View model
                    canvas = new myCanvas(DoorActivity.this, surfaceview, rect);




                    // settling rectangle on image
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    DoorActivity.this.addContentView(canvas, layoutParams);

                    // in case we want to change the zone of the rectangle
                    canvas.invalidate();


                }
                return true;
            }
        });

        getInfos();

        /*
        Ecouteurs sur les éléments de la recycler view afin de choisir la pièce vers
        laquelle l'accès mène
        */
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                nextRoom = pieces.get(position).getName();
                Toast.makeText(DoorActivity.this, "La pièce "+nextRoom+" est choisie",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        /*
        Ajout d'accès au modèle en cas de clique sur bouton AjouterPorte
        Vérification des exceptions
         */
        addDoor.setOnClickListener(view -> {
            /*if(imageView.getDrawable() == null){
                Toast.makeText(this, "Insérer une image", Toast.LENGTH_SHORT).show();
            }*/
            if(rect == null){
                Toast.makeText(this, "Sélectionner une zone", Toast.LENGTH_SHORT).show();
            }



            if(rect != null){

                // Cas où on a pas choisi la prochaine pièce
                if(nextRoom == null){
                    Toast.makeText(this, "Choisissez une piece", Toast.LENGTH_SHORT).show();
                }

                // Cas on a choisi la meme piece
                else if(nextRoom.equals(nomPiece)){
                    Toast.makeText(this, "Porte vers la même pièce", Toast.LENGTH_SHORT).show();
                }
                else{
                    Acces porte = new Acces(nextRoom, rect);
                    switch(dir){
                        case 1:
                            getPiece(nomPiece).getMur("nord").getAcces().add(porte);
                            break;
                        case 2:
                            getPiece(nomPiece).getMur("est").getAcces().add(porte);
                            break;
                        case 3:
                            getPiece(nomPiece).getMur("sud").getAcces().add(porte);
                            break;
                        case 4:
                            getPiece(nomPiece).getMur("ouest").getAcces().add(porte);
                            break;
                    }
                    Toast.makeText(this, "La porte a bien été ajoutée", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    /**
     * Initialise la recycler View avec l'adapter
     */
    private void getInfos() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(pieces);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Retourne la pièce dont le nom est name
     * @param name Nom de la pièce
     * @return Retourne la pièce dont le nom est name
     */
    public Piece getPiece(String name){
        for(Piece p: pieces){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
}