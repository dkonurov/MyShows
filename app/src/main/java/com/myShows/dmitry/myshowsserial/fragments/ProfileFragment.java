package com.myShows.dmitry.myshowsserial.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.activity.MainActivity;
import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.listener.ResultUserListener;
import com.myShows.dmitry.myshowsserial.model.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileFragment extends Fragment {

    private User mUser;

    public static ProfileFragment newInstance(int index) {
        ProfileFragment fragment = new ProfileFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        final ImageView avatar = (ImageView) rootView.findViewById(R.id.profile_avatar);
        final TextView login = (TextView) rootView.findViewById(R.id.profile_login);
        ApiManager.getInstance().profileGet(new ResultUserListener() {
            @Override
            public void onResultUser(User user) {
                if (user != null) {
                    mUser = user;
                    login.setText(mUser.getLogin());
                    ImageLoader.getInstance().displayImage(mUser.getAvatarUrl(), avatar);
                }
            }

            @Override
            public void onErrorAuthorization() {
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
    }
}
