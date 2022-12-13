package com.example.virtualhabitat.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import com.example.virtualhabitat.R;
import com.example.virtualhabitat.model.GestionnairePieces;
import com.example.virtualhabitat.model.Piece;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ItemModel extends AppCompatActivity {



    private String namePiece="";

    private int directionPrisePhoto;
    private int directionAjoutMur;
    private TextView roomName;

    private ArrayList<Piece> pieces;

    private Piece currentPiece;
    private ImageView imageNord;
    private Button btnNord;
    private ImageView imageEst;
    private Button btnEst;
    private ImageView imageSud;
    private Button btnSud;
    private ImageView imageOuest;
    private Button btnOuest;

    private ImageButton ajouterMurNord, ajouterMurSud, ajouterMurOuest, ajouterMurEst;


    private ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_item_details);


        // Liaison avec les composants dans xml file

        roomName = (TextView) findViewById(R.id.nameRoom);

        imageNord = findViewById(R.id.imageNord);
        btnNord = findViewById(R.id.ajouterNord);
        imageEst = findViewById(R.id.imageEst);
        btnEst = findViewById(R.id.ajouterEst);
        imageSud = findViewById(R.id.imageSud);
        btnSud = findViewById(R.id.ajouterSud);
        imageOuest = findViewById(R.id.imageOuest);
        btnOuest = findViewById(R.id.ajouterOuest);

        ajouterMurNord = findViewById(R.id.ajouterMurNord);
        ajouterMurEst = findViewById(R.id.ajouterMurEst);
        ajouterMurOuest = findViewById(R.id.ajouterMurOuest);
        ajouterMurSud = findViewById(R.id.ajouterMurSud);








        // getting the room name from extras
        Bundle extras = getIntent().getExtras();
        namePiece = extras.getString("pieceChoisie");
        roomName.setText(namePiece);

        pieces = GestionnairePieces.getInstance().getPieces();

        currentPiece = currentPiece(namePiece);

        // Affichage des images prises dans leur cadre (Cas de sortie temporaire de l'application)
        currentPiece.getMur("nord").setImageView(imageNord);
        currentPiece.getMur("sud").setImageView(imageSud);
        currentPiece.getMur("est").setImageView(imageEst);
        currentPiece.getMur("ouest").setImageView(imageOuest);

        // Réaffichage des images dans leur position en cas de sortie/retour vers la piece
        Bitmap nord = currentPiece.getMur("nord").getBitmap();
        Bitmap est = currentPiece.getMur("est").getBitmap();
        Bitmap sud = currentPiece.getMur("sud").getBitmap();
        Bitmap ouest = currentPiece.getMur("ouest").getBitmap();

        // Verification si les bitmaps sont nuls
        if(nord != null) {
            currentPiece.getMur("nord").getImageView().setImageBitmap(nord);
            imageNord.setImageBitmap(nord);

        }
        if(est != null){
            currentPiece.getMur("est").getImageView().setImageBitmap(est);
            imageEst.setImageBitmap(est);

        }
        if(currentPiece.getMur("sud").getBitmap() != null){
            currentPiece.getMur("sud").getImageView().setImageBitmap(sud);
            imageSud.setImageBitmap(sud);
        }
        if(ouest != null){
            currentPiece.getMur("ouest").getImageView().setImageBitmap(ouest);
            imageOuest.setImageBitmap(ouest);
        }



        // Listener pour prendre la photo suivant une direction

         btnNord.setOnClickListener(view -> {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             directionPrisePhoto = 1;
             if(intent.resolveActivity(getPackageManager()) != null){
                 btnNord.setText("");
                 startActivityForResult(intent, 24);
             }
        });

        btnSud.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            directionPrisePhoto = 3;
            if(intent.resolveActivity(getPackageManager()) != null){
                btnSud.setText("");
                startActivityForResult(intent, 24);
            }
        });

        btnEst.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            directionPrisePhoto = 2;
            if(intent.resolveActivity(getPackageManager()) != null){
                btnEst.setText("");
                startActivityForResult(intent, 24);
            }

        });

        btnOuest.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            directionPrisePhoto = 4;
            if(intent.resolveActivity(getPackageManager()) != null){
                btnOuest.setText("");
                startActivityForResult(intent, 24);
            }

        });


        // Listener pour l'activité d'ajout de portes

        ajouterMurNord.setOnClickListener(view -> {
                directionAjoutMur = 1;
                Intent intent = new Intent(ItemModel.this, DoorActivity.class);

                intent.putExtra("direction", directionAjoutMur);
                intent.putExtra("nomPiece", currentPiece.getName());

                startActivity(intent);
        });

        ajouterMurEst.setOnClickListener(view -> {
            directionAjoutMur = 2;
            Intent intent = new Intent(ItemModel.this, DoorActivity.class);

            intent.putExtra("direction", directionAjoutMur);
            intent.putExtra("nomPiece", currentPiece.getName());

            startActivity(intent);
        });

        ajouterMurSud.setOnClickListener(view -> {
            directionAjoutMur = 3;
            Intent intent = new Intent(ItemModel.this, DoorActivity.class);

            intent.putExtra("direction", directionAjoutMur);
            intent.putExtra("nomPiece", currentPiece.getName());

            startActivity(intent);
        });

        ajouterMurOuest.setOnClickListener(view -> {
            directionAjoutMur = 4;
            Intent intent = new Intent(ItemModel.this, DoorActivity.class);

            intent.putExtra("direction", directionAjoutMur);
            intent.putExtra("nomPiece", currentPiece.getName());

            startActivity(intent);
        });


    }

    // Stock l'image prise dans le cadre associé

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 24 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            /*Toast.makeText(this, imageBitmap.getHeight()+", w: "+
                    imageBitmap.getWidth(), Toast.LENGTH_SHORT).show();*/
            //String dir = extras.getString("direction");

            try{
                FileOutputStream fos = openFileOutput(namePiece+directionPrisePhoto+".data", MODE_APPEND);
                imageBitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
                switch(directionPrisePhoto){
                    case 1:
                        this.currentPiece.getMur("nord").
                                getImageView().setImageBitmap(imageBitmap);
                        this.currentPiece.getMur("nord").
                                setBitmap(imageBitmap);
                        imageNord.setImageBitmap(imageBitmap);
                        break;
                    case 2:
                        this.currentPiece.getMur("est").
                                getImageView().setImageBitmap(imageBitmap);
                        this.currentPiece.getMur("est").
                                setBitmap(imageBitmap);
                        imageEst.setImageBitmap(imageBitmap);

                        break;
                    case 3:
                        this.currentPiece.getMur("sud").
                                getImageView().setImageBitmap(imageBitmap);
                        this.currentPiece.getMur("sud").
                                setBitmap(imageBitmap);
                        imageSud.setImageBitmap(imageBitmap);
                        break;
                    case 4:
                        this.currentPiece.getMur("ouest").
                                getImageView().setImageBitmap(imageBitmap);
                        this.currentPiece.getMur("ouest").
                                setBitmap(imageBitmap);
                        imageOuest.setImageBitmap(imageBitmap);
                        break;
                }



                fos.flush();fos.close();




            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Retourne la piece dont le nom est passée en paramètre
     * @param nom Nom de la pièce
     * @return Pièce dont le nom est 'nom'
     */
    public Piece currentPiece(String nom){
        for(int i=0; i < this.pieces.size(); i++){
            if(pieces.get(i).getName().equals(nom)){
                return pieces.get(i);
            }
        }
        return null;
    }
}