package com.myShows.dmitry.myshowsserial.apiUtils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.activity.LoginActivity;
import com.myShows.dmitry.myshowsserial.asyncTask.GetRequest;
import com.myShows.dmitry.myshowsserial.listener.ResultJsonListener;
import com.myShows.dmitry.myshowsserial.listener.ResultLoginListener;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void loginStart(String login, String password, final ResultLoginListener resultListener) {
        GetRequest getRequest = new GetRequest(mContext) {
            @Override
            public void onResultRequest(HttpResponse response) {
                switch (response.getStatusLine().getStatusCode()) {
                    case ACCESS_AUTHORIZATION:
                        if (resultListener != null) {
                            resultListener.onResult();
                        }
                        break;
                    case ERROR_AUTHORIZATION:
                        resultListener.onErrorLoginResult();
                        Toast.makeText(mContext,
                                mContext.getString(R.string.error_login_or_password),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onResultNeedAuthorization() {
            }

        };
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.setMethod(ParamsBuilder.LOGIN_METHOD);
        paramsBuilder.addParams("login", login);
        paramsBuilder.addParams("password", password);
        getRequest.execute(paramsBuilder);
    }

    public void profileGet(final ResultJsonListener resultListener) {
        GetRequest getRequest = new GetRequest(mContext) {
            @Override
            public void onResultRequest(HttpResponse response) {
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

            @Override
            public void onResultNeedAuthorization() {
                AccountManager accountManager = AccountManager.get(mContext);
                Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
                if (accounts != null) {
                    loginStart(accounts[0].name, accountManager.getPassword(accounts[0]), new ResultLoginListener() {
                        @Override
                        public void onResult() {
                            profileGet(resultListener);
                        }

                        @Override
                        public void onErrorLoginResult() {
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }
                    });
                } else {
                    startActivity();
                }
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.setMethod(ParamsBuilder.PROFILE_METHOD);
        getRequest.execute(paramsBuilder);
    }

    public void getSerials(final ResultJsonListener resultJsonListener) {
        GetRequest getRequest = new GetRequest(mContext) {
            @Override
            public void onResultRequest(HttpResponse response) {
                BufferedReader reader = null;
                InputStreamReader inputReader = null;
                try {
                    inputReader = new InputStreamReader(response.getEntity().getContent(), Utils.UTF_8);
                    reader = new BufferedReader(inputReader);
                    String json = reader.readLine();
                    Log.d("json", json + "");
                    if (resultJsonListener != null) {
                        resultJsonListener.onJSONListener(json);
                    }
                } catch (IOException e) {
                    Log.d("myShows", "file don't read");
                } finally {
                    IOUtils.closeQuietly(reader);
                    IOUtils.closeQuietly(inputReader);
                }
            }

            @Override
            public void onResultNeedAuthorization() {
                AccountManager accountManager = AccountManager.get(mContext);
                Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
                if (accounts != null) {
                    loginStart(accounts[0].name, accountManager.getPassword(accounts[0]), new ResultLoginListener() {
                        @Override
                        public void onResult() {
                            getSerials(resultJsonListener);
                        }

                        @Override
                        public void onErrorLoginResult() {
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }
                    });
                } else {
                    startActivity();
                }
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.setMethod(ParamsBuilder.SHOWS_METHOD);
        getRequest.execute(paramsBuilder);
    }

    private void startActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

}
