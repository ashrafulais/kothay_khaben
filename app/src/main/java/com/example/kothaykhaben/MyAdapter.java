package com.example.kothaykhaben;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.Intent;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<MyItem> MyList;
    private Context context;

    public MyAdapter(List<MyItem> myList, Context context) {
        MyList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurantlist_rowlayout, parent, false);
        MyViewHolder VH = new MyViewHolder(v);

        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyItem myItemPosition = MyList.get(position);

        holder.headText.setText(myItemPosition.getHead());
        holder.descText.setText(myItemPosition.getDesc());
        Picasso.get().load(myItemPosition.getImageUrl()).into(holder.imageView);

        //Toast messege
        /*holder.headText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, myItemPosition.getHead(), Toast.LENGTH_SHORT).show();

                Context context = view.getContext();
                Intent intent = new Intent(context, RestaurantMenuActivity.class);
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return MyList.size();
    }



}
