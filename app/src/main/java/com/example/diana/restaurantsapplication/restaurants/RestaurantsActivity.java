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

        //restaurants = getMockedData();
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

    private ArrayList<ItemRestaurant> getMockedData() {
        ArrayList<ItemRestaurant> restaurants = new ArrayList<>();
        restaurants.add(new ItemRestaurant("https://media-cdn.tripadvisor.com/media/photo-s/06/c6/e4/7e/the-clink-restaurant.jpg", "The Clink Restaurant","The Clink Restaurant at HMP Brixton opened in February 2014 as the third restaurant operated by The Clink Charity in the UK and offers up to 30 prisoners full-time " +
                "work within the restaurant and kitchen. Prisoners train towards gaining nationally recognised City & Guilds NVQs before returning to their cells at the end of the working day. Upon their release, The Clink" +
                " Charity helps graduates find employment within the catering and hospitality industry. The restaurant is a flagship example of a unique charitable project that is committed to reducing reoffending rates, in" +
                " partnership with HMPS. The restaurant positively promotes the rehabilitation and reintegration of prisoners and also champions the wealth of quality, fresh and organic produce available throughout the UK " +
                "with many vegetables and herbs being sourced from the prison gardens. The restaurant is located in the former Governor's house built in 1819, the 120-cover restaurant is available for exclusive hire. " +
                "Breakfast is served from Monday to Friday between 8:15am and 10:30am, and lunch is served from Monday to Friday between 12pm and 3pm. ", new ArrayList<>(Arrays.asList("https://media-cdn.tripadvisor.com/media/photo-l/06/c6/e4/7e/the-clink-restaurant.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-l/06/c6/e4/46/the-clink-restaurant.jpg", "https://media-cdn.tripadvisor.com/media/photo-l/06/c6/e3/d2/the-clink-restaurant.jpg" )) ));

        restaurants.add(new ItemRestaurant("https://media-cdn.tripadvisor.com/media/photo-s/14/8a/6e/59/elegant-decoration.jpg", "The Chelsea Corner","Authentic Italian for us always means the freshest ingredients that are rightly dictated by the season. " +
                "Everything here is homemade and sourced with love and passion, from our daily landed fish to vegetables from New Covent Garden Market. The Chelsea Corner offers a rich Italian menu choice and something for everyone.",
                new ArrayList<>(Arrays.asList("https://media-cdn.tripadvisor.com/media/photo-l/14/8a/6e/59/elegant-decoration.jpg", "https://media-cdn.tripadvisor.com/media/photo-l/15/e3/76/a3/gamberoni-alla-griglia.jpg",
                        "https://media-cdn.tripadvisor.com/media/photo-l/15/e3/01/da/mama-s-pancakes-brunch.jpg","https://media-cdn.tripadvisor.com/media/photo-l/15/e3/6e/3e/fresh-burrata-cheese.jpg"))));

        restaurants.add(new ItemRestaurant("https://media-cdn.tripadvisor.com/media/photo-s/19/5f/35/54/salmon-teriyaki.jpg", "Companero", "Compañero is the new Street Tapas brought to you by Nikolaus Greig and utilising his vast experience in food & wine. " +
                "After many years working in successful restaurants he has taken his cooking skills and knowledge a step further. Compañero is a street food concept that will exhibit fine Spanish Tapas in Londons best locations.",
                new ArrayList<>(Arrays.asList("https://media-cdn.tripadvisor.com/media/photo-l/19/5f/35/54/salmon-teriyaki.jpg", "https://media-cdn.tripadvisor.com/media/photo-l/19/70/c6/40/moroccan-breakfast.jpg",
                        "https://media-cdn.tripadvisor.com/media/photo-l/19/5f/34/b7/tomatoes.jpg", "https://media-cdn.tripadvisor.com/media/photo-l/19/5f/34/f6/chicken-fajitas.jpg", "https://media-cdn.tripadvisor.com/media/photo-l/19/5f/35/49/arroz-gambas.jpg"))));
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
        startActivity(intent);
    }
}
