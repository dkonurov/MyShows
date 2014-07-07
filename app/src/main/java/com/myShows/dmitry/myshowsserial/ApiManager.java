package com.myShows.dmitry.myshowsserial;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.myShows.dmitry.myshowsserial.activity.LoginActivity;
import com.myShows.dmitry.myshowsserial.asyncTask.GetRequest;
import com.myShows.dmitry.myshowsserial.listener.ResultJsonListener;
import com.myShows.dmitry.myshowsserial.listener.ResultListener;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ApiManager {
    public static final String ACCOUNT_TYPE = "com.myShowsSer";
    public static final int ERROR_AUTHORIZATION = 403;
    public static final int NEED_AUTHORIZATION = 401;
    public static final int ACCESS_AUTHORIZATION = 200;
    private static ApiManager INSTANCE;
    private Context mContext;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiManager();
        }
        return INSTANCE;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void loginStart(String login, String password, final ResultListener resultListener) {
        if (!isLoginActivityStart()) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
        }
        GetRequest getRequest = new GetRequest() {
            @Override
            public void onResultRequest(HttpResponse response) {
                switch (response.getStatusLine().getStatusCode()) {
                    case ACCESS_AUTHORIZATION:
                        if (resultListener != null) {
                            resultListener.onResult();
                        }
                        break;
                    case ERROR_AUTHORIZATION:

                        Toast.makeText(mContext,
                                mContext.getString(R.string.error_login_or_password),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.setMethod(ParamsBuilder.LOGIN_METHOD);
        paramsBuilder.addParams("login", login);
        paramsBuilder.addParams("password", password);
        getRequest.execute(paramsBuilder.getUrl());
    }

    private boolean isLoginActivityStart() {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List< ActivityManager.RunningTaskInfo > task = activityManager.getRunningTasks(1);
        ComponentName componentInfo = task.get(0).topActivity;

        // Check if it matches our package name.
        if(componentInfo.getPackageName().equals(mContext.getPackageName())) return true;
        return false;
    }

    public void profileGet(final ResultJsonListener resultListener) {
        GetRequest getRequest = new GetRequest() {
            @Override
            public void onResultRequest(HttpResponse response) {
                switch (response.getStatusLine().getStatusCode()) {
                    case NEED_AUTHORIZATION:
                        AccountManager accountManager = AccountManager.get(mContext);
                        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
                        if (accounts != null) {
                            loginStart(accounts[0].name, accountManager.getPassword(accounts[0]), new ResultListener() {
                                @Override
                                public void onResult() {
                                    profileGet(resultListener);
                                }
                            });
                        }
                        break;
                    default:
                        BufferedReader reader = null;
                        InputStreamReader inputReader = null;
                        try {
                            inputReader = new InputStreamReader(response.getEntity().getContent(), Utils.UTF_8);
                            reader = new BufferedReader(inputReader);
                            String json = reader.readLine();
                            Log.d("json", json + "");
                            if (resultListener != null) {
                                resultListener.onJSONListener(json);
                            }
                        } catch (IOException e) {
                            Log.d("myShows", "file don't read");
                        } finally {
                            IOUtils.closeQuietly(reader);
                            IOUtils.closeQuietly(inputReader);
                        }
                }
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.setMethod(ParamsBuilder.PROFILE_METHOD);
        getRequest.execute(paramsBuilder.getUrl());
    }


}
