package com.example.smart_pantry.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_pantry.DetailedRecipe;
import com.example.smart_pantry.ExploreAll;
import com.example.smart_pantry.R;
import com.example.smart_pantry.model.Card;
import com.example.smart_pantry.model.RecipeTab;

import java.util.List;

public class RecipeTabAdapter extends RecyclerView.Adapter<RecipeTabAdapter.ViewHolder>{

    private Context context;
    private List<RecipeTab> recipeTabList;
    public RecipeTabAdapter(Context context, List<RecipeTab> recipeTabList) {
        this.context = context;
        this.recipeTabList = recipeTabList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_tab_layout, parent, false);
        return new RecipeTabAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeTab recipeTab = recipeTabList.get(position);
        holder.textTitle.setText(recipeTab.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailedRecipe.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeTabList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.recipeTextTitle);
            cardView = itemView.findViewById(R.id.recipeCardView);
        }
    }
}
