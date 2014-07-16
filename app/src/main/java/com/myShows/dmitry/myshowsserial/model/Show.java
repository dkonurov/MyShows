package com.myShows.dmitry.myshowsserial.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private float mRating;

    @SerializedName("image")
    private String mImageUrl;

    private Bitmap mImage;

    private Map<String, Episode> mEpisodeMap;

    @SerializedName("kinopoiskId")
    private int mKinopoiskId;

    @SerializedName("tvrageId")
    private int mTvrageId;

    @SerializedName("watching")
    private int mWatching;

    @SerializedName("imdbId")
    private int mImdbId;

    @SerializedName("voted")
    private int mVoted;

    public int getKinopoiskId() {
        return mKinopoiskId;
    }

    public void setKinopoiskId(int mKinopoiskId) {
        this.mKinopoiskId = mKinopoiskId;
    }

    public int getTvrageId() {
        return mTvrageId;
    }

    public void setTvrageId(int mTvrageId) {
        this.mTvrageId = mTvrageId;
    }

    public int getWatching() {
        return mWatching;
    }

    public void setWatching(int mWatching) {
        this.mWatching = mWatching;
    }

    public int getImdbId() {
        return mImdbId;
    }

    public void setImdbId(int mImdbId) {
        this.mImdbId = mImdbId;
    }

    public int getVoted() {
        return mVoted;
    }

    public void setVoted(int mVoted) {
        this.mVoted = mVoted;
    }

    public Map<String, Episode> getEpisodeMap() {
        return mEpisodeMap;
    }

    public void setEpisodeMap(Map<String, Episode> episodeMap) {
        this.mEpisodeMap = episodeMap;
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

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
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

    public List<Episode> getEpisodeList() {
        List<Episode> episodes = new ArrayList<Episode>();
        Set<Map.Entry<String,Episode>> set = mEpisodeMap.entrySet();
        for (Map.Entry<String, Episode> entry : set) {
            episodes.add(entry.getValue());
        }
        return episodes;
    }
}
