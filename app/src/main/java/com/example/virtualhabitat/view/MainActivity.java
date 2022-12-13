package com.example.virtualhabitat.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.virtualhabitat.R;
import com.example.virtualhabitat.model.Piece;
import com.example.virtualhabitat.model.SaveModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Piece> pieces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SaveModel saveModel = new SaveModel();
        //saveModel.loadData(MainActivity.this, getString(R.string.PREFERENCEFILE));

        Button buttonCreation = (Button) findViewById(R.id.boutonEdit);
        Button buttonView = (Button) findViewById(R.id.boutonView);

        // Edit mode of the app
        buttonCreation.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });

        // Visualization mode of the app
        buttonView.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, VisualisationActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Menu à afficher sur la page principale
     * @param menu Menu à afficher
     * @return true en cas de réussite
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Ecouteurs sur les items du menu
     * @param item MenuItem cliqué
     * @return true si l'écouteur est bien ajouté sur l'item du menu, false sinon
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.quitter:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}