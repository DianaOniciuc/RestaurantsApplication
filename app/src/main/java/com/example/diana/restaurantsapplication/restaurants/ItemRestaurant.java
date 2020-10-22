package com.example.diana.restaurantsapplication.restaurants;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemRestaurant implements Serializable {

    private String image;
    private String title;
    private String subtitle;
    private ArrayList<String> photos;

    public ItemRestaurant(String image, String title, String subtitle, ArrayList<String> photos) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.photos = photos;
    }

    public ItemRestaurant(String image, String title, String subtitle) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

}
