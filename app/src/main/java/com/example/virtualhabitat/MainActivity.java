package com.example.virtualhabitat;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.model.Habitation;
import com.example.virtualhabitat.model.Piece;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonCreation = (Button) findViewById(R.id.boutonEdit);
        Button buttonView = (Button) findViewById(R.id.boutonView);


        buttonCreation.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });

        buttonView.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, VisualisationActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.quitter:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}