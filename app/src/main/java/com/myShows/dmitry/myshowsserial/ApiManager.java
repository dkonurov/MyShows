package com.myShows.dmitry.myshowsserial;

import android.accounts.AccountManager;
import android.content.Context;
import android.widget.Toast;

import com.myShows.dmitry.myshowsserial.AsyncTask.GetResponse;
import com.myShows.dmitry.myshowsserial.Listener.ResultListener;

import org.apache.http.HttpResponse;

public class ApiManager {
    private static final String URL_LOGIN = "http://api.myshows.ru/profile/login?login=%s&password=%s";
    public static final String ACCOUNT_TYPE = "com.myShowsSer";
    private static ApiManager mApiManager;
    private Context mContext;

    private ApiManager() {
    }

    public void setContext(Context context) {
        mContext = context;
    }



    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public void LoginStart(String login, String password, final ResultListener resultListener) {
        GetResponse getResponse = new GetResponse() {
            @Override
            public void onResultRequest(HttpResponse response) {
                switch (response.getStatusLine().getStatusCode()) {
                    case 200:
                        resultListener.onResult();
                        break;
                    case 403:
                        Toast.makeText(mContext,
                                mContext.getString(R.string.error_login_or_password),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        getResponse.execute(URL_LOGIN, login, password);
    }


}
