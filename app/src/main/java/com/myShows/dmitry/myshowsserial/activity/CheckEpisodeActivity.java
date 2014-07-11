package com.myShows.dmitry.myshowsserial.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.adapter.EpisodeAdapter;
import com.myShows.dmitry.myshowsserial.model.Episode;
import com.myShows.dmitry.myshowsserial.navigationDrawer.BackNavigationDrawer;

public class CheckEpisodeActivity extends ActionBarActivity
        implements BackNavigationDrawer.NavigationDrawerCallbacks {

    private BackNavigationDrawer mNavigationDrawerFragment;
    private Episode mEpisode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_episode);
        mEpisode = new Episode();
        mEpisode.setAirDate(getIntent().getStringExtra(EpisodeAdapter.AIR_DATE));
        mEpisode.setEpisodeId(getIntent().getIntExtra(EpisodeAdapter.EPISODE_ID, -1));
        mEpisode.setEpisodeNumber(getIntent().getIntExtra(EpisodeAdapter.EPISODE_NUMBER, -1));
        mEpisode.setSeasonNumber(getIntent().getIntExtra(EpisodeAdapter.SEASON_NUMBER, -1));
        mEpisode.setShowId(getIntent().getIntExtra(EpisodeAdapter.SHOW_ID, -1));
        mEpisode.setTitle(getIntent().getStringExtra(ShowCurrentActivity.TITLE));
        mNavigationDrawerFragment = (BackNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        setTitle(mEpisode.getTitle());


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {}
}
