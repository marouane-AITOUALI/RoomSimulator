package com.example.virtualhabitat.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.virtualhabitat.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SaveModel {
    private ArrayList<Piece> pieces;
    private static final SaveModel instance = new SaveModel();

    public SaveModel(){
    }

    public static SaveModel getInstance(){
        return instance;
    }
    public void loadData(Context context, String fileName){
        File file = new File(fileName);
        if(file.exists()){
            Toast.makeText(context, "entré dans loadData", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = context.getSharedPreferences("jsonData", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(fileName, null);
            Type type = new TypeToken<ArrayList<Piece>>() {

            }.getType();
            pieces = gson.fromJson(json, type);

            if(pieces == null){
                GestionnairePieces.getInstance().setPieces(new ArrayList<>());
            }else{
                GestionnairePieces.getInstance().setPieces(pieces);
            }
        }
    }



    public void saveData(Context context, String fileName) {
        // assert habitationList != null: "Liste pieces vide";
        SharedPreferences sharedPreferences = context.getSharedPreferences("jsonData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pieces);
        editor.putString(fileName, json);
        editor.apply();
        Toast.makeText(context, "SaveData...", Toast.LENGTH_SHORT).show();
        loadData(context, fileName);
    }






    public String readFile(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileName);
        if (file.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String s;
                while((s = br.readLine()) != null){
                    stringBuilder.append(s);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return stringBuilder.toString();
        }
        return "";
    }

}
