package com.myShows.dmitry.myshowsserial.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.apiUtils.ParamsBuilder;
import com.myShows.dmitry.myshowsserial.cookie.PersistentCookieStore;
import com.myShows.dmitry.myshowsserial.listener.ResultObjectListener;
import com.myShows.dmitry.myshowsserial.model.ResultAsyncTask;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class GetAsyncTask extends AsyncTask<ParamsBuilder, Void, Object> {

    public static final int PARAMS_DEFAULT_POSITION = 0;
    private static HttpClient sHttpClient;
    private Context mContext;
    private ResultObjectListener mResultObjectListener;

    public GetAsyncTask(Context context, ResultObjectListener resultObjectListener) {
        if (sHttpClient == null) {
            sHttpClient = new DefaultHttpClient();
        }
        mResultObjectListener = resultObjectListener;
        mContext = context;
    }

    @Override
    protected Object doInBackground(ParamsBuilder... params) {
        HttpGet httpGet = new HttpGet(params[PARAMS_DEFAULT_POSITION].getUrl());
        Log.d("checkRequest", params[PARAMS_DEFAULT_POSITION].getUrl());
        HttpResponse response;
        HttpContext httpContext = new BasicHttpContext();
        PersistentCookieStore cookie = new PersistentCookieStore(mContext);
        if (cookie.getCookies() == null) {
            CookieStore cookieStore = new BasicCookieStore();
            cookie.addCookie((org.apache.http.cookie.Cookie) cookieStore);
        }
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookie);

        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;

        try {
            response = sHttpClient.execute(httpGet);
            Log.d("check", response.getStatusLine().getStatusCode() + "");
            inputStreamReader = new InputStreamReader(response.getEntity().getContent());
            bufferedReader = new BufferedReader(inputStreamReader);
            String json = bufferedReader.readLine();
            ResultAsyncTask resultAsyncTask = new ResultAsyncTask();
            resultAsyncTask.setJson(json);
            resultAsyncTask.setStatusCode(response.getStatusLine().getStatusCode());
            Log.d("json", resultAsyncTask.getJson());
            switch (resultAsyncTask.getStatusCode()) {
                case ApiManager.NEED_AUTHORIZATION:
                    onNeedResult(params[0], mResultObjectListener);
                    break;
                case ApiManager.ERROR_AUTHORIZATION:
                    return null;
                case ApiManager.ACCESS_AUTHORIZATION:
                    return Parser.getInstance().parseMethod(params[0].getEnumMethod(), json);
            }
            return null;

        } catch (IOException e) {
            Log.d("myShows", "error reading");
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(bufferedReader);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        mResultObjectListener.onResult(object);
    }

    abstract public void onNeedResult(ParamsBuilder paramsBuilder, ResultObjectListener resultObjectListener);
}
