package com.myShows.dmitry.myshowsserial.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.apiUtils.ParamsBuilder;
import com.myShows.dmitry.myshowsserial.cookie.PersistentCookieStore;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public abstract class GetRequest extends AsyncTask<ParamsBuilder, HttpResponse, HttpResponse> {

    public static final int PARAMS_DEFAULT_POSITION = 0;
    private static HttpClient sHttpClient;
    private Context mContext;

    public GetRequest(Context context) {
        if (sHttpClient == null) {
            sHttpClient = new DefaultHttpClient();
        }
        mContext = context;
    }

    @Override
    protected HttpResponse doInBackground(ParamsBuilder... params) {
        HttpGet httpGet = new HttpGet(params[PARAMS_DEFAULT_POSITION].getUrl());
        HttpResponse response;
        HttpContext httpContext = new BasicHttpContext();
        PersistentCookieStore cookie = new PersistentCookieStore(mContext);
        if (cookie.getCookies() == null) {
            CookieStore cookieStore = new BasicCookieStore();
            cookie.addCookie((org.apache.http.cookie.Cookie) cookieStore);
        }
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookie);

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
        switch (response.getStatusLine().getStatusCode()) {
            case ApiManager.NEED_AUTHORIZATION:
                onResultNeedAuthorization();
                break;
            default:
                onResultRequest(response);
                break;
        }
    }

    abstract public void onResultRequest(HttpResponse response);
    abstract public void onResultNeedAuthorization();
}
