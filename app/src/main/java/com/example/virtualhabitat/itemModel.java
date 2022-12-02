package com.example.virtualhabitat;

import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class itemModel extends AppCompatActivity {

    private String namePiece="";
    private TextView roomName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_item_details);

        roomName = (TextView) findViewById(R.id.nameRoom);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            namePiece = extras.getString("pieceChoisie");
            roomName.setText(namePiece);
        }


    }
}