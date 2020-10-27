package com.example.diana.restaurantsapplication.util;

import android.content.Context;
import android.content.SharedPreferences;


public final class SharedPrefUtil {

    public static final String PREFERRED_RESTAURANTS = "preferred_restaurants";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public final void addToPreferences(String key, String value){
        editor.putString(key, value);
        editor.apply();
    }

    public final void removeFromPreferences(String key){
        editor.remove(key);
        editor.apply();
    }

    public boolean isInPreferences(String key){
        return preferences.contains(key);
    }

    public SharedPrefUtil(Context context){
        this.preferences = context.getSharedPreferences(PREFERRED_RESTAURANTS, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }
}
