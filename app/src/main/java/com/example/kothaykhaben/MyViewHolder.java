package com.example.kothaykhaben;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder  {


    ImageView imageView;
    TextView headText, descText;
    String resID, resLink;
    int resRating;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageId);
        headText = (TextView) itemView.findViewById(R.id.headId);
        descText = (TextView) itemView.findViewById(R.id.descriptionId);

    }


}
