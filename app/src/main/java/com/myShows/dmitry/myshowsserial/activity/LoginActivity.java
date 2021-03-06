package com.myShows.dmitry.myshowsserial.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.Toast;

import com.myShows.dmitry.myshowsserial.apiUtils.ApiManager;
import com.myShows.dmitry.myshowsserial.R;
import com.myShows.dmitry.myshowsserial.apiUtils.Utils;
import com.myShows.dmitry.myshowsserial.listener.ResultNothingListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AccountAuthenticatorActivity {


    private EditText mPasswordEditText;
    private EditText mLoginEditText;
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ApiManager.getInstance().setContext(getApplicationContext());
        mAccountManager = AccountManager.get(this);
        CookieSyncManager.createInstance(this);
        Account[] accounts = mAccountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
        if (accounts != null && accounts.length != 0) {
            startMainActivity();
        }
        mLoginEditText = (EditText) findViewById(R.id.login_activity_login);
        mPasswordEditText = (EditText) findViewById(R.id.login_activity_password);
    }

    public void onClickSignIn(View view) {
        try {
            final String loginText = URLEncoder.encode(mLoginEditText.getText().toString(), Utils.UTF_8);
            final String passwordText = Utils.md5(URLEncoder.
                    encode(mPasswordEditText.getText().toString(), Utils.UTF_8));
            if (loginText != null && passwordText != null
                    && loginText.length() != 0 && passwordText.length() != 0) {
                ApiManager.getInstance().loginStart(loginText, passwordText, new ResultNothingListener() {
                    @Override
                    public void onNothingResult() {
                        Account account = new Account(loginText,
                                ApiManager.ACCOUNT_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putString(AccountManager.KEY_ACCOUNT_NAME, loginText);
                        bundle.putString(AccountManager.KEY_PASSWORD, passwordText);
                        if (mAccountManager.addAccountExplicitly(account, passwordText, bundle)) {
                            Log.d("myShows", "add Account");
                            startMainActivity();
                        }
                    }

                    @Override
                    public void onErrorAuthorization() {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.error_login_or_password),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.please_write_login_pass), Toast.LENGTH_SHORT).show();
            }
        } catch (UnsupportedEncodingException e) {
            Log.d("myShows", "System don't  make decode");
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
