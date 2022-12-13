package com.example.virtualhabitat.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.virtualhabitat.R;
import com.example.virtualhabitat.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private SaveModel saveModel = SaveModel.getInstance();
    private RecyclerView nomsHabitations;
    private EditText nomHabitation;
    private MainAdapter adapter;
    private ArrayList<Piece> habitationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //saveModel.loadData(EditActivity.this, getString(R.string.PREFERENCEFILE));

        Button valider = (Button) findViewById(R.id.valider);
        Button save = (Button) findViewById(R.id.save);

        nomsHabitations = (RecyclerView) findViewById(R.id.textViewHabitation);
        nomsHabitations.setHasFixedSize(false);

        habitationList = GestionnairePieces.getInstance().getPieces();

        getInfos();

        // Ecouteur pour la création d'une pièce
        valider.setOnClickListener(view -> {

            boolean nomExist = false;
            nomHabitation = findViewById(R.id.habitationName);

            for(Piece p : habitationList){
                if(p.getName().equals(nomHabitation.getText().toString())){
                    nomExist = true;
                }
            }

            if(nomExist){
                Toast.makeText(this, "Le nom existe déjà !",
                        Toast.LENGTH_SHORT).show();
            }
            else if(!nomExist && nomHabitation.getText().toString().length() < 2){
                Toast.makeText(this, "Enter a valid name(length >= 2)", Toast.LENGTH_SHORT).show();
            }
            else{
                habitationList.add(new Piece(nomHabitation.getText().toString()));

                getInfos();
                nomHabitation.setText("");
                Toast.makeText(this, "Pièce crée avec succès",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Save the model if case of exiting app
        save.setOnClickListener(view ->{
            habitationList.clear();
            //GestionnairePieces.getInstance().setPieces(new ArrayList<>());
            getInfos();
        });

        /*
        Ecouteurs sur les pièces de la recycler view afin de choisir la pièce qu'on
        aimerait éditer
        */
        nomsHabitations.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), nomsHabitations,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(EditActivity.this, ItemModel.class);
                        intent.putExtra("pieceChoisie", habitationList.get(position).getName());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

    }


    /**
     * Initialise la recycler View avec l'adapter
     */
    private void getInfos() {
        nomsHabitations.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(habitationList);
        nomsHabitations.setAdapter(adapter);
    }



}

