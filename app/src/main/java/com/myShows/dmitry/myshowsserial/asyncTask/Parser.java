package com.myShows.dmitry.myshowsserial.asyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myShows.dmitry.myshowsserial.enums.EnumMethod;
import com.myShows.dmitry.myshowsserial.model.Episode;
import com.myShows.dmitry.myshowsserial.model.Show;
import com.myShows.dmitry.myshowsserial.model.User;
import com.myShows.dmitry.myshowsserial.model.WatchEpisode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Parser {

    private static Parser INSTANCE;

    public static Parser getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Parser();
        }

        return INSTANCE;
    }

    private User parseUser(String json) {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        return user;
    }

    private List<Show> parseMassiveShow(String json) {
        Gson gson = new Gson();
        List<Show> tList = new ArrayList<Show>();
        Type mapType = new TypeToken<Map<String, Show>>() {
        }.getType();
        Map<String, Show> map = gson.fromJson(json, mapType);
        Set<Map.Entry<String, Show>> serialEntry = map.entrySet();
        for (Map.Entry<String, Show> entry : serialEntry) {
            tList.add(entry.getValue());
        }
        return tList;
    }

    private List<WatchEpisode> parseMassiveWatchEpisode(String json) {
        Gson gson = new Gson();
        List<WatchEpisode> tList = new ArrayList<WatchEpisode>();
        Type mapType = new TypeToken<Map<String, WatchEpisode>>() {
        }.getType();
        Map<String, WatchEpisode> map = gson.fromJson(json, mapType);
        Set<Map.Entry<String, WatchEpisode>> serialEntry = map.entrySet();
        for (Map.Entry<String, WatchEpisode> entry : serialEntry) {
            tList.add(entry.getValue());
        }
        return tList;
    }

    private List<Episode> parseMassiveEpisode(String json) {
        Gson gson = new Gson();
        List<Episode> tList = new ArrayList<Episode>();
        Type mapType = new TypeToken<Map<String, Episode>>() {
        }.getType();
        Map<String, Episode> map = gson.fromJson(json, mapType);
        Set<Map.Entry<String, Episode>> serialEntry = map.entrySet();
        for (Map.Entry<String, Episode> entry : serialEntry) {
            tList.add(entry.getValue());
        }
        return tList;
    }

    public Object parseMethod(EnumMethod enumMethod, String json) {
        switch (enumMethod) {
            case LOGIN_START:
                return null;
            case PROFILE_GET:
                return parseUser(json);
            case SHOW:
                return parseMassiveShow(json);
            case CURRENT_SHOW:
                return parseMassiveWatchEpisode(json);
            case EPISODE_UNWATCHED:
            case EPISODE_NEXT:
                return parseMassiveEpisode(json);
            default:
                return null;
        }
    }
}
