package com.myShows.dmitry.myshowsserial.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.myShows.dmitry.myshowsserial.R;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AccountAuthenticatorActivity {

    private static final String UTF_8 = "UTF-8";
    private static final int LOGIN_POSITION = 0;
    private static final int PASSWORD_POSITION = 1;
    public static final String ACCOUNT_TYPE = "com.myShowsSer";

    private EditText mPasswordEditText;
    private EditText mLoginEditText;
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAccountManager = AccountManager.get(this);
        Account[] accounts = mAccountManager.getAccountsByType(ACCOUNT_TYPE);
        if (accounts != null && accounts.length != 0) {
            startMainActivity();
        }
        mLoginEditText = (EditText) findViewById(R.id.login_activity_login);
        mPasswordEditText = (EditText) findViewById(R.id.login_activity_password);
    }




    public void onClickSignIn(View view) {
        //TODO need create response for login to site
        try {
            final String loginText = URLEncoder.encode(mLoginEditText.getText().toString(), UTF_8);
            final String passwordText = md5(URLEncoder.
                    encode(mPasswordEditText.getText().toString(), UTF_8));
            if (loginText != null && passwordText != null
                    && loginText.length() != 0 && passwordText.length() != 0) {
                LoginTask loginTask = new LoginTask();
                loginTask.execute(loginText, passwordText);
            } else {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.please_write_login_pass), Toast.LENGTH_SHORT).show();
            }
        } catch (UnsupportedEncodingException e) {
            Log.d("myShows","System don't  make decode");
        }
    }

    private class LoginTask extends AsyncTask<String, Boolean, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            String url = String.format("http://api.myshows.ru/profile/login?login=%s&password=%s",
                    params[LOGIN_POSITION], params[PASSWORD_POSITION]);
            Log.d("url", url);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    Account account = new Account(params[LOGIN_POSITION],
                            ACCOUNT_TYPE);
                    Bundle bundle = new Bundle();
                    bundle.putString(mAccountManager.KEY_ACCOUNT_NAME, params[LOGIN_POSITION]);
                    bundle.putString(mAccountManager.KEY_PASSWORD, params[PASSWORD_POSITION]);
                    if (mAccountManager.addAccountExplicitly(account, params[PASSWORD_POSITION], bundle)) {

                    }
                }
                return statusLine.getStatusCode();

            } catch (IOException e) {
                Log.d("myShows", "error reading");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer statusCode) {
            switch (statusCode) {
                case 403:
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.error_login_or_password),
                            Toast.LENGTH_SHORT).show();
                    break;
                case 200:
                    startMainActivity();
                    break;
            }
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Log.d("myShows", "No Algorithm");
        }
        return null;
    }
}
