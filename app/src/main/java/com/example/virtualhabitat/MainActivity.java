package com.example.virtualhabitat;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.model.Habitation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonCreation = (Button) findViewById(R.id.boutonEdit);


        buttonCreation.setOnClickListener((v) -> {
            Intent ic = new Intent(MainActivity.this, EditActivity.class);
            startActivity(ic);
        });


    }
}