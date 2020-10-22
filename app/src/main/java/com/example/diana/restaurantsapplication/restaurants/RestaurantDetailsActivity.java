package com.example.diana.restaurantsapplication.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diana.restaurantsapplication.R;

import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_KEY;

public class RestaurantDetailsActivity extends AppCompatActivity {
    ItemRestaurant restaurant;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_details);
        initView();
    }

    private void initView() {
        AppCompatImageView image =  findViewById(R.id.appCompatImageView);
        AppCompatTextView title = findViewById(R.id.restaurant_detail_title);
        RecyclerView photos = findViewById(R.id.restaurant_gallery_rv);
        AppCompatTextView subtitle = findViewById(R.id.restaurant_detail_subtitle);

        Intent intent = getIntent();
        if(intent.hasExtra(RESTAURANT_KEY)){
             restaurant = (ItemRestaurant) intent.getSerializableExtra(RESTAURANT_KEY);
        }

        image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background));
        title.setText(restaurant.getTitle());
        subtitle.setText(restaurant.getSubtitle());

        photos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        photos.setHasFixedSize(true);

        PhotosAdapter adapter = new PhotosAdapter(restaurant.getPhotos(),this);
        photos.setAdapter(adapter);

        setupSupportActionBar();

    }

    private void setupSupportActionBar() {
        if(getSupportActionBar() != null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
