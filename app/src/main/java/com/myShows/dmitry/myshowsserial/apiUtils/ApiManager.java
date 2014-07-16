package com.myShows.dmitry.myshowsserial.apiUtils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;

import com.myShows.dmitry.myshowsserial.activity.LoginActivity;
import com.myShows.dmitry.myshowsserial.asyncTask.GetAsyncTask;
import com.myShows.dmitry.myshowsserial.enums.EnumMethod;
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
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.LOGIN_START);
        paramsBuilder.addMethod("method",ParamsBuilder.LOGIN_METHOD);
        paramsBuilder.addParams("login", login);
        paramsBuilder.addParams("password", password);
        getAsyncTask.execute(paramsBuilder);
    }

    public void profileGet(final ResultObjectListener resultListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.PROFILE_GET);
        paramsBuilder.addMethod("method",ParamsBuilder.PROFILE_METHOD);
        getAsyncTask.execute(paramsBuilder);
    }

    public void getShows(final ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.MY_SHOW);
        paramsBuilder.addMethod("method", ParamsBuilder.MY_SHOWS_METHOD);
        getAsyncTask.execute(paramsBuilder);
    }

    public void authorization(final ParamsBuilder paramsBuilder, final ResultObjectListener resultObjectListener) {
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
                        case MY_SHOW:
                            getShows(resultObjectListener);
                            break;
                        case CURRENT_SHOW:
                            getCurrentWatchShow(Integer.parseInt(paramsBuilder.getMethodByString("id")), resultObjectListener);
                            break;
                        case EPISODE_UNWATCHED:
                            getUnwatchedEpisode(resultObjectListener);
                            break;
                        case EPISODE_NEXT:
                            getNextEpisode(resultObjectListener);
                            break;
                        case EPISODE_CHECK:
                            if (paramsBuilder.getParamsByString("rating") != null) {
                                setCheckEpisode(
                                        Integer.parseInt(paramsBuilder.getMethodByString("id")),
                                        resultObjectListener,
                                        Integer.valueOf(paramsBuilder.getParamsByString("rating")));
                            } else {
                                setCheckEpisode(Integer.parseInt(paramsBuilder.getMethodByString("id")), resultObjectListener);
                            }
                            break;
                        case EPISODE_UNCHECKED:
                            setUnCheckEpisode(Integer.parseInt(paramsBuilder.getMethodByString("id")), resultObjectListener);
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

    public void getCurrentWatchShow(final int id, final ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.CURRENT_SHOW);
        paramsBuilder.addMethod("method",ParamsBuilder.MY_SHOWS_METHOD);
        paramsBuilder.addMethod("id",String.format("%s/", id));
        getAsyncTask.execute(paramsBuilder);
    }

    public void getUnwatchedEpisode(ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.EPISODE_UNWATCHED);
        paramsBuilder.addMethod("method", ParamsBuilder.EPISODE_UNWATCHED);
        getAsyncTask.execute(paramsBuilder);
    }

    public void getNextEpisode(ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.EPISODE_NEXT);
        paramsBuilder.addMethod("method",ParamsBuilder.EPISODE_NEXT);
        getAsyncTask.execute(paramsBuilder);
    }

    public void setCheckEpisode(int id, ResultObjectListener resultObjectListener, Integer... rating) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.EPISODE_CHECK);
        paramsBuilder.addMethod("method", ParamsBuilder.EPISODE_CHECK);
        if (rating.length != 0) {
            paramsBuilder.addParams("rating", String.valueOf(rating[0]));
        }
        paramsBuilder.addMethod("id",String.valueOf(id));
        getAsyncTask.execute(paramsBuilder);
    }

    public void setUnCheckEpisode(int id, ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.EPISODE_UNCHECKED);
        paramsBuilder.addParams("method", ParamsBuilder.EPISODE_UNCHECKED);
        paramsBuilder.addMethod("id", String.valueOf(id));
        getAsyncTask.execute(paramsBuilder);
    }

    public void getCurrentShow(int id, ResultObjectListener resultObjectListener) {
        GetAsyncTask getAsyncTask = new GetAsyncTask(mContext, resultObjectListener);
        ParamsBuilder paramsBuilder = new ParamsBuilder(EnumMethod.SHOW);
        paramsBuilder.addMethod("method", ParamsBuilder.SHOWS_METHOD);
        paramsBuilder.addMethod("id", String.valueOf(id));
        getAsyncTask.execute(paramsBuilder);
    }

}
