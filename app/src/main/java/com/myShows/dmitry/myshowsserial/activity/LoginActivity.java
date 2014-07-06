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

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.myShows.dmitry.myshowsserial.ApiManager;
import com.myShows.dmitry.myshowsserial.Listener.ResultListener;
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

    private EditText mPasswordEditText;
    private EditText mLoginEditText;
    private AccountManager mAccountManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAccountManager = AccountManager.get(this);
        ApiManager.getInstance().setContext(this);
        Account[] accounts = mAccountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
        if (accounts != null && accounts.length != 0) {
            startMainActivity();
        }
        mLoginEditText = (EditText) findViewById(R.id.login_activity_login);
        mPasswordEditText = (EditText) findViewById(R.id.login_activity_password);
    }




    public void onClickSignIn(View view) {
        try {
            final String loginText = URLEncoder.encode(mLoginEditText.getText().toString(), UTF_8);
            final String passwordText = md5(URLEncoder.
                    encode(mPasswordEditText.getText().toString(), UTF_8));
            if (loginText != null && passwordText != null
                    && loginText.length() != 0 && passwordText.length() != 0) {
                ApiManager.getInstance().LoginStart(loginText, passwordText, new ResultListener() {
                    @Override
                    public void onResult() {
                        Account account = new Account(loginText,
                                ApiManager.ACCOUNT_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putString(mAccountManager.KEY_ACCOUNT_NAME, loginText);
                        bundle.putString(mAccountManager.KEY_PASSWORD, passwordText);
                        if (mAccountManager.addAccountExplicitly(account, passwordText, bundle)) {
                            Log.d("myShows", "addAccount");
                            startMainActivity();
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.please_write_login_pass), Toast.LENGTH_SHORT).show();
            }
        } catch (UnsupportedEncodingException e) {
            Log.d("myShows","System don't  make decode");
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
