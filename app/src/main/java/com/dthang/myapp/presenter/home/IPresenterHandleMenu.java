package com.dthang.myapp.presenter.home;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface IPresenterHandleMenu {
    void getMenus();
    void handle(Context context);
    AccessToken getmAccessToken(Context context);
    GoogleSignInAccount getGoogleSignInAccount(Context context);
    String getUserName();
    void handleLogout();
}
