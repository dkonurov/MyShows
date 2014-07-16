package com.myShows.dmitry.myshowsserial.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.adapter.EpisodeAdapter;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.listener.ResultEpisodeListener;
import com.myShows.dmitry.myshowsserial.listener.ResultShowListener;
import com.myShows.dmitry.myshowsserial.model.Episode;
import com.myShows.dmitry.myshowsserial.model.Show;
import com.myShows.dmitry.myshowsserial.navigationDrawer.BackNavigationDrawer;

import java.util.List;
import java.util.Map;

public class ShowCurrentActivity extends ActionBarActivity
        implements BackNavigationDrawer.NavigationDrawerCallbacks {

    public static final String TITLE = "TITLE_SHOW_CURRENT";
    public static final String ID = "ID";
    private BackNavigationDrawer mNavigationDrawerFragment;
    private Show mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_current);
        mNavigationDrawerFragment = (BackNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        setTitle(getIntent().getStringExtra(TITLE));
        final int id = getIntent().getIntExtra(ID, -1);
        final ListView listView = (ListView) findViewById(R.id.list_view_watch_episode);
        ApiManager.getInstance().getCurrentShow(id, new ResultShowListener() {
            @Override
            protected void onResultShow(final Show show) {
                mShow = show;
                ApiManager.getInstance().getCurrentWatchShow(id, new ResultEpisodeListener() {
                    @Override
                    public void onResultEpisodeList(List<Episode> episodeList) {
                        for(Episode episode : episodeList) {
                            String idString = String.valueOf(episode.getId());
                           if (mShow.getEpisodeMap().containsKey(idString)) {
                               mShow.getEpisodeMap().get(idString).setIsWatched(true);
                           }
                        }
                        EpisodeAdapter episodeAdapter = new EpisodeAdapter(getApplicationContext(),
                                R.layout.element_list_series, mShow.getEpisodeList());
                        listView.setAdapter(episodeAdapter);
                    }
                });
            }
        });


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.closeDrawers();

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
