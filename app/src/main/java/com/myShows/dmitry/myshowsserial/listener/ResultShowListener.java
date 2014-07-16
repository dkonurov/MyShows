package com.myShows.dmitry.myshowsserial.listener;


import com.myShows.dmitry.myshowsserial.model.Show;

public abstract class ResultShowListener implements ResultObjectListener {

    @Override
    public void onResult(Object object) {
        onResultShow((Show) object);
    }

    @Override
    public void onErrorAuthorization() {}

    protected abstract void onResultShow(Show show);
}
