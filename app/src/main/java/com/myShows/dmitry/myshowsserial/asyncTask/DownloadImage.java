package com.myShows.dmitry.myshowsserial.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


abstract public class DownloadImage extends AsyncTask<String, Object, Bitmap> {

    private int URL_POSITION = 0;

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(params[URL_POSITION]);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("myShows", "file don't read");
        } finally {
            IOUtils.close(connection);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        onResult(bitmap);
    }

    abstract public void onResult(Bitmap bitmap);
}
