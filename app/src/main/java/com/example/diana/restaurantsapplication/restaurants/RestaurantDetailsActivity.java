package com.example.diana.restaurantsapplication.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diana.restaurantsapplication.R;
import com.example.diana.restaurantsapplication.adapters.PhotosAdapter;
import com.example.diana.restaurantsapplication.models.Photo;
import com.example.diana.restaurantsapplication.models.Restaurant;

import java.util.ArrayList;

import static com.example.diana.restaurantsapplication.util.Constants.RESTAURANT_KEY;

public class RestaurantDetailsActivity extends AppCompatActivity {
    Restaurant restaurant;
    private int toolbarSize;

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

        Guideline mapGuideline = findViewById(R.id.map_guideline);
        setupGuideline(mapGuideline);

        Intent intent = getIntent();
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

        setupSupportActionBar();

    }


    private void setupGuideline(Guideline mapGuideline) {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;

        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > height) {
            toolbarSize = realHeight - height;
        }

        mapGuideline.setGuidelineBegin((int) ((height-toolbarSize)*0.4));

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
