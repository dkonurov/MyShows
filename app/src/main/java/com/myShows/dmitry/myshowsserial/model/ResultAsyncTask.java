package com.myShows.dmitry.myshowsserial.model;

public class ResultAsyncTask {

    private int mStatusCode;

    private String mJson;

    public ResultAsyncTask() {
        new ResultAsyncTask(0, new String());
    }

    public ResultAsyncTask(int statusCode, String json) {
        mStatusCode = statusCode;
        mJson = json;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int mStatusCode) {
        this.mStatusCode = mStatusCode;
    }

    public String getJson() {
        return mJson;
    }

    public void setJson(String mJson) {
        this.mJson = mJson;
    }
}
