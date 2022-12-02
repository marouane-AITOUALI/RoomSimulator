package com.example.virtualhabitat.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.virtualhabitat.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<Piece> habitationsList;

    public MainAdapter(ArrayList<Piece> arr){
        habitationsList = arr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView habitationName;

        public ViewHolder(final View view){
            super(view);
            habitationName = view.findViewById(R.id.textViewHabitation);
            //notifyDataSetChanged();
        }


    }


    @NonNull
    @NotNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainAdapter.ViewHolder holder, int position) {
        String habitationNom = habitationsList.get(position).getName();
        holder.habitationName.setText(habitationNom);
    }

    @Override
    public int getItemCount() {
        return habitationsList.size();
    }


}