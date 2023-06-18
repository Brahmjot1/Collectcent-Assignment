package com.brahm.assigment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TodayStoryAdapter extends Adapter<TodayStoryAdapter.ViewHolder> {

    Context context;
    ArrayList<TodayStoryModal> arrayList;
    TodayStoryAdapter(Context context,ArrayList arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.today_story_modal,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Glide.with(context).load(arrayList.get(position).getImageView()).into(holder.imgTodayStory);
       holder.txtTodayStory.setText(arrayList.get(position).txtStoryView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
       ImageView imgTodayStory;
       TextView txtTodayStory;
        public ViewHolder(@NonNull View itemView)

        {
            super(itemView);
            imgTodayStory=itemView.findViewById(R.id.imgTodayStory);
            txtTodayStory=itemView.findViewById(R.id.txtTodayStory);
        }
    }
}
