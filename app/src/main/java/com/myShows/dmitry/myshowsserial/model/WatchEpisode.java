package com.myShows.dmitry.myshowsserial.model;

import com.google.gson.annotations.SerializedName;

public class WatchEpisode {

    @SerializedName("id")
    private int mId;

    @SerializedName("watchDate")
    private String mWatchDate;

    @SerializedName("rating")
    private int mRating;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getWatchDate() {
        return mWatchDate;
    }

    public void setWatchDate(String mWatchDate) {
        this.mWatchDate = mWatchDate;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int mRating) {
        this.mRating = mRating;
    }
}
