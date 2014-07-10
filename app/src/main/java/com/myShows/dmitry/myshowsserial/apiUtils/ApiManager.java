package com.myShows.dmitry.myshowsserial.apiUtils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;

import com.myShows.dmitry.myshowsserial.activity.LoginActivity;
import com.myShows.dmitry.myshowsserial.asyncTask.GetAsyncTask;
import com.myShows.dmitry.myshowsserial.enums.EnumMethod;
import com.myShows.dmitry.myshowsserial.listener.ResultLoginListener;
import com.myShows.dmitry.myshowsserial.listener.ResultObjectListener;

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

    public void loginStart(String login, String password, final ResultObjectListener resultListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultListener) {
            @Override
            public void onNeedResult(ParamsBuilder paramsBuilder, ResultObjectListener resultObjectListener) {
                authorization(paramsBuilder, resultObjectListener);
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.LOGIN_START);
        paramsBuilder.addMethod("method",ParamsBuilder.LOGIN_METHOD);
        paramsBuilder.addParams("login", login);
        paramsBuilder.addParams("password", password);
        getAsyncTask.execute(paramsBuilder);
    }

    public void profileGet(final ResultObjectListener resultListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultListener) {
            @Override
            public void onNeedResult(ParamsBuilder paramsBuilder, ResultObjectListener resultObjectListener) {
                authorization(paramsBuilder, resultObjectListener);
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.PROFILE_GET);
        paramsBuilder.addMethod("method",ParamsBuilder.PROFILE_METHOD);
        getAsyncTask.execute(paramsBuilder);
    }

    public void getShows(final ResultObjectListener resultObjectListener) {

        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener) {
            @Override
            public void onNeedResult(ParamsBuilder paramsBuilder, ResultObjectListener resultObjectListener) {
                authorization(paramsBuilder, resultObjectListener);
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.SHOW);
        paramsBuilder.addMethod("method", ParamsBuilder.SHOWS_METHOD);
        getAsyncTask.execute(paramsBuilder);
    }

    private void authorization(final ParamsBuilder paramsBuilder, final ResultObjectListener resultObjectListener) {
        AccountManager accountManager = AccountManager.get(mContext);
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        if (accounts != null) {
            loginStart(accounts[0].name, accountManager.getPassword(accounts[0]), new ResultObjectListener() {

                @Override
                public void onResult(Object object) {
                    switch (paramsBuilder.getEnumMethod()) {
                        case PROFILE_GET:
                            profileGet(resultObjectListener);
                            break;
                        case SHOW:
                            getShows(resultObjectListener);
                            break;
                        case CURRENT_SHOW:
                            getCurrentShow(Integer.parseInt(paramsBuilder.getMethodByString("id")), resultObjectListener);
                            break;
                    }
                }

                @Override
                public void onErrorAuthorization() {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            });
        } else {
            startActivity();
        }
    }

    private void startActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    public void getCurrentShow(final int id, final ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener) {
            @Override
            public void onNeedResult(ParamsBuilder paramsBuilder, ResultObjectListener resultObjectListener) {
                authorization(paramsBuilder, resultObjectListener);
            }
        };
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.CURRENT_SHOW);
        paramsBuilder.addMethod("method",ParamsBuilder.SHOWS_METHOD);
        paramsBuilder.addMethod("id",String.format("%s/", id));
        getAsyncTask.execute(paramsBuilder);
    }

}
