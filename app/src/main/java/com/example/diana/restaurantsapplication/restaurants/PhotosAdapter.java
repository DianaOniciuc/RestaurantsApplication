package com.example.diana.restaurantsapplication.restaurants;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diana.restaurantsapplication.R;
import com.example.diana.restaurantsapplication.models.Photo;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>{

    private ArrayList<Photo> photos;
    private Context context;

    public PhotosAdapter(ArrayList<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        String photoPath = photos.get(position).getImagePath();
        Glide.with(context).load(photoPath).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView photo;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.restaurant_photo);
        }
    }

}
