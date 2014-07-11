package com.myShows.dmitry.myshowsserial.model;

import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("episodeId")
    private int mEpisodeId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("showId")
    private int mShowId;

    @SerializedName("seasonNumber")
    private int mSeasonNumber;

    @SerializedName("episodeNumber")
    private int mEpisodeNumber;

    @SerializedName("airDate")
    private String mAirDate;

    public int getEpisodeId() {
        return mEpisodeId;
    }

    public void setEpisodeId(int mEpisodeId) {
        this.mEpisodeId = mEpisodeId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getShowId() {
        return mShowId;
    }

    public void setShowId(int mShowId) {
        this.mShowId = mShowId;
    }

    public int getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(int mSeasonNumber) {
        this.mSeasonNumber = mSeasonNumber;
    }

    public int getEpisodeNumber() {
        return mEpisodeNumber;
    }

    public void setEpisodeNumber(int mEpisodeNumber) {
        this.mEpisodeNumber = mEpisodeNumber;
    }

    public String getAirDate() {
        return mAirDate;
    }

    public void setAirDate(String mAirDate) {
        this.mAirDate = mAirDate;
    }
}
