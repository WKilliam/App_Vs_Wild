package com.learn.app_vs_wild;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapteurRecyclerview extends RecyclerView.Adapter<AdapteurRecyclerview.ViewHolder> {

    List<String> titre;
    List<Integer> image;
    LayoutInflater inflater;


    public AdapteurRecyclerview(Context ctx,List<String> titre,List<Integer> image){
        this.titre =titre;
        this.image = image;
        this.inflater= LayoutInflater.from(ctx);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custumlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.iconView.setImageResource(image.get(position));
        holder.textTitre.setText(titre.get(position));
    }

    @Override
    public int getItemCount() {
        return titre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView  iconView;
        TextView textTitre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitre = itemView.findViewById(R.id.titreViewPath);
            iconView = itemView.findViewById(R.id.iconImagePath);
        }
    }
}
