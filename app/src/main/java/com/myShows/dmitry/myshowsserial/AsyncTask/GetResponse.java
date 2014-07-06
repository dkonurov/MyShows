package com.myShows.dmitry.myshowsserial.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public abstract class GetResponse extends AsyncTask<String, HttpResponse, HttpResponse> {

    public static final int URL_POSITION = 0;
    private static final int LOGIN_POSITION = 1;
    private static final int PASSWORD_POSITION = 2;

    @Override
    protected HttpResponse doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        String url = String.format(params[URL_POSITION],
                params[LOGIN_POSITION], params[PASSWORD_POSITION]);
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            return response;

        } catch (IOException e) {
            Log.d("myShows", "error reading");
        }
        return null;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        onResultRequest(response);
    }

    abstract public void onResultRequest(HttpResponse response);
}
