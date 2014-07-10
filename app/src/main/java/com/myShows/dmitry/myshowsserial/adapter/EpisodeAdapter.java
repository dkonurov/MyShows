package com.myShows.dmitry.myshowsserial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.model.Episode;

import java.util.List;

public class EpisodeAdapter extends ArrayAdapter<Episode> {

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }
        LinearLayout convertLayout = (LinearLayout) convertView;
        TextView textView = (TextView) convertView.findViewById(R.id.series_label_list);
        textView.setText(String.format("%s %s", mSeries.get(position).getId(), mSeries.get(position).getWatchDate()));
        for (int i = 0, length = mSeries.get(position).getRating(); i < length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.myshowsstyle_btn_rating_star_on_focused_holo_light);
            convertLayout.addView(imageView);
        }
        return convertLayout;
    }
}
