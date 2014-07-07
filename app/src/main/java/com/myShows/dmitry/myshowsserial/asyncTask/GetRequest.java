package com.myShows.dmitry.myshowsserial.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public abstract class GetRequest extends AsyncTask<String, HttpResponse, HttpResponse> {

    public static final int URL_POSITION = 0;
    public static final HttpClient sHttpClient = new DefaultHttpClient();

    @Override
    protected HttpResponse doInBackground(String... params) {
        HttpGet httpGet = new HttpGet(params[URL_POSITION]);
        HttpResponse response;
        try {
            response = sHttpClient.execute(httpGet);
            Log.d("check", response.getStatusLine().getStatusCode() + "");
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
