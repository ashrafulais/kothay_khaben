package com.example.kothaykhaben;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class FoodMenuViewHolder extends RecyclerView.ViewHolder  {


    TextView headText;
    RatingBar rating;

    public FoodMenuViewHolder(@NonNull View itemView) {
        super(itemView);

        headText = (TextView) itemView.findViewById(R.id.recyclerFoodName);
        rating = (RatingBar) itemView.findViewById(R.id.recyclerFoodRating);

    }
}
