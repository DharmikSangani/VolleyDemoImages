package com.example.volleydemoimages;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleHolder> {
    MainActivity mainActivity;
    ArrayList<Data_Model> dataModalList;

    public RecyclerAdapter(MainActivity mainActivity, ArrayList<Data_Model> dataModalList) {
        this.mainActivity = mainActivity;
        this.dataModalList = dataModalList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.main_item,parent,false);
        RecycleHolder holder = new RecycleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecycleHolder holder, int position) {
        holder.txt1.setText(""+dataModalList.get(position).getId());
        holder.txt2.setText(""+dataModalList.get(position).getTitle());
        Glide
                .with(mainActivity)
                .load(dataModalList.get(position).url)
                .centerCrop()
                .placeholder(R.drawable.animation)
                .into(holder.img);

        //holder.img.setImageURI(Uri.parse(""+dataModalList.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return dataModalList.size();
    }


    public class RecycleHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2;
        ImageView img;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.item_id);
            txt2 = itemView.findViewById(R.id.item_title);
            img = itemView.findViewById(R.id.item_images);

        }
    }
}

