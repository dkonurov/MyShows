package com.myShows.dmitry.myshowsserial.asyncTask.parser;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.myShows.dmitry.myshowsserial.model.User;
import com.nostra13.universalimageloader.core.ImageLoader;

abstract public class GsonParserToUser extends AsyncTask<String, Void, User> {

    private Bitmap mBitmap;

    @Override
    protected User doInBackground(String... params) {
        Gson gson = new Gson();
        User user = gson.fromJson(params[0],User.class);
        mBitmap = ImageLoader.getInstance().loadImageSync(user.getAvatarUrl());
        return user;
    }

    @Override
    protected void onPostExecute(User user) {
        onResultParser(user, mBitmap);
    }

    abstract public void onResultParser(User user, Bitmap bitmap);

}
