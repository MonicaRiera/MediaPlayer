package com.example.mediaplayer.data;


public class Song {

    private String imageId;
    private String songId;
    private String title;
    private int duration;
    private String country;
    private String comments;
    //public static List<Song> songs = new ArrayList<>();

    public Song(String imageId, String songId, String title, int duration, String country, String comments) {
        this.imageId = imageId;
        this.songId = songId;
        this.title = title;
        this.duration = duration;
        this.country = country;
        this.comments = comments;

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
}
