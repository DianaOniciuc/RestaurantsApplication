package com.example.diana.restaurantsapplication.restaurants;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diana.restaurantsapplication.R;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_KEY;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>{

    private ArrayList<ItemRestaurant> restaurants;
    private Context context;

    public RestaurantsAdapter(ArrayList<ItemRestaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        ItemRestaurant restaurant = restaurants.get(position);

        Glide.with(context)
                .load(restaurant.getImage())
                .into(holder.image);
        holder.title.setText(restaurant.getTitle());
        holder.subtitle.setText(restaurant.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size(): 0;
    }

    public class RestaurantsViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView image;
        private TextView title;
        private TextView subtitle;

        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.list_item_icon);
            title = itemView.findViewById(R.id.list_item_title);
            subtitle = itemView.findViewById(R.id.list_item_subtitle);
            itemView.setOnClickListener(v -> {
                ItemRestaurant restaurant = restaurants.get(getAdapterPosition());
                onItemClicked(restaurant);
            });
        }
    }

    private void onItemClicked(ItemRestaurant restaurant) {
        Intent intent = new Intent(context, RestaurantDetailsActivity.class);
        intent.putExtra(RESTAURANT_KEY, restaurant);
        context.startActivity(intent);
    }
}
