package com.example.diana.restaurantsapplication.restaurants;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diana.restaurantsapplication.R;
import com.example.diana.restaurantsapplication.adapters.PhotosAdapter;
import com.example.diana.restaurantsapplication.models.Photo;
import com.example.diana.restaurantsapplication.models.Restaurant;
import com.example.diana.restaurantsapplication.util.SharedPrefUtil;

import java.util.ArrayList;

import static com.example.diana.restaurantsapplication.util.Constants.LOG_TAG;
import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_ID;
import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_KEY;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private Restaurant restaurant;
    private Intent intent;
    private Toolbar toolbar;
    private SharedPrefUtil prefUtil;
    private String restaurantPrefKey;
    private boolean isBookmarked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_details);
        initView();
    }

    private void initView() {

        prefUtil = new SharedPrefUtil(this);

        AppCompatImageView image =  findViewById(R.id.appCompatImageView);
        AppCompatTextView title = findViewById(R.id.restaurant_detail_title);
        RecyclerView photos = findViewById(R.id.restaurant_gallery_rv);
        AppCompatTextView subtitle = findViewById(R.id.restaurant_detail_subtitle);

        intent = getIntent();
        if(intent.hasExtra(RESTAURANT_KEY)){
             restaurant = (Restaurant) intent.getSerializableExtra(RESTAURANT_KEY);
        }

        image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background));
        title.setText(restaurant.getName());
        subtitle.setText(restaurant.getDescription());

        photos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        photos.setHasFixedSize(true);

        PhotosAdapter adapter = new PhotosAdapter((ArrayList<Photo>) restaurant.getPhotos(),this);
        photos.setAdapter(adapter);

        //the SharedPreferences key for each restaurant is the item position in the RecyclerView, stored as a String
        restaurantPrefKey = String.valueOf(intent.getIntExtra(RESTAURANT_ID,0));

        setToolbar(restaurant.getName());

    }


    private void setToolbar(String name) {
        toolbar = findViewById(R.id.restaurant_details_toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        setToolbarBackButton();
    }

    private void setFavoriteIcon() {
        if(intent.hasExtra(RESTAURANT_ID)){
            isBookmarked = prefUtil.isInPreferences(restaurantPrefKey);
        }

        if(isBookmarked){
            MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_favorite);
            menuItem.setIcon(R.drawable.ic_baseline_favorite_red_24);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        setFavoriteIcon();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_favorite){
            if(isBookmarked){
                item.setIcon(R.drawable.ic_baseline_favorite_24);
                prefUtil.removeFromPreferences(restaurantPrefKey);
                isBookmarked = false;
                return true;
            }else {
                item.setIcon(R.drawable.ic_baseline_favorite_red_24);
                prefUtil.addToPreferences(restaurantPrefKey, restaurant.getName());
                isBookmarked = true;
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setToolbarBackButton() {
        if(getSupportActionBar() != null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

}
