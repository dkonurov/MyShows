package com.myShows.dmitry.myshowsserial.listener;

import com.myShows.dmitry.myshowsserial.model.Show;

import java.util.List;

abstract public class ResultShowListListener implements ResultObjectListener {
    @Override
    public void onResult(Object object) {
        onResultShowList((List<Show>) object);
    }

    @Override
    public void onErrorAuthorization() {}

    abstract public void onResultShowList(List<Show> showList);
}
