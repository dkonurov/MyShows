package com.myShows.dmitry.myshowsserial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.activity.CheckEpisodeActivity;
import com.myShows.dmitry.myshowsserial.activity.ShowCurrentActivity;
import com.myShows.dmitry.myshowsserial.model.Episode;

import java.util.InputMismatchException;
import java.util.List;

public class EpisodeAdapter extends ArrayAdapter {
    public static final String EPISODE_ID = "EPISODE_ID";
    public static final String SHOW_ID = "SHOW_ID";
    public static final String SEASON_NUMBER = "SEASON_NUMBER";
    public static final String EPISODE_NUMBER = "EPISODE_NUMBER";
    public static final String AIR_DATE = "AIR_DATE";
    private final LayoutInflater mInflater;
    private final int mResource;
    private final List<Episode> mSeries;

    public EpisodeAdapter(Context context, int resource, List<Episode> series) {
        super(context, resource, series);
        mResource = resource;
        mSeries = series;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }
        LinearLayout convertLayout = (LinearLayout) convertView;
        TextView textView = (TextView) convertView.findViewById(R.id.series_label_list);
        textView.setText(String.format("%s %s", mSeries.get(position).getEpisodeId(), mSeries.get(position).getTitle()));
        convertLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckEpisodeActivity.class);
                intent.putExtra(ShowCurrentActivity.TITLE, mSeries.get(position).getTitle());
                intent.putExtra(EPISODE_ID, mSeries.get(position).getEpisodeId());
                intent.putExtra(SHOW_ID, mSeries.get(position).getShowId());
                intent.putExtra(SEASON_NUMBER, mSeries.get(position).getSeasonNumber());
                intent.putExtra(EPISODE_NUMBER, mSeries.get(position).getEpisodeNumber());
                intent.putExtra(AIR_DATE, mSeries.get(position).getAirDate());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        return convertLayout;
    }
}
