package com.myShows.dmitry.myshowsserial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.enums.EnumMethod;
import com.myShows.dmitry.myshowsserial.listener.ResultNothingListener;
import com.myShows.dmitry.myshowsserial.model.Episode;

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
        textView.setText(String.format("%s %s", mSeries.get(position).getId(), mSeries.get(position).getTitle()));
        CheckBox checkBox = (CheckBox) convertLayout.findViewById(R.id.episode_check_box_list);
        checkBox.setChecked(mSeries.get(position).isWatched());
        initOnClickListener(position, convertLayout, checkBox);
        return convertLayout;
    }

    private void initOnClickListener(final int position, final LinearLayout convertLayout, final CheckBox checkBox) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSeries.get(position).isWatched()) {
                    ApiManager.getInstance().setUnCheckEpisode(mSeries.get(position).getId(), new ResultNothingListener() {
                        @Override
                        public void onNothingResult() {
                            mSeries.get(position).setIsWatched(false);
                            checkBox.setChecked(mSeries.get(position).isWatched());
                        }
                    });
                } else {
                    ApiManager.getInstance().setCheckEpisode(mSeries.get(position).getId(), new ResultNothingListener() {
                        @Override
                        public void onNothingResult() {
                            mSeries.get(position).setIsWatched(true);
                            checkBox.setChecked(mSeries.get(position).isWatched());
                        }
                    });
                }
            }
        };
    }
}
