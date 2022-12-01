package com.example.virtualhabitat;

import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class EditActivity extends AppCompatActivity {


    private RecyclerView nomsHabitations;
    private EditText nomHabitation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button valider = (Button) findViewById(R.id.valider);
        nomsHabitations = (RecyclerView) findViewById(R.id.nomsHabitations);
        valider.setOnClickListener(view -> {
            nomHabitation = findViewById(R.id.nomHabitation);
        });
    }
}