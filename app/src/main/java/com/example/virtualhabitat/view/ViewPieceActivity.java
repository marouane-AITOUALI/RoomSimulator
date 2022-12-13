package com.example.virtualhabitat.view;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.R;
import com.example.virtualhabitat.model.Acces;
import com.example.virtualhabitat.model.GestionnairePieces;
import com.example.virtualhabitat.model.Piece;

import java.util.ArrayList;

public class ViewPieceActivity extends AppCompatActivity {

    private ArrayList<Piece> pieces;
    private ImageButton left, right;
    private ImageView image;
    private String nomPiece;

    private ArrayList<Button> buttonsMurs = new ArrayList<>();

    // La direction par défaut
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

        /*
         * Ecouteur pour changer la direction du mur choisie dans le sens horaire
         */
        right.setOnClickListener(view ->{
            removeAllAcces();
            switch(dir){
                case 1:
                    image.setImageBitmap(getPiece(nomPiece).getMur("est").getBitmap());
                    break;
                case 2:
                    image.setImageBitmap(getPiece(nomPiece).getMur("sud").getBitmap());
                    break;
                case 3:
                    image.setImageBitmap(getPiece(nomPiece).getMur("ouest").getBitmap());
                    break;
                case 4:
                    image.setImageBitmap(getPiece(nomPiece).getMur("nord").getBitmap());
                    dir = 0;
                    break;
            }
            dir++;
            setAcces(nomPiece, dir);


        });

        /*
        * Ecouteur pour changer la direction du mur choisie dans le sens antihoraire
        */
        left.setOnClickListener(view ->{
            removeAllAcces();

            switch(dir){
                case 1:
                    image.setImageBitmap(getPiece(nomPiece).getMur("ouest").getBitmap());
                    dir = 5;
                    break;
                case 2:
                    image.setImageBitmap(getPiece(nomPiece).getMur("nord").getBitmap());
                    break;
                case 3:
                    image.setImageBitmap(getPiece(nomPiece).getMur("est").getBitmap());
                    break;
                case 4:
                    image.setImageBitmap(getPiece(nomPiece).getMur("sud").getBitmap());
                    break;
            }
            dir--;
            setAcces(nomPiece, dir);

        });
    }

    /**
     * Affiche Le mur suivant la direction choisie
     */
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

        setAcces(nomPiece,dir);

    }

    /**
     * Affiche les portes du mur de direction dir, dans la piece nomPiece
     * @param nomPiece Nom de la pièce
     * @param dir Direction de la pièce
     */
    private void setAcces(String nomPiece, int dir)
    {
        ArrayList<Acces> accesMur = getPiece(nomPiece).getAcces(dir);
        if(accesMur.size() != 0)
        {
            for(Acces acces: accesMur)
            {

                Button bouton = new Button(ViewPieceActivity.this.getApplicationContext());

                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.width = acces.getRect().width();
                layoutParams.height = acces.getRect().height();
                bouton.setLayoutParams(layoutParams);
                bouton.setWidth(acces.getRect().width());
                bouton.setHeight(acces.getRect().height());
                bouton.setX(acces.getRect().left);
                bouton.setY(acces.getRect().top);
                bouton.setBackgroundColor(Color.BLUE);
                bouton.setText(acces.getNextPiece());
                bouton.setTextColor(Color.YELLOW);

                bouton.setAlpha(0.08F);
                bouton.setClickable(true);

                buttonsMurs.add(bouton);
                setListener(acces.getNextPiece());
                ViewPieceActivity.this.addContentView(bouton, layoutParams);
            }
        }
    }

    /**
     * Pose des écouteurs sur les boutons
     * @param nextPiece La pièce vers laquelle mène le bouton
     */
    private void setListener(String nextPiece) {
        for(Button bouton: buttonsMurs){
            bouton.setOnClickListener(e ->{
                nomPiece = nextPiece;
                dir = 1;
                setImage();
                removeAllAcces();
                setAcces(nomPiece, dir);
            });
        }
    }

    /**
     * Vide la liste des boutons et enlève les boutons déjà affichés
     */
    public void removeAllAcces(){
        for(Button b: buttonsMurs){
            ((ViewGroup)b.getParent()).removeView(b);
        }
        buttonsMurs.clear();
        buttonsMurs = new ArrayList<>();
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