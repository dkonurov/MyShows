package com.myShows.dmitry.myshowsserial.listener;

import com.myShows.dmitry.myshowsserial.model.WatchEpisode;

import java.util.List;

abstract public class ResultWatchEpisodeListener implements ResultObjectListener {

    @Override
    public void onResult(Object object) {
        onResultWatchEpisodeList((List<WatchEpisode>) object);
    }

    @Override
    public void onErrorAuthorization() {}

    abstract public void onResultWatchEpisodeList(List<WatchEpisode> watchEpisodeList);
}
