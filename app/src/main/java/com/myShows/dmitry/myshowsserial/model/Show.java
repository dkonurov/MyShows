package com.myShows.dmitry.myshowsserial.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show {

    @SerializedName("showId")
    private int mShowId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("ruTitle")
    private String mRuTitle;

    @SerializedName("showStatus")
    private String mShowStatus;

    @SerializedName("runtime")
    private int mRunTime;

    @SerializedName("watchStatus")
    private String mWatchStatus;

    @SerializedName("watchedEpisodes")
    private int mWatchedEpisodes;

    @SerializedName("totalEpisodes")
    private int mTotalEpisodes;

    @SerializedName("rating")
    private int mRating;

    @SerializedName("image")
    private String mImageUrl;

    private Bitmap mImage;

    private List<Episode> mEpisodeList;

    public List<Episode> getSeriesList() {
        return mEpisodeList;
    }

    public void setSeriesList(List<Episode> mEpisodeList) {
        this.mEpisodeList = mEpisodeList;
    }

    public int getShowId() {
        return mShowId;
    }

    public void setShowId(int mShowId) {
        this.mShowId = mShowId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getRuTitle() {
        return mRuTitle;
    }

    public void setRuTitle(String mRuTitle) {
        this.mRuTitle = mRuTitle;
    }

    public String getShowStatus() {
        return mShowStatus;
    }

    public void setShowStatus(String mShowStatus) {
        this.mShowStatus = mShowStatus;
    }

    public int getRunTime() {
        return mRunTime;
    }

    public void setRunTime(int mRunTime) {
        this.mRunTime = mRunTime;
    }

    public String getWatchStatus() {
        return mWatchStatus;
    }

    public void setWatchStatus(String mWatchStatus) {
        this.mWatchStatus = mWatchStatus;
    }

    public int getWatchedEpisodes() {
        return mWatchedEpisodes;
    }

    public void setWatchedEpisodes(int mWatchedEpisodes) {
        this.mWatchedEpisodes = mWatchedEpisodes;
    }

    public int getTotalEpisodes() {
        return mTotalEpisodes;
    }

    public void setTotalEpisodes(int mTotalEpisodes) {
        this.mTotalEpisodes = mTotalEpisodes;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int mRating) {
        this.mRating = mRating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }
}
