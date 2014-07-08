package com.myShows.dmitry.myshowsserial.model;


import com.google.gson.annotations.SerializedName;

public class Static {

    @SerializedName("watchedHours")
    private float mWatchedHours;

    @SerializedName("remainingHours")
    private float mRemainingHours;

    @SerializedName("watchedEpisodes")
    private int mWatchedEpisodes;

    @SerializedName("remainingEpisodes")
    private int mRemainingEpisodes;

    @SerializedName("totalEpisodes")
    private int mTotalEpisodes;

    @SerializedName("totalDays")
    private float mTotalDays;

    @SerializedName("totalHours")
    private float mTotalHours;

    @SerializedName("remainingDays")
    private float mRemainingDays;

    @SerializedName("watchedDays")
    private float mWatchedDays;

    public float getWatchedHours() {
        return mWatchedHours;
    }

    public void setWatchedHours(float mWatchedHours) {
        this.mWatchedHours = mWatchedHours;
    }

    public float getRemainingHours() {
        return mRemainingHours;
    }

    public void setRemainingHours(float mRemainingHours) {
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

    public float getTotalDays() {
        return mTotalDays;
    }

    public void setTotalDays(float mTotalDays) {
        this.mTotalDays = mTotalDays;
    }

    public float getTotalHours() {
        return mTotalHours;
    }

    public void setTotalHours(float mTotalHours) {
        this.mTotalHours = mTotalHours;
    }

    public float getRemainingDays() {
        return mRemainingDays;
    }

    public void setRemainingDays(float mRemainingDays) {
        this.mRemainingDays = mRemainingDays;
    }

    public float getWatchedDays() {
        return mWatchedDays;
    }

    public void setWatchedDays(float mWatchedDays) {
        this.mWatchedDays = mWatchedDays;
    }
}
