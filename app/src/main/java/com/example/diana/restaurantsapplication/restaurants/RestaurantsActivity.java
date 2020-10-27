package com.example.diana.restaurantsapplication.restaurants;

import android.content.Intent;
import android.os.Bundle;

import android.transition.Fade;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diana.restaurantsapplication.R;
import com.example.diana.restaurantsapplication.adapters.RestaurantsAdapter;
import com.example.diana.restaurantsapplication.models.ItemRestaurant;
import com.example.diana.restaurantsapplication.models.Restaurant;
import com.example.diana.restaurantsapplication.server.RestaurantsServiceProvider;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_ID;
import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_KEY;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantsAdapter.OnRestaurantClickListener {

    private ContentLoadingProgressBar progressBar;
    private ArrayList<Restaurant> restaurants;
    private RestaurantsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        initView();
    }


    private void initView() {

        progressBar = findViewById(R.id.progress_bar);
        showProgressBar(progressBar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        restaurants = getRestaurantsRetrofit();
        adapter = new RestaurantsAdapter(restaurants, this, this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Restaurant> getRestaurantsRetrofit() {
        RestaurantsServiceProvider.createRestaurantService().getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                restaurants = (ArrayList<Restaurant>) response.body();
                if(restaurants!=null){
                    adapter.setRestaurants(restaurants);
                    adapter.notifyDataSetChanged();
                    progressBar.hide();
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(RestaurantsActivity.this, R.string.get_restaurants_error, Toast.LENGTH_SHORT).show();
            }
        });
        return restaurants;
    }


    private void showProgressBar(ContentLoadingProgressBar progressBar) {
          progressBar.show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onRestaurantClick(int position) {
        Intent intent = new Intent(this, RestaurantDetailsActivity.class);
        intent.putExtra(RESTAURANT_KEY,restaurants.get(position));
        intent.putExtra(RESTAURANT_ID, position);
        startActivity(intent);
    }
}
