package com.myShows.dmitry.myshowsserial.model;


import com.google.gson.annotations.SerializedName;

public class Static {

    @SerializedName("watchedHours")
    private int mWatchedHours;

    @SerializedName("remainingHours")
    private int mRemainingHours;

    @SerializedName("watchedEpisodes")
    private int mWatchedEpisodes;

    @SerializedName("remainingEpisodes")
    private int mRemainingEpisodes;

    @SerializedName("totalEpisodes")
    private int mTotalEpisodes;

    @SerializedName("totalDays")
    private int mTotalDays;

    @SerializedName("totalHours")
    private int mTotalHours;

    @SerializedName("remainingDays")
    private int mRemainingDays;

    @SerializedName("watchedDays")
    private int mWatchedDays;

    public int getWatchedHours() {
        return mWatchedHours;
    }

    public void setWatchedHours(int mWatchedHours) {
        this.mWatchedHours = mWatchedHours;
    }

    public int getmRemainingHours() {
        return mRemainingHours;
    }

    public void setRemainingHours(int mRemainingHours) {
        this.mRemainingHours = mRemainingHours;
    }

    public int getWatchedEpisodes() {
        return mWatchedEpisodes;
    }

    public void setWatchedEpisodes(int mWatchedEpisodes) {
        this.mWatchedEpisodes = mWatchedEpisodes;
    }

    public int getRemainingEpisodes() {
        return mRemainingEpisodes;
    }

    public void setRemainingEpisodes(int mRemainingEpisodes) {
        this.mRemainingEpisodes = mRemainingEpisodes;
    }

    public int getTotalEpisodes() {
        return mTotalEpisodes;
    }

    public void setTotalEpisodes(int mTotalEpisodes) {
        this.mTotalEpisodes = mTotalEpisodes;
    }

    public int getTotalDays() {
        return mTotalDays;
    }

    public void setTotalDays(int mTotalDays) {
        this.mTotalDays = mTotalDays;
    }

    public int getTotalHours() {
        return mTotalHours;
    }

    public void setTotalHours(int mTotalHours) {
        this.mTotalHours = mTotalHours;
    }

    public int getRemainingDays() {
        return mRemainingDays;
    }

    public void setRemainingDays(int mRemainingDays) {
        this.mRemainingDays = mRemainingDays;
    }

    public int getWatchedDays() {
        return mWatchedDays;
    }

    public void setWatchedDays(int mWatchedDays) {
        this.mWatchedDays = mWatchedDays;
    }
}
