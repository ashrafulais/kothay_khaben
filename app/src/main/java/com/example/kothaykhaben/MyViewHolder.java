package com.example.kothaykhaben;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder  {


    ImageView imageView;
    TextView headText, descText;
    Button linkBtn;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageId);
        headText = (TextView) itemView.findViewById(R.id.headId);
        descText = (TextView) itemView.findViewById(R.id.descriptionId);
        linkBtn = (Button) itemView.findViewById(R.id.resSocialLinkBtn);
    }


}
