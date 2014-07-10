package com.myShows.dmitry.myshowsserial.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.adapter.EpisodeAdapter;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.listener.ResultEpisodeListener;
import com.myShows.dmitry.myshowsserial.model.Episode;

import java.util.List;

public class ShowCurrentActivity extends Activity {

    public static final String ID = "id";
    private List<Episode> mEpisodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_current);
        final ListView listView = (ListView) findViewById(R.id.main_list_view_show_current);
        int id = getIntent().getIntExtra(ID, 0);
        ApiManager.getInstance().getCurrentShow(id, new ResultEpisodeListener() {
            @Override
            public void onResultEpisodeList(List<Episode> episodeList) {
                if (episodeList != null) {
                    mEpisodeList = episodeList;
                    EpisodeAdapter episodeAdapter = new EpisodeAdapter(getApplicationContext(),
                            R.layout.element_list_series, mEpisodeList);
                    listView.setAdapter(episodeAdapter);
                }
            }

            @Override
            public void onErrorAuthorization() {}
        });
    }
}
