package com.myShows.dmitry.myshowsserial.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    private String mLogin;

    @SerializedName("avatar")
    private String mAvatarUrl;

    @SerializedName("wastedTime")
    private int mWastedTime;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("friends")
    private User[] mFriends;

    @SerializedName("stats")
    private Static mStatic;

    private Bitmap mImage;

    private Show[] mShow;

    public Static getStatic() {
        return mStatic;
    }

    public void setStatic(Static mStatic) {
        this.mStatic = mStatic;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String mAvatar) {
        this.mAvatarUrl = mAvatar;
    }

    public int getWastedTime() {
        return mWastedTime;
    }

    public void setWastedTime(int mWastedTime) {
        this.mWastedTime = mWastedTime;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public User[] getFriends() {
        return mFriends;
    }


    public void setFriends(User[] mFriends) {
        this.mFriends = mFriends;
    }

    public Show[] getSerial() {
        return mShow;
    }

    public void setSerial(Show[] mShow) {
        this.mShow = mShow;
    }
}
