package com.example.kothaykhaben;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuViewHolder> {

    private List<MyItem> MyList;
    private Context context;

    public FoodMenuAdapter(List<MyItem> myList, Context context) {
        MyList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodmenu_layout, parent, false);
        FoodMenuViewHolder VH = new FoodMenuViewHolder(v);

        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenuViewHolder holder, int position) {
        final MyItem myItemPosition = MyList.get(position);

        holder.headText.setText(myItemPosition.getFoodName());
        holder.rating.setRating(myItemPosition.getFoodRating());

    }

    @Override
    public int getItemCount() {
        return MyList.size();
    }


}
