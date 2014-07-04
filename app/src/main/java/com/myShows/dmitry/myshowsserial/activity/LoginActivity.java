package com.myShows.dmitry.myshowsserial.activity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.myShows.dmitry.myshowsserial.R;

public class LoginActivity extends ActionBarActivity {

    private static final String ACCOUNT_TYPE ="MyShowSer";

    private EditText mPasswordEditText;
    private EditText mLoginEditText;
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAccountManager = AccountManager.get(this);
        mLoginEditText = (EditText) findViewById(R.id.login_activity_login);
        mPasswordEditText = (EditText) findViewById(R.id.login_activity_password);
    }




    public void onClickSignUp(View view) {
        //TODO need create response for login to site
        final String loginText = mLoginEditText.getText().toString();
        final String passwordText = mPasswordEditText.getText().toString();

        LoginTask loginTask = new LoginTask();
        loginTask.execute();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class LoginTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }
    }
}
