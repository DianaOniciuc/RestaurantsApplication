package com.example.diana.restaurantsapplication.server;

import com.example.diana.restaurantsapplication.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {
    @GET("/restaurant/list")
    Call<List<Restaurant>> getRestaurants();
}
