package com.myShows.dmitry.myshowsserial.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.adapter.EpisodeAdapter;
import com.myShows.dmitry.myshowsserial.adapter.WatchEpisodeAdapter;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.listener.ResultEpisodeListener;
import com.myShows.dmitry.myshowsserial.listener.ResultWatchEpisodeListener;
import com.myShows.dmitry.myshowsserial.model.Episode;
import com.myShows.dmitry.myshowsserial.model.WatchEpisode;
import com.myShows.dmitry.myshowsserial.navigationDrawer.BackNavigationDrawer;

import java.util.List;

public class ShowCurrentActivity extends ActionBarActivity
        implements BackNavigationDrawer.NavigationDrawerCallbacks {

    public static final String TITLE = "TITLE_SHOW_CURRENT";
    public static final String ID = "ID";
    private BackNavigationDrawer mNavigationDrawerFragment;
    private List<WatchEpisode> mWatchEpisodeList;
    private List<Episode> mUnwatchEpisodeList;
    private List<Episode> mNextEpisodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_current);

        final ListView listViewWatch = (ListView) findViewById(R.id.list_view_watch_episode);
        final ListView listViewUnWatch = (ListView) findViewById(R.id.list_view_unwatch_episode);
        final ListView listViewNext = (ListView) findViewById(R.id.list_view_next_episode);
        mNavigationDrawerFragment = (BackNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        setTitle(getIntent().getStringExtra(TITLE));

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.closeDrawers();
        int id = getIntent().getIntExtra(ID, -1);
        ApiManager.getInstance().getCurrentShow(id, new ResultWatchEpisodeListener() {
            @Override
            public void onResultWatchEpisodeList(List<WatchEpisode> watchEpisodeList) {
                mWatchEpisodeList = watchEpisodeList;
                WatchEpisodeAdapter watchEpisodeAdapter = new WatchEpisodeAdapter(getApplicationContext(),
                        R.layout.element_list_series, mWatchEpisodeList);
                listViewWatch.setAdapter(watchEpisodeAdapter);
            }
        });
        ApiManager.getInstance().getUnwatchedEpisode(new ResultEpisodeListener() {

            @Override
            public void onResultEpisodeList(List<Episode> episodeList) {
                mUnwatchEpisodeList = episodeList;
                EpisodeAdapter unwatchEpisodeAdapter = new EpisodeAdapter(getApplicationContext(),
                        R.layout.element_list_series, mUnwatchEpisodeList);
                listViewUnWatch.setAdapter(unwatchEpisodeAdapter);
            }
        });
        ApiManager.getInstance().getNextEpisode(new ResultEpisodeListener() {
            @Override
            public void onResultEpisodeList(List<Episode> episodeList) {
                mNextEpisodeList = episodeList;
                EpisodeAdapter nextEpisodeAdapter = new EpisodeAdapter(getApplicationContext(),
                        R.layout.element_list_series, mNextEpisodeList);
                listViewNext.setAdapter(nextEpisodeAdapter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    }
}
