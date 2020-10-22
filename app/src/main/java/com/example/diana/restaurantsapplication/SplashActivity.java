package com.example.diana.restaurantsapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;

import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diana.restaurantsapplication.restaurants.RestaurantsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        //setupWindowAnimations();
        startRestaurantActivity();
    }

    private void startRestaurantActivity() {

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, RestaurantsActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
            finish();
        }, 1000);

    }

    private void setupWindowAnimations() {
        Fade fade = new Fade(Gravity.START);
        fade.setDuration(1000);
        getWindow().setExitTransition(fade);
    }

    private void initView() {
        TextView tvSplash = findViewById(R.id.splashTV);
    }
}
