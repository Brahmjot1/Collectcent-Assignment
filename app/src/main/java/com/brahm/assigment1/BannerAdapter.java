package com.brahm.assigment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    Context context;
    ArrayList<BannerModel> bannerModelsArrayList;
    BannerAdapter(Context context,ArrayList bannerModelsArrayList)
    {
        this.context=context;
        this.bannerModelsArrayList=bannerModelsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.banner_model,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Glide.with(context).load(bannerModelsArrayList.get(position).getUrl()).into(holder.homeBanner);
    }

    @Override
    public int getItemCount() {
        return bannerModelsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView homeBanner;
        public ViewHolder(@NonNull View itemView)

        {
            super(itemView);
            homeBanner=itemView.findViewById(R.id.imgbanner);

        }
    }
}
