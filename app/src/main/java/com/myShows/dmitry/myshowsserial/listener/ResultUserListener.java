package com.myShows.dmitry.myshowsserial.listener;

import com.myShows.dmitry.myshowsserial.model.User;

public abstract class ResultUserListener implements ResultObjectListener {

    @Override
    public void onResult(Object object) {
        onResultUser((User) object);
    }

    abstract public void onResultUser(User user);
}
