package com.myShows.dmitry.myshowsserial.asyncTask.parser;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myShows.dmitry.myshowsserial.model.Show;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class GsonParserToMapSerial extends AsyncTask<String, Void, List<Show>> {

    @Override
    protected List<Show> doInBackground(String... params) {
        Gson gson = new Gson();
        List<Show> shows = new ArrayList<Show>();
        Type mapType = new TypeToken<Map<String, Show>>() {
        }.getType();
        Map<String, Show> map = gson.fromJson(params[0], mapType);
        Set<Map.Entry<String, Show>> serialEntry = map.entrySet();
        for (Map.Entry<String, Show> entry : serialEntry) {
            shows.add(entry.getValue());
        }
        return shows;
    }

    @Override
    protected void onPostExecute(List<Show> shows) {
        onResultParser(shows);
    }

    abstract public void onResultParser(List<Show> shows);

}
