package com.example.setareh.musiclibrary;

public class Track {
    private int mPicture;
    private int mMusic;
    private String mTitle;

    public Track() {

    }

    public Track(int mPicture, int mMusic, String mTitle) {

        this.mPicture = mPicture;
        this.mMusic = mMusic;
        this.mTitle = mTitle;
    }

    public int getPicture() {
        return mPicture;
    }

    public int getMusic() {
        return mMusic;
    }

    public String getTitle() {
        return mTitle;
    }
}