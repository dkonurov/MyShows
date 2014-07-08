package com.myShows.dmitry.myshowsserial.apiUtils;

import android.util.Log;

public class ParamsBuilder {
    private static final String DEFAULT_API_STRING = "http://api.myshows.ru/";
    public static final String LOGIN_METHOD="profile/login";
    public static final String PROFILE_METHOD = "profile/";
    public static final String SHOWS_METHOD = "profile/shows/";
    private String mGetUrl;

    private boolean mIsFirstParams = true;
    private boolean mIsSetMethod = false;

    public void setMethod(String method) {
        mGetUrl = String.format("%s%s", DEFAULT_API_STRING, method);
        mIsSetMethod = true;
    }

    public void addParams(String name, String value) {
        if (!mIsSetMethod) {
            Log.d("myShows", "please first set method");
        } else {
            if (mIsFirstParams) {
                mGetUrl = String.format("%s?%s=%s", mGetUrl, name, value);
                mIsFirstParams = false;
            } else {
                mGetUrl = String.format("%s&%s=%s", mGetUrl, name, value);
            }
        }
    }

    public String getUrl() {
        return mGetUrl;
    }
}
