package com.myShows.dmitry.myshowsserial.model;

import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("episodeId")
    private Integer mEpisodeId;

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

    @SerializedName("id")
    private Integer mId;

    @SerializedName("watchDate")
    private String mWatchDate;

    @SerializedName("rating")
    private int mRating;

    private boolean mIsWatched = false;

    public boolean isWatched() {
        return mIsWatched;
    }

    public void setIsWatched(Boolean mIsWatched) {
        this.mIsWatched = mIsWatched;
    }

    public Integer getId() {
      if (mId == null && mEpisodeId == null) {
          return null;
      } else if (mId == null) {
          return mEpisodeId;
      } else {
          return mId;
      }
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
