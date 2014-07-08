package com.myShows.dmitry.myshowsserial.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.activity.MainActivity;
import com.myShows.dmitry.myshowsserial.adapter.ShowAdapter;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.asyncTask.parser.GsonParserToMapSerial;
import com.myShows.dmitry.myshowsserial.listener.ResultJsonListener;
import com.myShows.dmitry.myshowsserial.model.Show;

import java.util.List;

public class ShowSerialFragment extends Fragment {

    private List<Show> mShows;
    private Context mContext;

    public static ShowSerialFragment newInstance(int index) {
        ShowSerialFragment fragment = new ShowSerialFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_serial, container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.main_layout_shows);
        ApiManager.getInstance().getSerials(new ResultJsonListener() {
            @Override
            public void onJSONListener(String json) {
                GsonParserToMapSerial gsonParserToMapSerial = new GsonParserToMapSerial() {
                    @Override
                    public void onResultParser(List<Show> showList) {
                        mShows = showList;
                        ShowAdapter showAdapter = new ShowAdapter(mContext, R.layout.element_list_shows, showList);
                        listView.setAdapter(showAdapter);
                    }
                };
                gsonParserToMapSerial.execute(json);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
        mContext = activity.getApplicationContext();
    }
}
