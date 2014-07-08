package com.myShows.dmitry.myshowsserial.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.model.Show;

import java.util.List;

public class ShowAdapter extends ArrayAdapter<Show> {

    private final LayoutInflater mInflater;
    private final int mResource;
    private final List<Show> mShows;

    public ShowAdapter(Context context, int resource, List<Show> shows) {
        super(context, resource, shows);
        mResource = resource;
        mShows = shows;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.label_show_list);
        textView.setText(generateTitle(position));
        return convertView;
    }

    private String generateTitle(int position) {
        if (mShows.get(position).getRuTitle() == null && mShows.get(position).getTitle() == null) {
            return "No name";
        } else if (mShows.get(position).getRuTitle() == null) {
            return mShows.get(position).getTitle();
        } else if (mShows.get(position).getTitle() == null) {
            return mShows.get(position).getRuTitle();
        } else {
            return String.format("%s/%s", mShows.get(position).getTitle(), mShows.get(position).getRuTitle());
        }
    }
}
