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
    private int lastITemSelected;
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

        Bundle extras = getIntent().getExtras();
        dir = extras.getInt("direction");
        nomPiece = extras.getString("nomPiece");
        Log.i("direction", String.valueOf(dir));

        recyclerView = findViewById(R.id.pieces);

        imageView = findViewById(R.id.imageAcces);
        surfaceview = findViewById(R.id.surfaceView);
        surfaceview.setZOrderOnTop(true);
        surfaceview.getHolder().setFormat(PixelFormat.TRANSPARENT);

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
                    DoorActivity.this.addContentView(canvas, new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                    // in case we want to change the zone of the rectangle
                    canvas.invalidate();


                }
                return true;
            }
        });

        getInfos();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recyclerView.getChildAt(lastITemSelected).setBackgroundColor(Color.WHITE);
                nextRoom = pieces.get(position).getName();
                //Toast.makeText(DoorActivity.this, nextRoom, Toast.LENGTH_SHORT).show();
                recyclerView.getChildAt(position).setBackgroundColor(Color.GREEN);
                lastITemSelected = position;
            }

            @Override
            public void onLongItemClick(View view, int position) {
                recyclerView.getChildAt(position).setBackgroundColor(Color.WHITE);
            }
        }));

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

    private void getInfos() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(pieces);
        recyclerView.setAdapter(adapter);
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