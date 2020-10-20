package com.example.diana.restaurantsapplication.restaurants;

import android.os.Bundle;
import android.transition.Fade;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diana.restaurantsapplication.R;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        initView();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade(Gravity.LEFT);
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    private void initView() {

        TextView restaurantsTV = findViewById(R.id.restaurantsTV);
    }
}
