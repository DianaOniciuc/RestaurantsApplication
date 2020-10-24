package com.example.diana.restaurantsapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diana.restaurantsapplication.R;
import com.example.diana.restaurantsapplication.models.Restaurant;

import java.util.ArrayList;


public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>{

    private ArrayList<Restaurant> restaurants;
    private Context context;

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    private OnRestaurantClickListener onRestaurantClickListener;

    public RestaurantsAdapter(ArrayList<Restaurant> restaurants, Context context, OnRestaurantClickListener onRestaurantClickListener) {
        this.restaurants = restaurants;
        this.context = context;
        this.onRestaurantClickListener = onRestaurantClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantsViewHolder(view, onRestaurantClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        Glide.with(context)
                .load(restaurant.getImagePath())
                .into(holder.image);
        holder.title.setText(restaurant.getName());
        holder.subtitle.setText(restaurant.getDescription());
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size(): 0;
    }

    public static class RestaurantsViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView image;
        private TextView title;
        private TextView subtitle;
        OnRestaurantClickListener onRestaurantClickListener;

        public RestaurantsViewHolder(@NonNull View itemView, OnRestaurantClickListener onRestaurantClickListener) {
            super(itemView);
            image= itemView.findViewById(R.id.list_item_icon);
            title = itemView.findViewById(R.id.list_item_title);
            subtitle = itemView.findViewById(R.id.list_item_subtitle);
            this.onRestaurantClickListener = onRestaurantClickListener;
            itemView.setOnClickListener(v -> {
                onRestaurantClickListener.onRestaurantClick(getAdapterPosition());
            });
        }
    }

    public interface OnRestaurantClickListener{
        void onRestaurantClick(int position);
    }
}
