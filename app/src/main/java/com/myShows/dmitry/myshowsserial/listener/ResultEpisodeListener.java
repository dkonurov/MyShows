package com.myShows.dmitry.myshowsserial.listener;

import com.myShows.dmitry.myshowsserial.model.Episode;

import java.util.List;

abstract public class ResultEpisodeListener implements ResultObjectListener {

    @Override
    public void onResult(Object object) {
        onResultEpisodeList((List<Episode>) object);
    }

    @Override
    public void onErrorAuthorization() {}

    abstract public void onResultEpisodeList(List<Episode> episodeList);
}
