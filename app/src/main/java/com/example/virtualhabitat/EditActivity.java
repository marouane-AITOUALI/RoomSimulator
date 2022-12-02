package com.example.virtualhabitat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.virtualhabitat.model.Habitation;
import com.example.virtualhabitat.model.MainAdapter;
import com.example.virtualhabitat.model.Piece;
import com.example.virtualhabitat.model.RecyclerItemClickListener;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {


    private RecyclerView nomsHabitations;
    private EditText nomHabitation;
    private MainAdapter adapter;
    private ArrayList<Piece> habitationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button valider = (Button) findViewById(R.id.valider);

        nomsHabitations = (RecyclerView) findViewById(R.id.textViewHabitation);
        nomsHabitations.setHasFixedSize(false);

        getInfos();


        valider.setOnClickListener(view -> {

            nomHabitation = findViewById(R.id.habitationName);
            if(nomHabitation.getText().toString().length() < 2){
                Toast.makeText(this, "Enter a valid name(length >= 2)", Toast.LENGTH_SHORT).show();
            }
            else{
                habitationList.add(new Piece(nomHabitation.getText().toString()));
                getInfos();
                nomHabitation.setText("");
            }
        });

        nomsHabitations.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), nomsHabitations,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(EditActivity.this, itemModel.class);
                        intent.putExtra("pieceChoisie", habitationList.get(position).getName());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

    }

    private void getInfos() {
        nomsHabitations.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(habitationList);
        nomsHabitations.setAdapter(adapter);
    }



}

