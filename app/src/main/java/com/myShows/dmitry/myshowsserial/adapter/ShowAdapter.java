package com.myShows.dmitry.myshowsserial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.activity.MainActivity;
import com.myShows.dmitry.myshowsserial.activity.ShowCurrentActivity;
import com.myShows.dmitry.myshowsserial.model.Show;

import java.util.List;

public class ShowAdapter extends ArrayAdapter<Show> {

    private final LayoutInflater mInflater;
    private final int mResource;
    private final List<Show> mShows;
    private final MainActivity mActivity;

    public ShowAdapter(MainActivity activity, int resource, List<Show> shows) {
        super(activity, resource, shows);
        mResource = resource;
        mShows = shows;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mActivity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.label_show_list);
        textView.setText(generateTitle(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowCurrentActivity.class);
                intent.putExtra(ShowCurrentActivity.ID, mShows.get(position).getShowId());
                intent.putExtra(ShowCurrentActivity.TITLE, mShows.get(position).getTitle());
                getContext().startActivity(intent);
            }
        });
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
