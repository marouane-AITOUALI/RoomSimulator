package com.example.virtualhabitat;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.virtualhabitat.model.GestionnairePieces;
import com.example.virtualhabitat.model.MainAdapter;
import com.example.virtualhabitat.model.Piece;
import com.example.virtualhabitat.model.RecyclerItemClickListener;

import java.util.ArrayList;

public class VisualisationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Piece> pieces;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualisation);

        recyclerView = findViewById(R.id.recyclerView);
        pieces = GestionnairePieces.getInstance().getPieces();

        getInfos();


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(VisualisationActivity.this, ViewPieceActivity.class);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void getInfos() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(pieces);
        recyclerView.setAdapter(adapter);
    }
}