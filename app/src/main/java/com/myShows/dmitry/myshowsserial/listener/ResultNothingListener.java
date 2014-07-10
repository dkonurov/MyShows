package com.myShows.dmitry.myshowsserial.listener;


public abstract class ResultNothingListener implements ResultObjectListener {
    @Override
    public void onResult(Object object) {
        onNothingResult();
    }

    abstract public void onNothingResult();
}
