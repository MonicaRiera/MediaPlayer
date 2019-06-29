package com.example.mediaplayer.data;


import com.google.android.gms.maps.model.LatLng;

public class Song {

    private String imageId;
    private String songId;
    private String title;
    private int duration;
    private String country;
    private String comments;
    private LatLng location;

    public Song(String imageId, String songId, String title, int duration, String country, String comments, LatLng location) {
        this.imageId = imageId;
        this.songId = songId;
        this.title = title;
        this.duration = duration;
        this.country = country;
        this.comments = comments;
        this.location = location;

    }

    public String getImageId() {
        return imageId;
    }

    public String getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getCountry() {
        return country;
    }

    public String getComments() {
        return comments;
    }

    public LatLng getLocation() {
        return location;
    }
}
